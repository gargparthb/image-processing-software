package io.github.gargparthb.effects;

import io.github.gargparthb.ColorUtils;
import io.github.gargparthb.RGBVector;

import java.awt.*;

public class GrayScaleEffect implements IEffect {
  boolean isGrayScale;

  public GrayScaleEffect(boolean isGrayScale) {
    this.isGrayScale = isGrayScale;
  }

  public Color apply(Color in) {
    if (isGrayScale) {
      double v = ColorUtils.grayScaleValue(in);
      RGBVector vec = new RGBVector(v * 255);
      return vec.toColor();
    } else {
      return in;
    }
  }
}
