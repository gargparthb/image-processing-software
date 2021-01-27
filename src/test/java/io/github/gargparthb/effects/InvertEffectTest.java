package io.github.gargparthb.effects;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class InvertEffectTest {

  @Test
  void apply() {
    Color c = new Color(232, 42, 100);
    assertEquals(new InvertEffect(true).apply(c), new Color(23, 213, 155));
  }
}