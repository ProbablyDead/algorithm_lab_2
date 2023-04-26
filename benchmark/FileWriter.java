package benchmark;

import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {

  private PrintWriter printWriter;
  private static final String parameters = String.format("%-30s %-10s %-10s", "Points and rectangles count", "Time", "Units");

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

  public void write (int currentNum, long data) {
    printWriter.printf("%-30d %-10d %-10s.\n", currentNum, data, "ns."); 
  }

  public void write (String string) {
    printWriter.println(string);
  }

  public void close () {
    printWriter.close();
  }
}
