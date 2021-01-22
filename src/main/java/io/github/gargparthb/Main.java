package io.github.gargparthb;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Help.Ansi;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

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
  public void run(@Parameters(paramLabel = "main image") File img,
                  @Option(names = {"-b", "--brightness"}, description = "light multiplier", defaultValue = "0.0") double b) {
    try {
      // starts the editor
      ImageEditor editor = new ImageEditor(img, b);
      editor.edit();

      System.out.println(Ansi.AUTO.string("@|bold,green Image Processed!|@"));
    } catch (IOException e) {
      System.out.println(Ansi.AUTO.string("@|bold,red fatal: Invalid Path|@"));
    }
  }
}
