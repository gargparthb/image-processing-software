package io.github.gargparthb.transformations;

import io.github.gargparthb.Composition;
import io.github.gargparthb.effects.ColorFilterEffect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageOverTransformation implements ITransformation {
   BufferedImage img;
   int x, y;

  public ImageOverTransformation(Composition settings) throws IOException {
    if(settings != null) {
      this.img = ImageIO.read(settings.getImg().toFile());
      this.x = settings.getX();
      this.y = settings.getY();
    }
  }

  public ImageOverTransformation(BufferedImage img, int x, int y) {
    this.img = img;
    this.x = x;
    this.y = y;
  }

  public BufferedImage apply(BufferedImage in) {
    // checks to see if the image is there and should be composited
    if (this.img != null) {
      // loops over two coordinates
      for (int xBase = x, xOver = 0; xBase < in.getWidth() && xOver < img.getWidth(); xBase++, xOver++) {
        for (int yBase = y, yOver = 0; yBase < in.getHeight() && yOver < img.getHeight(); yBase++, yOver++) {
          Color base = new Color(in.getRGB(xBase, yBase), true);
          Color over = new Color(img.getRGB(xOver, yOver), true);

          in.setRGB(xBase, yBase, new ColorFilterEffect(over).apply(base).getRGB());
        }
      }
    }
    return in;
  }
}
