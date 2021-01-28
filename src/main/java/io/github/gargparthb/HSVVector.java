package io.github.gargparthb;

import java.awt.*;

import static io.github.gargparthb.ColorUtils.truncate;

public class HSVVector implements IColorVector {
  double h, s, v;

  HSVVector(double h, double s, double v) {
    this.h = h;
    this.s = s;
    this.v = v;
  }

  // Color -> Vec
  public HSVVector(Color color) {
    float[] col = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
    this.h = col[0];
    this.s = col[1];
    this.v = col[2];
  }

  // String -> Vec
  public HSVVector(String s) {
    String formatted = s.replaceAll("\\s", "").substring(1, s.length() - 1);
    String[] split = formatted.split(",");
    this.h = Double.parseDouble(split[0]);
    this.s = Double.parseDouble(split[1]);
    this.v = Double.parseDouble(split[2]);
  }

  public Color toColor() {
    return new Color(Color.HSBtoRGB((float) this.h, (float) this.s, (float) this.v));
  }

  // increase saturation by the given factor and converts to color
  public Color saturate(double factor) {
    this.s = truncate(0.0, 1.0, this.s * factor);
    return this.toColor();
  }

  // applies a vector transformation to the given HSV
  public HSVVector apply(HSVVector transformation) {
    double appliedHue = this.h * (1 + transformation.h);
    double newHue = appliedHue % ((int) appliedHue);
    double newSaturation = truncate(0.0, 1.0, this.s * (1 + transformation.s));
    double newVal = truncate(0.0, 1.0, this.v * (1 + transformation.v));
    return new HSVVector(newHue, newSaturation, newVal);
  }

  // contract
  public int hashCode() {
    return super.hashCode();
  }

  // equals
  public boolean equals(Object obj) {
    if(obj instanceof HSVVector){
      HSVVector other = (HSVVector) obj;
      return other.h == this.h && other.s == this.s && other.v == this.v;
    }
    return false;
  }

  public double getH() {
    return h;
  }

  public double getS() {
    return s;
  }

  public double getV() {
    return v;
  }
}
