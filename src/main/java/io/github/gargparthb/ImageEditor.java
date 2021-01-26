package io.github.gargparthb;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

class ImageEditor {
  BufferedImage img;

  File output;

  double brightnessScalar, highlightScalar, shadowScalar;

  double contrastScalar;

  boolean grayscale;

  double temperature;
  double tint;

  ImageEditor(Path img,
              String outName,
              double brightnessScalar,
              double highlightScalar,
              double shadowScalar,
              double contrastScalar,
              boolean grayscale,
              double temperature,
              double tint) throws IOException {
    this.img = ImageIO.read(img.toFile());
    this.output = generateOutputPath(img, outName);
    this.brightnessScalar = validateRange(brightnessScalar);
    this.highlightScalar = validateRange(highlightScalar);
    this.shadowScalar = validateRange(shadowScalar);
    this.contrastScalar = validateRange(contrastScalar);
    this.grayscale = grayscale;
    this.temperature = validateRange(temperature);
    this.tint = validateRange(tint);
  }

  // testing constructor
  // no IO operations
  ImageEditor(double brightnessScalar, double highlightScalar, double shadowScalar, double contrastScalar, boolean grayscale, double temperature, double tint) {
    this.brightnessScalar = brightnessScalar;
    this.highlightScalar = highlightScalar;
    this.shadowScalar = shadowScalar;
    this.contrastScalar = contrastScalar;
    this.grayscale = grayscale;
    this.temperature = temperature;
    this.tint = tint;
  }

  // main editing method
  public void edit() throws IOException {

    Color tinter = this.getSpectrumColor(new Color(255, 0, 255), new Color(0, 255, 0), this.tint);
    Color temper = this.getSpectrumColor(new Color(255, 0, 0), new Color(0, 0, 255), this.temperature);

    // loops through the colors of each pixel in the image
    for (int i = 0; i < this.img.getWidth(); i++) {
      for (int j = 0; j < this.img.getHeight(); j++) {
        Color currentCol = new Color(this.img.getRGB(i, j), true);

        // Light(pixel) = Contrast * Color + Brightness
        currentCol = this.contrastedColor(currentCol);

        // adds brightness factor to the color
        currentCol = this.brightenedColor(currentCol);

        // adds the grayscale maybe
        if(this.grayscale) {
          currentCol = ColorUtils.toGrayScale(currentCol);
        }

        // applies the tint
        if(tinter.getAlpha() != 0) {
          currentCol = ColorUtils.composeOver(currentCol, tinter);
        }

        // changes temperature
        if(temper.getAlpha() != 0) {
          currentCol = ColorUtils.composeOver(currentCol, temper);
        }

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

  // calculates the contrasted color
  public Color contrastedColor(Color base) {
    // C = F(C - 128) + 128

    double contrastAdjustment = 255 * this.contrastScalar;
    RGBVector factor = new RGBVector((259 * (contrastAdjustment + 255)) / (255 * (259 - contrastAdjustment)));

    RGBVector baseVector = new RGBVector(base);
    RGBVector offset = baseVector.sum(new RGBVector(-128.0));
    RGBVector factored = offset.product(factor);
    RGBVector recenter = factored.sum(new RGBVector(128.0));

    return recenter.toColor();
  }

  // calculates the color with the brightness factored in
  public Color brightenedColor(Color base) {
    // accumulates all the transformations
    double totalLightAdjustment = this.brightnessScalar;
    double grayscale = ColorUtils.grayScaleValue(base);

    if (grayscale >= 0.75) {
      totalLightAdjustment += this.highlightScalar;
    } else if (grayscale <= 0.25) {
      totalLightAdjustment += this.shadowScalar;
    }

    // put transformation into a vector
    RGBVector brightnessTransformation = new RGBVector(255 * totalLightAdjustment);

    return brightnessTransformation.sum(base);
  }

  // calculates the spectrum color between c1 and c2
  public Color getSpectrumColor(Color c1, Color c2, double val) {
    if(val > 0) {
      return new Color(c1.getRed(), c1.getGreen(), c1.getBlue(), (int) (255 * val));
    } else if (val < 0) {
      return new Color(c2.getRed(), c2.getGreen(), c2.getBlue(), (int) Math.abs(255 * val));
    } else {
      return new Color(0,0,0,0);
    }
  }
}
