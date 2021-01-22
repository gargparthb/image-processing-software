package io.github.gargparthb;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

public class ImageEditor {
  BufferedImage img;

  ImageEditor(File img) throws IOException {
    this.img = ImageIO.read(img);
  }

  // main editing method
  void edit() {

  }
}
