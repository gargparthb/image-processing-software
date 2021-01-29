package io.github.gargparthb.transformations;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ITransformation {
  // the image -> image functional interface
  BufferedImage apply(BufferedImage in) throws IOException;
}
