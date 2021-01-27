package io.github.gargparthb.effects;

import io.github.gargparthb.HSVVector;

import java.awt.*;

public class SaturationEffect implements IEffect {
  double scalar;

  public SaturationEffect(double scalar) {
    this.scalar = scalar;
  }

  public Color apply(Color in) {
    HSVVector hsv = new HSVVector(in);
    // maps to the new range
    return hsv.saturate(1 + this.scalar);
  }
}
