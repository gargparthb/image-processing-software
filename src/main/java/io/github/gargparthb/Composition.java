package io.github.gargparthb;

import picocli.CommandLine.Option;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class Composition {
  @Option(names = {"--over-image", "-over"}, description = "file for the over file", required = true)
  Path img;

  @Option(names = "-x", description = "x position for composition", defaultValue = "0")
  int x;

  @Option(names = "-y", description = "y position for composition", defaultValue = "0")
  int y;

  public Path getImg() {
    return img;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
