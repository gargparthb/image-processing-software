package io.github.gargparthb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ImageEditorTest {
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
}