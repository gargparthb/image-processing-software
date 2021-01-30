package io.github.gargparthb.effects;

import io.github.gargparthb.ColorUtils;
import io.github.gargparthb.RGBVector;

import java.awt.*;

public class ColorFilterEffect implements IEffect {
  Color over;

  public ColorFilterEffect(Color over) {
    this.over = over;
  }

  // generates the spectrum color
  public ColorFilterEffect(Color l, Color r, double val) {
    this(ColorUtils.getSpectrumColor(l, r, val));
  }

  public Color apply(Color in) {
    if(over.getAlpha() != 0) {
      double overAlpha = over.getAlpha() / 255.0;

      RGBVector source = new RGBVector(over).product(new RGBVector(overAlpha));
      RGBVector destination = new RGBVector(in).product(new RGBVector(1 - overAlpha));
      RGBVector col = source.sum(destination);

      return col.toColor();
    } else {
      return in;
    }
  }
}
