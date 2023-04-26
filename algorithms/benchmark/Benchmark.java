package algorithms.benchmark;

import java.util.Scanner;

import algorithms.solution.FirstAlgorithm;
import algorithms.solution.SecondAlgorithm;
import algorithms.solution.ThirdAlgorithm;

public class Benchmark {

  public static void main (String[] args) {
    Generator generator = new Generator();
    
    String data = generator.generateRectanglesString(5) + generator.generatePointsString(5);

    System.out.println(FirstAlgorithm.firstAlgorithmMain(new Scanner(data)));
    System.out.println(SecondAlgorithm.secondAlgorithmMain(new Scanner(data)));
    System.out.println(ThirdAlgorithm.thirdAlgorithmMain(new Scanner(data)));
  }
}
