package io.github.gargparthb;

import io.github.gargparthb.effects.*;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

class ImageEditor {
  BufferedImage img;

  File output;

  BrightnessEffect brightnessEffect;

  ContrastEffect contrastEffect;

  GrayScaleEffect grayScaleEffect;

  OverEffect temperatureEffect, tintEffect;

  SaturationEffect saturationEffect;

  ImageEditor(Path img,
              String outName,
              double brightnessScalar,
              double highlightScalar,
              double shadowScalar,
              double contrastScalar,
              boolean grayscale,
              double temperatureScalar,
              double tintScalar,
              double saturationScalar) throws IOException {
    this.img = ImageIO.read(img.toFile());
    this.output = generateOutputPath(img, outName);

    this.brightnessEffect = new BrightnessEffect(
            validateRange(brightnessScalar),
            validateRange(highlightScalar),
            validateRange(shadowScalar));

    this.contrastEffect = new ContrastEffect(validateRange(contrastScalar));

    this.grayScaleEffect = new GrayScaleEffect(grayscale);

    this.temperatureEffect = new OverEffect(Color.RED, Color.BLUE, validateRange(temperatureScalar));
    this.tintEffect = new OverEffect(new Color(255, 0, 255), Color.GREEN, validateRange(tintScalar));

    this.saturationEffect = new SaturationEffect(validateRange(saturationScalar));
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

        // adds the grayscale maybe
        currentCol = this.grayScaleEffect.apply(currentCol);

        // applies the tint
        currentCol = this.tintEffect.apply(currentCol);

        // changes temperature
        currentCol = this.temperatureEffect.apply(currentCol);

        // saturation
        currentCol = this.saturationEffect.apply(currentCol);

        this.img.setRGB(i, j, currentCol.getRGB());
      }

    }

    ImageIO.write(img, "jpg", this.output);
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
    return new File(img.getParent().toFile(), newName);
  }
}
