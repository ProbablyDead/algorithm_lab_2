package algorithms.solution;

import java.util.Scanner;

import javafx.util.Pair;

public class FirstAlgorithm {

  public static Pair<Long, Long> firstAlgorithmMain (Scanner scanner) {

    long start = System.nanoTime();

    loadRectangles(scanner);

    long finishedCreating = System.nanoTime() - start;
    start = System.nanoTime();
   
    loadPoints(scanner);

    // System.out.println();
    scanner.close();
    return new Pair<Long,Long>(finishedCreating, System.nanoTime() - start);
  }

  private static void loadRectangles (Scanner scanner) {

    Rectangle.rectangles = new Rectangle[scanner.nextInt()];
    for (int i = 0; i < Rectangle.rectangles.length; ++i) {
      Rectangle.rectangles[i] = new Rectangle(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
    }

  }
  
  private static void loadPoints (Scanner scanner) {
    int pointCount = scanner.nextInt();

    for (int i = 0; i < pointCount; ++i) {
      int count = 0;
      Point point = new Point(scanner.nextInt(), scanner.nextInt());
      for (Rectangle rectangle : Rectangle.rectangles) {
        if (rectangle.isBelong(point)) {
          ++count;    
        }
      }
      // System.out.print(count + " ");
    }
  }

}

