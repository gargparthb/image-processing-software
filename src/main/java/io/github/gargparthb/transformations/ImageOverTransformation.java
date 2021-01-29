package io.github.gargparthb.transformations;

import io.github.gargparthb.Composition;
import io.github.gargparthb.effects.ColorFilterEffect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageOverTransformation implements ITransformation {
  Composition settings;

  public ImageOverTransformation(Composition settings) {
    this.settings = settings;
  }

  public BufferedImage apply(BufferedImage in) throws IOException {
    // checks to see if the image is there and should be composited
    if (this.settings != null) {
      BufferedImage overImage = ImageIO.read(this.settings.getImg().toFile());

      // loops over two coordinates
      for (int xBase = this.settings.getX(), xOver = 0; xBase < in.getWidth() && xOver < overImage.getWidth(); xBase++, xOver++) {
        for (int yBase = this.settings.getY(), yOver = 0; yBase < in.getHeight() && yOver < overImage.getHeight(); yBase++, yOver++) {
          Color base = new Color(in.getRGB(xBase, yBase), true);
          Color over = new Color(overImage.getRGB(xOver, yOver), true);

          in.setRGB(xBase, yBase, new ColorFilterEffect(over).apply(base).getRGB());
        }
      }
    }
    return in;
  }
}
