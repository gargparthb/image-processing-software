package io.github.gargparthb;

import java.awt.*;
public class ColorUtils {
  // turns the given RGB color into a grayscale percentage
  public static double grayScaleValue(Color color) {
    // Grayscale  = 0.299R + 0.587G + 0.114B
    return (0.299 * color.getRed() +
            0.587 * color.getGreen() +
            0.114 * color.getBlue()) / 255;
  }

  // turns the double into a color valid value in the range [0, 255]
  public static int makeValidColorValue(double d) {
    return (int) truncate(0.0, 255.0, d);
  }

  // truncates in the given range
  public static double truncate(double min, double max, double d) {
    return Math.min(max, Math.max(min, d));
  }

  // calculates the spectrum color between c1 and c2
  public static Color getSpectrumColor(Color c1, Color c2, double val) {
    if (val > 0) {
      return new Color(c1.getRed(), c1.getGreen(), c1.getBlue(), (int) (255 * val));
    } else if (val < 0) {
      return new Color(c2.getRed(), c2.getGreen(), c2.getBlue(), (int) Math.abs(255 * val));
    } else {
      return new Color(0,0,0,0);
    }
  }

  // returns the broad color classification, i.e. red, orange ...
  public static String getColorType(Color color) {
    float[] hsv = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
    double hue = 360 * hsv[0];

    if(hsv[1] < 0.1) return "white";
    else if (hsv[2] < 0.1) return "black";
    else if (hue > 345 || hue <= 15) return "red";
    else if (onInterval(15, 45, hue)) return "orange";
    else if (onInterval(45, 75, hue)) return "yellow";
    else if (onInterval(75, 150, hue)) return "green";
    else if (onInterval(150, 180, hue)) return "cyan";
    else if (onInterval(180, 255, hue)) return "blue";
    else if (onInterval(255, 285, hue)) return "purple";
    else return "magenta";
  }

  // checks if the val in on the (] interval
  public static boolean onInterval(int min, int max, double d) {
    return d > min && d <= max;
  }

  // gets the color tone by comparing grayscale
  public static String getColorTone(Color color) {
    if (ColorUtils.grayScaleValue(color) > 0.75) return "light";
    else if (ColorUtils.grayScaleValue(color) < 0.25) return "dark";
    return "mid";
  }
}
