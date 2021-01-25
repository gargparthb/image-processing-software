package io.github.gargparthb;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Help.Ansi;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;

public class Main {
  public static void main(String[] args) {
    // points cmd args to the run method
    Method run = CommandLine.getCommandMethods(Main.class, "run").get(0);
    CommandLine cmd = new CommandLine(run);
    cmd.execute(args);
  }

  // starts the ImageEditor class
  @Command(name = "edit",
          description = "edit the given image(s)",
          version = "1.0.0",
          mixinStandardHelpOptions = true)
  public void run(@Parameters(paramLabel = "main image") Path img,
                  @Option(names = {"-o", "--out"}, description = "out path", defaultValue = "") String outName,
                  @Option(names = {"-b", "--brightness"}, description = "light multiplier between -1.0 and 1.0", defaultValue = "0.0") double lightMultiplier,
                  @Option(names = {"-hl", "--highlights"}, description = "light multiplier for the lighter parts of image", defaultValue = "0.0") double highlightsMult) {
    try {

      // starts the editor
      ImageEditor editor = new ImageEditor(img, outName, lightMultiplier, highlightsMult);
      editor.edit();

      // UI messages
      System.out.println(Ansi.AUTO.string("@|bold,green Image Processed!|@"));
    } catch (IOException e) {
      System.out.println(Ansi.AUTO.string("@|bold,red fatal: File Issue|@"));
    } catch (IllegalArgumentException e) {
      System.out.println(Ansi.AUTO.string("@|bold,red fatal: Invalid Args|@"));
    }
  }
}
