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

  public Color sum(Color other) {
    double newRed = this.r + other.getRed();
    double newGreen = this.g + other.getGreen();
    double newBlue = this.b + other.getBlue();
    RGBVector vec = new RGBVector(newRed, newGreen, newBlue);
    return vec.toColor();
  }

  public Color toColor() {
    return new Color(ColorUtils.truncate(this.r), ColorUtils.truncate(this.g), ColorUtils.truncate(this.b));
  }
}
