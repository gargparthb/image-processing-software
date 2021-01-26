package io.github.gargparthb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ColorUtilsTest {

  @Test
  @DisplayName("tests the light level calculation")
  void grayScaleValue() {
    assertEquals(ColorUtils.grayScaleValue(new Color(0, 0, 0)), 0.0);
    assertEquals(ColorUtils.grayScaleValue(new Color(255, 157, 157)), 0.73, 0.05);
  }

  @Test
  @DisplayName("tests the truncate number util")
  void truncate() {
    assertEquals(ColorUtils.makeValidColorValue(1.0), 1);
    assertEquals(ColorUtils.makeValidColorValue(300.4), 255);
    assertEquals(ColorUtils.makeValidColorValue(-54.4), 0);
    assertEquals(ColorUtils.makeValidColorValue(43.3), 43);
  }
}