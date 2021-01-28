package io.github.gargparthb.effects;

import io.github.gargparthb.RGBVector;

import java.awt.*;

public class GammaEffect implements IEffect {
  double scalar;

  public GammaEffect(double scalar) {
    this.scalar = scalar;
  }

  public Color apply(Color in) {
    double gammaCorrection = 1.0 / this.scalar;
    RGBVector vec = new RGBVector(in);
    RGBVector normalized = vec.product(new RGBVector(1.0 / 255.0));
    RGBVector applied = normalized.pow(gammaCorrection);
    RGBVector remapped = applied.product(new RGBVector(255));
    return remapped.toColor();
  }
}
