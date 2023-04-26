package algorithms.solution;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class SecondAlgorithm {

  public static long secondAlgorithmMain (Scanner scanner) {

    long start = System.nanoTime();

    loadRectangles(scanner);
    createMap();
    loadNcheckPoints(scanner);

    scanner.close();

    return System.nanoTime() - start;
  }

  private static int getNumberOfJunctions (Point point) {
    if (point.x < Rectangle.compressedX.get(0) || point.y < Rectangle.compressedY.get(0)) {
      return 0;
    }
    if (point.x > Rectangle.compressedX.get(Rectangle.compressedX.size() - 1)
        || (point.y > Rectangle.compressedY.get(Rectangle.compressedY.size() - 1))) {
      return 0;
    }
    return Rectangle.Map[Point.binarySearch(Rectangle.compressedX, point.x)][Point.binarySearch(Rectangle.compressedY, point.y)];
  }

  private static void loadNcheckPoints (Scanner scanner) {
    int count = scanner.nextInt();
    for (int i = 0; i < count; i++) {
       // System.out.print(
           getNumberOfJunctions(new Point(scanner.nextInt(), scanner.nextInt()));
           // + " ");
    }
    // System.out.println();
  }

  private static void loadRectangles (Scanner scanner) {

    Rectangle.rectangles = new Rectangle[scanner.nextInt()];

    for (int i = 0; i < Rectangle.rectangles.length; i++) {
      Rectangle.rectangles[i] = new Rectangle(scanner.nextInt(), scanner.nextInt(),
          scanner.nextInt(), scanner.nextInt());

      Rectangle.addUniqueValue(Rectangle.compressedX, Rectangle.rectangles[i].leftBottomPoint.x);
      Rectangle.addUniqueValue(Rectangle.compressedY, Rectangle.rectangles[i].leftBottomPoint.y);
      Rectangle.addUniqueValue(Rectangle.compressedX, Rectangle.rectangles[i].rightUpperPoint.x);
      Rectangle.addUniqueValue(Rectangle.compressedY, Rectangle.rectangles[i].rightUpperPoint.y);
      Rectangle.addUniqueValue(Rectangle.compressedX, Rectangle.rectangles[i].rightUpperPoint.x + 1);
      Rectangle.addUniqueValue(Rectangle.compressedY, Rectangle.rectangles[i].rightUpperPoint.y + 1);
      
    }

    Collections.sort(Rectangle.compressedX);
    Collections.sort(Rectangle.compressedY);
  }

  private static void createMap () {
    Rectangle.Map = new int[Rectangle.compressedX.size()][Rectangle.compressedY.size()];

    for (int[] row : Rectangle.Map) {
      Arrays.fill(row, 0);
    }

    for (Rectangle rectangle : Rectangle.rectangles) {
      for (int i = Rectangle.compressedX.indexOf(rectangle.leftBottomPoint.x); 
          i <= Rectangle.compressedX.indexOf(rectangle.rightUpperPoint.x); i++) {
        for (int j = Rectangle.compressedY.indexOf(rectangle.leftBottomPoint.y); 
            j <= Rectangle.compressedY.indexOf(rectangle.rightUpperPoint.y); j++) {
          Rectangle.Map[i][j]++;
        }
      }
    }

  }

}
