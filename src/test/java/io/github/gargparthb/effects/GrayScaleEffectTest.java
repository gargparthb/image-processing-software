package io.github.gargparthb.effects;

import io.github.gargparthb.ColorUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GrayScaleEffectTest {

  @Test
  @DisplayName("tests the grayscale effect")
  void apply() {
    GrayScaleEffect gs = new GrayScaleEffect(false);
    GrayScaleEffect gs1 = new GrayScaleEffect(true);

    Color c = new Color(43, 64, 2);

    assertEquals(gs.apply(c), c);
    assertEquals(gs1.apply(c), new Color(50,50,50));
  }
}