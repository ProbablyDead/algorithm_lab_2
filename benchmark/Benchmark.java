package benchmark;

import java.util.Scanner;

import algorithms.solution.FirstAlgorithm;
import algorithms.solution.SecondAlgorithm;
import algorithms.solution.ThirdAlgorithm;

public class Benchmark {
  private static final int maxNumber = 150000;
  private static final int maxNumberForMap = 500;
  private static final int startI = 1;
  private static final int step = 2;

  public static void main (String[] args) {
    Generator generator = new Generator();
    ProgressBar progressBar = new ProgressBar();
    FileWriter bruteForseWriter = new FileWriter("results/bruteForseResult.txt", "Brute force results");
    FileWriter mapWriter = new FileWriter("results/mapResult.txt", "Map results");
    FileWriter treeWriter = new FileWriter("results/treeResult.txt", "Persistent tree results");

    for (int i = startI; i <= maxNumber; i *= step) {
      System.out.print(progressBar.progressBarString(i, maxNumber));
      
      String dataString = generator.generateRectanglesString(i) + generator.generatePointsString(i);

      bruteForseWriter.write(i, FirstAlgorithm.firstAlgorithmMain(new Scanner(dataString))); 
      
      if (i <= maxNumberForMap) {
        mapWriter.write(i, SecondAlgorithm.secondAlgorithmMain(new Scanner(dataString)));
      }

      treeWriter.write(i, ThirdAlgorithm.thirdAlgorithmMain(new Scanner(dataString)));
    }

    System.out.println(progressBar.progressBarString(maxNumber, maxNumber) + "\n");
    

    bruteForseWriter.close();
    mapWriter.close();
    treeWriter.close();
  }

}
