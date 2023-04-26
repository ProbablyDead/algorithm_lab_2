package benchmark;

public class ProgressBar {
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_BLACK = "\u001B[30m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_YELLOW = "\u001B[33m";
  private static final String ANSI_BLUE = "\u001B[34m";
  private static final String ANSI_PURPLE = "\u001B[35m";
  private static final String ANSI_CYAN = "\u001B[36m";
  private static final String ANSI_WHITE = "\u001B[37m";

  ProgressBar () {
    System.out.println();
  }

  public String progressBarString (int curretNum, int maxNumber) {
    final int persent = (int) ((double) curretNum / (double) maxNumber * 100);
    return String.format("%s[%s%s]%s %s%d%%%s %s%d%s\r",
        ANSI_CYAN, "=".repeat(persent), " ".repeat(100 - persent), ANSI_RESET, ANSI_GREEN, persent, ANSI_RESET, ANSI_RED, curretNum, ANSI_RESET);
  }
}
