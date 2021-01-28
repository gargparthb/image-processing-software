package io.github.gargparthb.effects;

import io.github.gargparthb.ColorUtils;

import java.awt.*;

public class SepiaEffect implements IEffect {
  boolean isSepia;

  public SepiaEffect(boolean isSepia) {
    this.isSepia = isSepia;
  }

  public Color apply(Color in) {
    if (this.isSepia) {
      int r = in.getRed();
      int g = in.getGreen();
      int b = in.getBlue();

      // takes multiple weighted averages of a color
      int newRed = ColorUtils.makeValidColorValue(0.393 * r + 0.769 * g + 0.189 * b);
      int newGreen = ColorUtils.makeValidColorValue(0.349 * r + 0.686 * g + 0.168 * b);
      int newBlue = ColorUtils.makeValidColorValue(0.272 * r + 0.534 * g + 0.131 * b);

      return new Color(newRed, newGreen, newBlue);
    } else {
      return in;
    }
  }
}
