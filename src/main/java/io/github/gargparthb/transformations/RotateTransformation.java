package io.github.gargparthb.transformations;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RotateTransformation implements ITransformation  {
  double angle;

  public RotateTransformation(double angle) {
    this.angle = angle;
  }

  public BufferedImage apply(BufferedImage in) {
    if(this.angle != 0) {
      // creates a white background for the rotated image
      int w = in.getWidth();
      int h = in.getHeight();

      BufferedImage bg = new BufferedImage(w * 2, h * 2, BufferedImage.TYPE_INT_RGB);

      Graphics2D graphics = bg.createGraphics();
      graphics.setColor(new Color(255, 255, 255));
      graphics.fillRect(0, 0, w * 2, h * 2);
      graphics.dispose();

      for (int i = 0; i < w; i++) {
        for (int j = 0; j < h; j++) {
          int originX = i - (int) Math.round(w / 2.0);
          int originY = (int) Math.round(h / 2.0) - j;
          double dist = Point2D.distance(originX, originY, 0, 0);

          double theta = Math.atan2(originY, originX);
          double newAngle = theta + this.angle * Math.PI / 180;

          int xPrime = (int) Math.round(dist * Math.cos(newAngle)) + w;
          int yPrime = h - (int) Math.round(dist * Math.sin(newAngle));

          bg.setRGB(xPrime, yPrime, in.getRGB(i, j));
        }
      }
      return bg;
    } else {
      return in;
    }
  }
}
