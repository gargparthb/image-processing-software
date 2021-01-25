package io.github.gargparthb;

import java.awt.*;

public class RGBVector {
  double r, g, b;

  RGBVector(double r, double g, double b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  RGBVector(double v) {
    this(v, v, v);
  }

  RGBVector(Color color) {
    this(color.getRed(), color.getGreen(), color.getBlue());
  }

  // adds the vector to color and converts to color
  public Color sum(Color other) {
    double newRed = this.r + other.getRed();
    double newGreen = this.g + other.getGreen();
    double newBlue = this.b + other.getBlue();
    RGBVector vec = new RGBVector(newRed, newGreen, newBlue);
    return vec.toColor();
  }

  // sums two vectors
  public RGBVector sum(RGBVector other) {
    double newRed = this.r + other.r;
    double newGreen = this.g + other.g;
    double newBlue = this.b + other.b;
    return new RGBVector(newRed, newGreen, newBlue);
  }

  // multiplies two vectors together
  public RGBVector product(RGBVector other) {
    double newRed = this.r * other.r;
    double newGreen = this.g * other.g;
    double newBlue = this.b * other.b;
    return new RGBVector(newRed, newGreen, newBlue);
  }

  // Getters
  public double getR() {
    return r;
  }

  public double getG() {
    return g;
  }

  public double getB() {
    return b;
  }

  // contract
  public int hashCode() {
    return super.hashCode();
  }

  // vec -> color
  public Color toColor() {
    return new Color(ColorUtils.truncate(this.r), ColorUtils.truncate(this.g), ColorUtils.truncate(this.b));
  }

  // equals method
  public boolean equals(Object o) {
    if(o instanceof RGBVector) {
      return ((RGBVector) o).getR() == this.r
              && ((RGBVector) o).getG() == this.g
              && ((RGBVector) o).getB() == this.b;
    }
    return false;
  }
}
