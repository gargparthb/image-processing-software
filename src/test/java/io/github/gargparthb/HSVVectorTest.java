package io.github.gargparthb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class HSVVectorTest {
  HSVVector vec1 = new HSVVector(0.3778, 0.7, 0.66);
  HSVVector vec2 = new HSVVector(0.1, 0.4, 0.5);

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

  @Test
  @DisplayName("tests the transformation application")
  void testApply() {
    assertEquals(vec1.apply(vec2).getH(), 0.41, 0.1);
    assertEquals(vec1.apply(vec2).getS(), 0.98, 0.1);
    assertEquals(vec1.apply(vec2).getV(), 0.99, 0.1);
  }

  @Test
  @DisplayName(("tests String -> Vec"))
  void testStringToVector() {
    assertEquals(new HSVVector("(0.4,2,5)"), new HSVVector(0.4,2,5));
  }
}