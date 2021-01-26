package io.github.gargparthb;

import java.awt.*;
public class ColorUtils {
  // turns the given RGB color into a grayscale percentage
  public static double grayScaleValue(Color color) {
    // Grayscale  = 0.299R + 0.587G + 0.114B
    return (0.299 * color.getRed() +
            0.587 * color.getGreen() +
            0.114 * color.getBlue()) / 255;
  }

  // turns the double into a color valid value in the range [0, 255]
  public static int makeValidColorValue(double d) {
    return (int) truncate(0.0, 255.0, d);
  }

  // truncates in the given range
  public static double truncate(double min, double max, double d) {
    return Math.min(max, Math.max(min, d));
  }

  // calculates the spectrum color between c1 and c2
  public static Color getSpectrumColor(Color c1, Color c2, double val) {
    if (val > 0) {
      return new Color(c1.getRed(), c1.getGreen(), c1.getBlue(), (int) (255 * val));
    } else if (val < 0) {
      return new Color(c2.getRed(), c2.getGreen(), c2.getBlue(), (int) Math.abs(255 * val));
    } else {
      return new Color(0,0,0,0);
    }
  }
}
