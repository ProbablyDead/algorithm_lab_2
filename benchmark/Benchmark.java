package benchmark;

import algorithms.solution.FirstAlgorithm;
import algorithms.solution.SecondAlgorithm;
import algorithms.solution.ThirdAlgorithm;

import javafx.util.Pair;
import java.util.Scanner;

public class Benchmark {
  private static final int maxNumber = 150000;
  private static final int maxNumberForMap = 2000;
  private static final int startI = 1;
  private static final int step = 2;

  public static void main (String[] args) {
    Generator generator = new Generator();
    ProgressBar progressBar = new ProgressBar();
    FileWriter bruteForseWriter = new FileWriter("results/bruteForseResult.txt", "Brute force results\n");
    FileWriter mapWriter = new FileWriter("results/mapResult.txt", "Map results\n");
    FileWriter treeWriter = new FileWriter("results/treeResult.txt", "Persistent tree results\n");

    long start, end;
    Pair<Long, Long> results;

    for (int i = startI; i <= maxNumber; i *= step) {
      System.out.print(progressBar.progressBarString(i, maxNumber));
      
      String dataString = generator.generateRectanglesString(i) + generator.generatePointsString(i);

      start = System.nanoTime();
      results = FirstAlgorithm.firstAlgorithmMain(new Scanner(dataString));
      end = System.nanoTime() - start;
      bruteForseWriter.write(i, results, end); 
      
      if (i <= maxNumberForMap) {
        start = System.nanoTime();
        results = SecondAlgorithm.secondAlgorithmMain(new Scanner(dataString));
        end = System.nanoTime() - start;
        mapWriter.write(i, results, end);
      }

      start = System.nanoTime();
      results = ThirdAlgorithm.thirdAlgorithmMain(new Scanner(dataString));
      end = System.nanoTime() - start;
      treeWriter.write(i, results, end);
    }

    System.out.println(progressBar.progressBarString(maxNumber, maxNumber) + "\n");
    
    bruteForseWriter.close();
    mapWriter.close();
    treeWriter.close();
  }

}
