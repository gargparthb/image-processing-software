package io.github.gargparthb;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

class ImageEditor {
  BufferedImage img;

  File output;

  double brightnessScalar;

  ImageEditor(Path img, String outName, double b) throws IOException {
    this.img = ImageIO.read(img.toFile());
    this.output = generateOutputPath(img, outName);
    this.brightnessScalar = validateRange(b, -1.0, 1.0);
  }

  // main editing method
  public void edit() throws IOException {
    for(int i = 0; i < this.img.getWidth(); i++) {
      for(int j = 0; j < this.img.getHeight(); j++) {
        // list of vectors to apply
        ArrayList<IColorVector> shaders = new ArrayList<>();

        // adds the brightness to the shaders
        if (this.brightnessScalar != 0) shaders.add(new RGBVector(1 + brightnessScalar));

        Color currentCol = new Color(this.img.getRGB(i, j), true);
        Color edited = ColorUtils.applyAll(currentCol, shaders);

        this.img.setRGB(i, j, edited.getRGB());
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

  // gets the output path given the optional outpath
  public static File generateOutputPath(Path img, String outName) {
    String currentName = img.toFile().getName();
    String newName;
    int periodIdx = currentName.indexOf('.');

    if(outName.equals("")) {
      // inserts -edited into the file name
      newName = currentName.substring(0, periodIdx) + "-edited" + currentName.substring(periodIdx);
    } else {
      newName = outName + currentName.substring(periodIdx);
    }
    return new File(img.getParent().toFile(), newName);
  }
}
