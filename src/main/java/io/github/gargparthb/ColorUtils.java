package io.github.gargparthb;

import java.awt.*;
import java.nio.file.Path;

public class ColorUtils {
  // turns the given RGB color into a grayscale percentage
  public static double grayScaleValue(Color color) {
    // Grayscale  = 0.299R + 0.587G + 0.114B
    return (0.299 * color.getRed() +
            0.587 * color.getGreen() +
            0.114 * color.getBlue()) / 255;
  }

  // generates the grayscale color from the given base
  public static Color toGrayScale(Color base) {
    double v = grayScaleValue(base);
    RGBVector vec = new RGBVector(v * 255);
    return vec.toColor();
  }

  // turns the double into a color valid value
  public static int truncate(double d) {
    return (int) Math.min(255.0, Math.max(0.0, d));
  }
}
