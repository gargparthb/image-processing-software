package io.github.gargparthb;

import java.awt.*;

public class RGBVector implements IColorVector {
  double r, g, b;

  RGBVector(double r, double g, double b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public RGBVector(double v) {
    this(v, v, v);
  }

  public RGBVector(Color color) {
    this(color.getRed(), color.getGreen(), color.getBlue());
  }

  // adds the vector to color and converts to color
  public Color sumWithColor(Color other) {
    return this.sum(new RGBVector(other)).toColor();
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

  // takes the vector to the given power
  public RGBVector pow(double exp) {
    double newRed = Math.pow(this.r, exp);
    double newGreen = Math.pow(this.g, exp);
    double newBlue = Math.pow(this.b, exp);
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
    return new Color(ColorUtils.makeValidColorValue(this.r), ColorUtils.makeValidColorValue(this.g), ColorUtils.makeValidColorValue(this.b));
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
