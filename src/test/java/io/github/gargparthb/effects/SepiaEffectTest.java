package io.github.gargparthb.effects;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SepiaEffectTest {

  @Test
  void apply() {
    Color c = new Color(100, 220, 75);
    assertEquals(new SepiaEffect(true).apply(c),
            new Color(222, 198, 154));
  }
}