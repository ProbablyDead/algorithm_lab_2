import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class secondAlgorithm {

  private static void loadPoints (Scanner scanner) {
    Point.NUMBER_OF_POINTS = scanner.nextInt();

    Point.points = new Point[Point.NUMBER_OF_POINTS];

    for (int i = 0; i < Point.NUMBER_OF_POINTS; i++) {
      Point.points[i] = new Point(scanner.nextInt(), scanner.nextInt());
    }
  }

  private static void loadRectangles (Scanner scanner) {
    Rectangle.NUMBER_OF_RECTANGLES = scanner.nextInt();

    Rectangle.rectangles = new Rectangle[Rectangle.NUMBER_OF_RECTANGLES];

    for (int i = 0; i < Rectangle.NUMBER_OF_RECTANGLES; i++) {
      Rectangle.rectangles[i] = new Rectangle(scanner.nextInt(), scanner.nextInt(),
          scanner.nextInt(), scanner.nextInt());

      Rectangle.addUniqueRectangle(Rectangle.compressedX, Rectangle.rectangles[i].leftBottomPoint.x);
      Rectangle.addUniqueRectangle(Rectangle.compressedY, Rectangle.rectangles[i].leftBottomPoint.y);
      Rectangle.addUniqueRectangle(Rectangle.compressedX, Rectangle.rectangles[i].rightUpperPoint.x);
      Rectangle.addUniqueRectangle(Rectangle.compressedY, Rectangle.rectangles[i].rightUpperPoint.y);
      Rectangle.addUniqueRectangle(Rectangle.compressedX, Rectangle.rectangles[i].rightUpperPoint.x + 1);
      Rectangle.addUniqueRectangle(Rectangle.compressedY, Rectangle.rectangles[i].rightUpperPoint.y + 1);
      
    }

    Collections.sort(Rectangle.compressedX);
    Collections.sort(Rectangle.compressedY);
  }

  private static void createMap () {
    Rectangle.Map = new int[Rectangle.compressedX.size()][Rectangle.compressedY.size()];

    for (int[] row : Rectangle.Map) {
      Arrays.fill(row, 0);
    }

    System.out.println(Rectangle.compressedX);
    System.out.println(Rectangle.compressedY);
    System.out.println();

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

  public static void main (String args[]) {
    Scanner scanner = new Scanner(System.in); 

    loadRectangles(scanner);
    createMap();
    loadPoints(scanner);

    scanner.close();
  }
}

class Point {
  static int NUMBER_OF_POINTS = 0;
  static Point[] points;
  int x;
  int y;

  Point (int x, int y) {
    this.x = x;
    this.y = y;
  }

}

class Rectangle {
  static ArrayList<Integer> compressedX = new ArrayList<>();
  static ArrayList<Integer> compressedY = new ArrayList<>();
  static int[][] Map;
  static Rectangle[] rectangles;
  Point leftBottomPoint;
  Point rightUpperPoint;
  static int NUMBER_OF_RECTANGLES = 0;

  public static void addUniqueRectangle (ArrayList<Integer> array, int value) {
    if (!array.contains(value)) {
      array.add(value);
    }
  }

  Rectangle (int x1, int y1, int x2, int y2) {
    leftBottomPoint = new Point(x1, y1);
    rightUpperPoint = new Point(x2, y2);
  }

}

