package io.github.gargparthb.effects;

import io.github.gargparthb.ColorUtils;
import io.github.gargparthb.RGBVector;

import java.awt.*;

public class BrightnessEffect implements IEffect {
  double brightnessScalar, highlightScalar, shadowScalar;

  public BrightnessEffect(double brightnessScalar, double highlightScalar, double shadowScalar) {
    this.brightnessScalar = brightnessScalar;
    this.highlightScalar = highlightScalar;
    this.shadowScalar = shadowScalar;
  }

  public Color apply(Color in) {
    // accumulates all the transformations
    double totalLightAdjustment = this.brightnessScalar;
    double grayscale = ColorUtils.grayScaleValue(in);

    if (grayscale >= 0.75) {
      totalLightAdjustment += this.highlightScalar;
    } else if (grayscale <= 0.25) {
      totalLightAdjustment += this.shadowScalar;
    }

    // put transformation into a vector
    RGBVector brightnessTransformation = new RGBVector(255 * totalLightAdjustment);

    return brightnessTransformation.sumWithColor(in);
  }
}
