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
}
