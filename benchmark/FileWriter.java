package benchmark;

import java.io.IOException;
import java.io.PrintWriter;

import javafx.util.Pair;

public class FileWriter {

  private PrintWriter printWriter;
  private static final String format = "%-30s | %-13s | %-13s | %-13s | %s\n";
  private static final String parameters = String.format(format + "-".repeat(86), 
      "Points and rectangles count", "Build time", "Request time", "Total time", "Units");

  FileWriter (String path) {
    try {
      printWriter = new PrintWriter(path); 
    }
    catch (IOException ex) {
      System.out.println("Cannot locate the file");
      return;
    }
  }

  FileWriter (String path, String firstString) {
    this(path);
    this.write(firstString);
    this.write(parameters);
  }

  public void write (int currentNum, Pair<Long, Long> data, long wholeTime) {
    printWriter.printf(format, currentNum, data.getKey(), data.getValue(), wholeTime, "ns."); 
  }

  public void write (String string) {
    printWriter.println(string);
  }

  public void close () {
    printWriter.close();
  }
}
