package io.github.gargparthb.effects;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ContrastEffectTest {

  @Test
  void apply() {
    ContrastEffect ce = new ContrastEffect(0.3);
    Color c = new Color(0xFFE635);

    assertEquals(ce.apply(c), new Color(255, 255, 0));
  }
}