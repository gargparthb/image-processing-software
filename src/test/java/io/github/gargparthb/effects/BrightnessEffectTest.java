package io.github.gargparthb.effects;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BrightnessEffectTest {

  @Test
  void apply() {
    BrightnessEffect be = new BrightnessEffect(0.4, -0.2, 0.1);

    assertEquals(be.apply(new Color(255,225,140)), new Color(255,255,191));
  }
}