package io.github.gargparthb;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Help.Ansi;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    // points cmd args to the run method
    Method run = CommandLine.getCommandMethods(Main.class, "run").get(0);
    CommandLine cmd = new CommandLine(run);
    cmd.registerConverter(HSVVector.class, HSVVector::new);
    cmd.execute(args);
  }

  // starts the ImageEditor class
  @Command(name = "edit",
          description = "edit the given image(s)",
          version = "1.0.0",
          mixinStandardHelpOptions = true)
  public void run(@Parameters(paramLabel = "main image and any composite images") Path imgs,
                  @Option(names = {"-o", "--out"}, description = "out path", defaultValue = "") String outName,
                  @Option(names = {"-b", "--brightness"}, description = "light multiplier between -1.0 and 1.0", defaultValue = "0.0") double lightMultiplier,
                  @Option(names = {"-hl", "--highlights"}, description = "light multiplier for the lighter parts of image", defaultValue = "0.0") double highlightsMult,
                  @Option(names = {"-s", "--shadows"}, description = "light multiplier for the shadow of image", defaultValue = "0.0") double shadowMult,
                  @Option(names = {"-c", "--contrast"}, description = "multiplier for contrast adjustment", defaultValue = "0.0") double contrast,
                  @Option(names = {"-gam", "--gamma"}, description = "gamma correction factor", defaultValue = "1.0") double gamma,
                  @Option(names = {"-g", "--grayscale"}, description = "convert image to grayscale", defaultValue = "false") boolean grayscale,
                  @Option(names = {"-i", "--invert"}, description = "inverts the image", defaultValue = "false") boolean invert,
                  @Option(names = {"--sepia"}, description = "sepia filter", defaultValue = "false") boolean sepia,
                  @Option(names = {"-temp", "--temperature"}, description = "gives a red/blue overlay to image", defaultValue = "0.0") double temp,
                  @Option(names = {"-tint", "--tint"}, description = "gives a green/purple tint to image", defaultValue = "0.0") double tint,
                  @Option(names = {"-sat", "--saturation"}, description = "saturation value", defaultValue = "0.0") double saturation,
                  @Option(names = {"--hue-adjustment", "-ha"}, description = "individual color adjustments", defaultValue = "dummy=(0,0,0)") HashMap<String, HSVVector> hueAdjustments,
                  @Option(names = {"--tone-adjustments", "-ta"}, description = "make color adjustments based on the tone", defaultValue = "dummy=(0,0,0)") HashMap<String, HSVVector> toneAdjustments,
                  @ArgGroup(exclusive = false) Composition overConfig,
                  @Option(names = {"-scale"}, description = "the scale value(s) for x and y respectively", defaultValue = "dummy=0") HashMap<String, Double> scales) {
    try {
      // starts the editor
      ImageEditor editor = new ImageEditor(
              imgs,
              outName,
              lightMultiplier,
              highlightsMult,
              shadowMult,
              contrast,
              gamma,
              grayscale,
              invert,
              sepia,
              temp,
              tint,
              saturation,
              hueAdjustments,
              toneAdjustments,
              overConfig,
              scales);
      editor.edit();

      // UI messages
      System.out.println(Ansi.AUTO.string("@|bold,green Image Processed!|@"));
    } catch (IOException e) {
      System.out.println(Ansi.AUTO.string("@|bold,red fatal: File Issue|@"));
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      System.out.println(Ansi.AUTO.string("@|bold,red fatal: Invalid Args|@"));
      e.printStackTrace();
    }
  }
}