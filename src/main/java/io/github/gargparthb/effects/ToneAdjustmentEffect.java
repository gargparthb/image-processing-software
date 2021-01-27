package io.github.gargparthb.effects;

import io.github.gargparthb.ColorUtils;
import io.github.gargparthb.HSVVector;

import java.awt.*;
import java.util.HashMap;

public class ToneAdjustmentEffect {
  HashMap<String, HSVVector> table;

  public ToneAdjustmentEffect(HashMap<String, HSVVector> table) {
    this.table = table;
  }

  public Color apply(Color in) {
    HSVVector base = new HSVVector(in);
    String tone = ColorUtils.getColorTone(in);
    if(this.table.containsKey(tone)) {
      return base.apply(this.table.get(tone)).toColor();
    } else {
      return in;
    }
  }
}
