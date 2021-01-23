package io.github.gargparthb;

import java.awt.*;

public class RGBVector implements IColorVector {
  double r, g, b;

  RGBVector(double r, double g, double b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  RGBVector(double v) {
    this(v, v, v);
  }

  public Color applyTo(Color base) {
    return new Color(
            (int) Math.min(255.0, this.r * base.getRed()),
            (int) Math.min(255.0, this.g * base.getGreen()),
            (int) Math.min(255.0, this.b * base.getBlue()));
  }
}
