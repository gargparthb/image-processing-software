package io.github.gargparthb;

import java.awt.*;
import java.util.ArrayList;

public class ColorUtils {
  // applies all the color vectors the the base color
  public static Color applyAll(Color base, ArrayList<IColorVector> toApply) {
    for (int i = 0; i < toApply.size(); i++) {
      base = toApply.get(0).applyTo(base);
    }
    return base;
  }

  //  turns the given RGB color into a grayscale percentage
  public static double toGrayScale(Color color) {
    // Grayscale  = 0.299R + 0.587G + 0.114B
    return 0.299 * color.getRed() +
            0.587 * color.getGreen() +
            0.114 * color.getBlue();
  }
}
