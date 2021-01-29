package io.github.gargparthb;

import io.github.gargparthb.effects.*;
import io.github.gargparthb.transformations.ImageOverTransformation;
import io.github.gargparthb.transformations.ScaleTransformation;
import picocli.CommandLine;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.swing.text.html.Option;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

class ImageEditor {
  BufferedImage img;

  File output;

  // creates a bunch of functional objects

  BrightnessEffect brightnessEffect;

  ContrastEffect contrastEffect;

  GammaEffect gammaEffect;

  GrayScaleEffect grayScaleEffect;
  InvertEffect invertEffect;
  SepiaEffect sepiaEffect;

  ColorFilterEffect temperatureEffect, tintEffect;

  SaturationEffect saturationEffect;

  HueAdjustmentEffect hueAdjustments;
  ToneAdjustmentEffect toneAdjustments;

  ImageOverTransformation overTransformation;

  ScaleTransformation scaleTransformation;

  ImageEditor(Path img,
              String outName,
              double brightnessScalar,
              double highlightScalar,
              double shadowScalar,
              double contrastScalar,
              double gammaScalar,
              boolean grayscale,
              boolean invert,
              boolean sepia,
              double temperatureScalar,
              double tintScalar,
              double saturationScalar,
              HashMap<String, HSVVector> hueAdjustments,
              HashMap<String, HSVVector> toneAdjustments,
              Composition overConfig,
              HashMap<String, Double> scales) throws IOException {
    this.img = ImageIO.read(img.toFile());
    this.output = generateOutputPath(img, outName);

    this.brightnessEffect = new BrightnessEffect(
            validateRange(brightnessScalar),
            validateRange(highlightScalar),
            validateRange(shadowScalar));

    this.contrastEffect = new ContrastEffect(validateRange(contrastScalar));

    this.gammaEffect = new GammaEffect(validateRange(gammaScalar, 0.001, 7.0));

    this.grayScaleEffect = new GrayScaleEffect(grayscale);
    this.invertEffect = new InvertEffect(invert);
    this.sepiaEffect = new SepiaEffect(sepia);

    this.temperatureEffect = new ColorFilterEffect(Color.RED, Color.BLUE, validateRange(temperatureScalar));
    this.tintEffect = new ColorFilterEffect(new Color(255, 0, 255), Color.GREEN, validateRange(tintScalar));

    this.saturationEffect = new SaturationEffect(validateRange(saturationScalar));

    this.hueAdjustments = new HueAdjustmentEffect(hueAdjustments);
    this.toneAdjustments = new ToneAdjustmentEffect(toneAdjustments);

    this.overTransformation = new ImageOverTransformation(overConfig);

    this.scaleTransformation = new ScaleTransformation(
            validateRange(getScalar(scales, "x"), 0.001, 1000),
            validateRange(getScalar(scales, "y"), 0.001, 1000));
  }

  // main editing method
  public void edit() throws IOException {

    // loops through the colors of each pixel in the image
    for (int i = 0; i < this.img.getWidth(); i++) {
      for (int j = 0; j < this.img.getHeight(); j++) {
        Color currentCol = new Color(this.img.getRGB(i, j), true);

        // uses the effect functional interface to build on the same color.

        // Light(pixel) = Contrast * Color + Brightness
        currentCol = this.contrastEffect.apply(currentCol);

        // adds brightness factor to the color
        currentCol = this.brightnessEffect.apply(currentCol);

        // applies the gamma
        currentCol = this.gammaEffect.apply(currentCol);

        // adds the grayscale maybe
        currentCol = this.grayScaleEffect.apply(currentCol);

        // maybe sepia
        currentCol = this.sepiaEffect.apply(currentCol);

        // applies the tint
        currentCol = this.tintEffect.apply(currentCol);

        // changes temperature
        currentCol = this.temperatureEffect.apply(currentCol);

        // saturation
        currentCol = this.saturationEffect.apply(currentCol);

        // color adjustments
        currentCol = this.hueAdjustments.apply(currentCol);
        currentCol = this.toneAdjustments.apply(currentCol);

        // invert should go last
        currentCol = this.invertEffect.apply(currentCol);

        this.img.setRGB(i, j, currentCol.getRGB());
      }

    }

    // over composition
    this.img = this.overTransformation.apply(this.img);

    // scale transformations
    this.img = this.scaleTransformation.apply(this.img);


    ImageIO.write(this.img, "jpg", this.output);
  }

  // checks if the double is the given range
  public static double validateRange(double d, double min, double max) {
    if (d < min || d > max) {
      throw new IllegalArgumentException("invalid parameter values");
    }
    return d;
  }

  // checks if the double is in [-1, 1]
  public static double validateRange(double d) {
    return validateRange(d, -1.0, 1.0);
  }

  // gets the output path given the optional out name
  public static File generateOutputPath(Path img, String outName) {
    String currentName = img.toFile().getName();
    String newName;
    int periodIdx = currentName.indexOf('.');

    if (outName.equals("")) {
      // inserts -edited into the file name
      newName = currentName.substring(0, periodIdx) + "-edited" + currentName.substring(periodIdx);
    } else {
      newName = outName + currentName.substring(periodIdx);
    }

    // generates the file from the given name and directory
    if (img.getNameCount() < 2) {
      return new File(newName);
    } else {
      return new File(img.getParent().toFile(), newName);
    }
  }

  // gets the given value from the hashmap
  public static double getScalar(HashMap<String, Double> map, String axis) {
    return map.getOrDefault(
            "all",
            map.getOrDefault(axis, 1.0)
    );
  }
}
