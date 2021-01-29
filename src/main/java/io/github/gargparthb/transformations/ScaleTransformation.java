package io.github.gargparthb.transformations;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ScaleTransformation implements ITransformation {
  double scaleX, scaleY;

  public ScaleTransformation(double scaleX, double scaleY) {
    this.scaleX = scaleX;
    this.scaleY = scaleY;
  }

  public BufferedImage apply(BufferedImage in) {
    if (this.scaleX != 1.0 && this.scaleY != 1.0) {
      int newX = (int) (in.getWidth() * this.scaleX);
      int newY = (int) (in.getHeight() * this.scaleY);

      double xRatio = 1 / this.scaleX;
      double yRatio = 1 / this.scaleY;

      BufferedImage scaled = new BufferedImage(newX, newY, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < newX; i++) {
        for (int j = 0; j < newY; j++) {
          int mappedX = (int) (i * xRatio);
          int mappedY = (int) (j * yRatio);

          scaled.setRGB(i, j, in.getRGB(mappedX, mappedY));
        }
      }

      return scaled;
    } else {
      return in;
    }
  }
}
