package io.github.gargparthb.effects;

import java.awt.*;

public class InvertEffect implements IEffect {
  boolean toBeInverted;

  public InvertEffect(boolean toBeInverted) {
    this.toBeInverted = toBeInverted;
  }


  public Color apply(Color in) {
    if(this.toBeInverted) {
      int newRed = 255 - in.getRed();
      int newGreen = 255 - in.getGreen();
      int newBlue = 255 - in.getBlue();

      return new Color(newRed, newGreen, newBlue);
    } else {
      return in;
    }
  }
}
