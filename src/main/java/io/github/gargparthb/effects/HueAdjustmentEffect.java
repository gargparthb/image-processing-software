package io.github.gargparthb.effects;

import io.github.gargparthb.ColorUtils;
import io.github.gargparthb.HSVVector;

import java.awt.*;
import java.util.HashMap;

public class HueAdjustmentEffect implements IEffect {
  HashMap<String, HSVVector> table;

  public HueAdjustmentEffect(HashMap<String, HSVVector> table) {
    this.table = table;
  }

  public Color apply(Color in) {
    HSVVector base = new HSVVector(in);
    String hue = ColorUtils.getColorType(in);
    if(this.table.containsKey(hue)) {
      return base.apply(this.table.get(hue)).toColor();
    } else {
      return in;
    }
  }
}
