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

  double brightnessScalar;
  double highlightScalar;

  ImageEditor(Path img, String outName, double brightnessScalar, double highlightScalar) throws IOException {
    this.img = ImageIO.read(img.toFile());
    this.output = generateOutputPath(img, outName);
    this.brightnessScalar = validateRange(brightnessScalar, -1.0, 1.0);
    this.highlightScalar = validateRange(highlightScalar, -1.0, 1.0);
  }

  // main editing method
  public void edit() throws IOException {
    for (int i = 0; i < this.img.getWidth(); i++) {
      for (int j = 0; j < this.img.getHeight(); j++) {
        Color currentCol = new Color(this.img.getRGB(i, j), true);

        // this section controls the absolute brightness of the image
        Color brightened = this.brightenedColor(currentCol);

        this.img.setRGB(i, j, brightened.getRGB());
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

  public Color brightenedColor(Color base) {
    // accumulates all the transformations
    double totalLightAdjustment = this.brightnessScalar;

    if (ColorUtils.toGrayScale(base) >= 0.75) {
      totalLightAdjustment += this.highlightScalar;
    }

    // put transformation into a vector
    RGBVector brightnessTransformation = new RGBVector(255 * totalLightAdjustment);

    return brightnessTransformation.sum(base);
  }
}
