package io.github.gargparthb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class HSVVectorTest {
  HSVVector vec1 = new HSVVector(0.3778, 0.7, 0.66);

  @Test
  @DisplayName("tests to color method")
  void toColor() {
    assertEquals(vec1.toColor(), new Color(50, 168, 82));
  }

  @Test
  @DisplayName("tests the saturate color")
  void saturate() {
    assertEquals(vec1.saturate(1.2), new HSVVector(0.3778, 0.84, 0.66).toColor());
  }
}