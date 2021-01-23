package io.github.gargparthb;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageEditor {
  File output;

  BufferedImage img;

  double brightnessScalar;

  ImageEditor(File img, Double b) throws IOException {
    this.output = img;
    this.img = ImageIO.read(img);
    this.brightnessScalar = b;
  }

  // main editing method
  void edit() throws IOException {
    for(int i = 0; i < this.img.getWidth(); i++) {
      for(int j = 0; j < this.img.getHeight(); j++) {
        // list of vectors to apply
        ArrayList<IColorVector> shaders = new ArrayList<>();

        // adds the brightness to the shaders
        if (this.brightnessScalar != 0) shaders.add(new RGBVector(1 + brightnessScalar));

        Color currentCol = new Color(this.img.getRGB(i, j));
        Color edited = ColorUtils.applyAll(currentCol, shaders);

        this.img.setRGB(i, j, edited.getRGB());
      }
    }

    ImageIO.write(img, "jpg", this.output);
  }
}
