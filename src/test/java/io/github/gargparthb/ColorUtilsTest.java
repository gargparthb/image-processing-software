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
  @DisplayName("calculates the grayscale color")
  void toGrayScale() {
    assertEquals(ColorUtils.toGrayScale(new Color(144, 203, 120)), new Color(175, 175, 175));
  }

  @Test
  @DisplayName("tests the truncate number util")
  void truncate() {
    assertEquals(ColorUtils.truncate(1.0), 1);
    assertEquals(ColorUtils.truncate(300.4), 255);
    assertEquals(ColorUtils.truncate(-54.4), 0);
    assertEquals(ColorUtils.truncate(43.3), 43);
  }

  @Test
  @DisplayName("tests the over operator")
  void testComposeOver() {
    Color c1 = new Color(68, 160, 100, 153);
    Color c2 = new Color(186, 29, 195);

    assertEquals(ColorUtils.composeOver(c2, c1), new Color(115, 107, 138));
  }

  @Test
  @DisplayName("testing saturation")
  void testSaturation() {
    assertEquals(ColorUtils.saturate(new Color(50, 168, 82), 1.5), new Color(0, 168, 46));
  }
}