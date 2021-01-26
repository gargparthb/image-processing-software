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

  public HSVVector(Color color) {
    float[] col = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
    this.h = col[0];
    this.s = col[1];
    this.v = col[2];
  }

  public Color toColor() {
    return new Color(Color.HSBtoRGB((float) this.h, (float) this.s, (float) this.v));
  }

  // increase saturation by the given factor and converts to color
  public Color saturate(double factor) {
    this.s = truncate(0.0, 1.0, this.s * factor);
    return this.toColor();
  }
}
