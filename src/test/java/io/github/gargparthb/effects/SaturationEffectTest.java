package io.github.gargparthb.effects;

import io.github.gargparthb.HSVVector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SaturationEffectTest {

  @Test
  @DisplayName("test the saturation effect application")
  void apply() {
    SaturationEffect eff = new SaturationEffect(0.6);
    assertEquals(eff.apply(new Color(34, 43, 64)), new HSVVector(new Color(34,43,64)).saturate(1.6));
  }
}