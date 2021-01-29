package io.github.gargparthb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ImageEditorTest {
  @Test
  @DisplayName("tests output path generation")
  void generateOutputPath() {
    assertEquals(ImageEditor.generateOutputPath(Path.of("/test/test.jpg"), ""),
            new File("/test/test-edited.jpg"));
    assertEquals(ImageEditor.generateOutputPath(Path.of("/test/test.jpg"), "final"),
            new File("/test/final.jpg"));
    assertEquals(ImageEditor.generateOutputPath(Path.of("test.jpg"), ""),
            new File("test-edited.jpg"));
  }
}