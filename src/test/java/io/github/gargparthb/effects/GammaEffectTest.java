package io.github.gargparthb.effects;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GammaEffectTest {

  @Test
  void apply() {
    GammaEffect g = new GammaEffect(2.2);
    Color c = new Color(64, 46, 187);
    assertEquals(g.apply(c), new Color(136, 117, 221));
  }
}