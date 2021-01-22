package io.github.gargparthb;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.lang.reflect.Method;

public class Main {
  public static void main(String[] args) {
    Method run = CommandLine.getCommandMethods(Main.class, "run").get(0);
    CommandLine cmd = new CommandLine(run);
    cmd.execute(args);
  }

  @Command(name = "edit",
          description = "edit the given image(s)",
          version = "1.0.0",
          mixinStandardHelpOptions = true)
  public void run(@Parameters(paramLabel = "main image") File img) {

  }
}
