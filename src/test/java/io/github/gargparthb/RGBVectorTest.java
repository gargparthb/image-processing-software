package io.github.gargparthb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RGBVectorTest {
  @Test
  @DisplayName("tests the vector sum to color method")
  void testSum() {
    RGBVector vec = new RGBVector(65.3);
    Color col = new Color(165, 194, 255);
    assertEquals(vec.sumWithColor(col), new Color(230, 255, 255));
  }

  @Test
  @DisplayName("tests the vector + vector method")
  void testSumVector() {
    RGBVector vec = new RGBVector(43.4);
    RGBVector vec1 = new RGBVector(-12.2);

    assertEquals(vec.sum(vec1), new RGBVector(31.2));
  }

  @Test
  @DisplayName("tests the vector * vector method")
  void testProductVector() {
    RGBVector vec = new RGBVector(43.4);
    RGBVector vec1 = new RGBVector(-12.2);

    assertEquals(vec.product(vec1).getB(), -529.48, 0.1);
  }

  @Test
  @DisplayName("tests to Color")
  void testToColor() {
    RGBVector vec = new RGBVector(45.4, 32.2, -2.3);
    assertEquals(vec.toColor(), new Color(45, 32, 0));
  }
}