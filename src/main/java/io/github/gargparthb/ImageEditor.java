package io.github.gargparthb;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

public class ImageEditor {
  BufferedImage img;

  double brightnessScalar;

  ImageEditor(File img, Double b) throws IOException {
    this.img = ImageIO.read(img);
    this.brightnessScalar = b;
  }

  // main editing method
  void edit() {

  }
}
