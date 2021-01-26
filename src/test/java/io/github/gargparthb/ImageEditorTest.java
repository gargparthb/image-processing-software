package io.github.gargparthb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ImageEditorTest {
  ImageEditor editor = new ImageEditor(0.5, 0.1, -0.4, 0.2, true, 0.2, -0.3);

  @Test
  @DisplayName("range testing of ImageEditors")
  void testRangeTesting() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> ImageEditor.validateRange(-2.0, -1.0, 1.0));
    assertEquals(e.getMessage(), "invalid parameter values");
  }

  @Test
  @DisplayName("tests the output path generation")
  void testOutputPath() {
    assertEquals(ImageEditor.generateOutputPath(Path.of("/test", "test.txt"), ""), new File("/test/test-edited.txt"));
    assertEquals(ImageEditor.generateOutputPath(Path.of("/test", "test.txt"), "new-file"), new File("/test/new-file.txt"));
  }

  @Test
  @DisplayName("tests the brigthening of colors")
  void testBrighten() {
    assertEquals(editor.brightenedColor(new Color(72, 229, 6)), new Color(199, 255, 133));
    assertEquals(editor.brightenedColor(new Color(100, 234, 220)), new Color(253, 255,255));
  }

  @Test
  @DisplayName("tests the contrasting of color")
  void testContrast() {
    assertEquals(editor.contrastedColor(new Color(179, 181, 247)), new Color(204, 207, 255));
  }

  @Test
  @DisplayName("tests the spectrum color generation")
  void testSpectrumColor() {
    assertEquals(editor.getSpectrumColor(new Color(255, 0, 255), new Color(0, 255, 0), editor.tint),
            new Color(0, 255, 0, 76));
  }
}