package io.github.gargparthb;

import picocli.CommandLine;

import java.lang.reflect.Method;

public class Main {
  public static void main(String[] args) {
    Method exec = CommandLine.getCommandMethods(Main.class, "run").get(0);
    CommandLine cmd = new CommandLine(exec);
    cmd.execute(args);
  }

  @CommandLine.Command(description = "")
  public void run(@CommandLine.Option(names = "-m") String msg) {
    System.out.println(msg);
  }
}
