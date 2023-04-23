import java.util.Scanner;

public class firstAlgorithm {

  static Rectangle[] rectangles; 
  static Point[] points;
  static int ARRAY_SIZE;
  static int POINT_COUNT;

  public static void loadRectangles (Scanner scanner) {

    ARRAY_SIZE = scanner.nextInt(); 

    rectangles = new Rectangle[ARRAY_SIZE];

    for (int i = 0; i < ARRAY_SIZE; ++i) {
      rectangles[i] = new Rectangle(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
    }

  }
  
  public static void loadPoints (Scanner scanner) {

    int POINT_COUNT = scanner.nextInt();

    points = new Point[POINT_COUNT];

    for (int i = 0; i < POINT_COUNT; ++i) {
      points[i] = new Point(scanner.nextInt(), scanner.nextInt());
    }

    scanner.close();
  }

  public static void main (String[] args) {
    Scanner scanner = new Scanner(System.in);

    loadRectangles(scanner);
    loadPoints(scanner);

    for (Point point : points) {
      int count = 0;
      for (Rectangle rectangle : rectangles) {
        if (rectangle.isBelong(point)) ++count;
      }

      System.out.print(count + " ");
    }

    System.out.println();
    scanner.close();
  }

}

class Point {
  int x;
  int y;

  Point (int x, int y) {
    this.x = x;
    this.y = y;
  }

}

 class Rectangle {
  Point leftBottomPoint;
  Point rightUpperPoint;

  Rectangle (int x1, int y1, int x2, int y2) {
    leftBottomPoint = new Point(x1, y1);
    rightUpperPoint = new Point(x2, y2);
  }

  public boolean isBelong (Point point) {
    return (point.x >= leftBottomPoint.x) && (point.x <= rightUpperPoint.x) && (point.y >= leftBottomPoint.y) && (point.y <= rightUpperPoint.y);  
  }

}

