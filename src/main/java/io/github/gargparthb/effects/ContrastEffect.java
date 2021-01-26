package io.github.gargparthb.effects;

import io.github.gargparthb.RGBVector;

import java.awt.*;

public class ContrastEffect implements IEffect {
  // between -1 and 1
  double scalar;

  public ContrastEffect(double scalar) {
    this.scalar = scalar;
  }

  public Color apply(Color in) {
    // C = F(C - 128) + 128

    double contrastAdjustment = 255 * this.scalar;
    RGBVector factor = new RGBVector((259 * (contrastAdjustment + 255)) / (255 * (259 - contrastAdjustment)));

    RGBVector baseVector = new RGBVector(in);
    RGBVector offset = baseVector.sum(new RGBVector(-128.0));
    RGBVector factored = offset.product(factor);
    RGBVector recenter = factored.sum(new RGBVector(128.0));

    return recenter.toColor();
  }
}
