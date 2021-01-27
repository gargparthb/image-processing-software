package io.github.gargparthb.effects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ColorFilterEffectTest {
  @Test
  @DisplayName("tests the application of a color filter")
  void testColorFilter() {
    ColorFilterEffect cfe = new ColorFilterEffect(new Color(255, 0,0, 100));
    ColorFilterEffect cfe1 = new ColorFilterEffect(new Color(255));

    assertEquals(cfe.apply(new Color(0x09FFFF)), new Color(105, 155, 155));
  }
}