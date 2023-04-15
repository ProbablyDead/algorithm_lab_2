import java.util.Scanner;

public class Main {

  static Rectangle[] rectangles; 
  static Point[] points;
  static int ARRAY_SIZE;
  static int POINT_COUNT;

  private class Point {
    int x;
    int y;
  
    Point (int x, int y) {
      this.x = x;
      this.y = y;
    }
  
  }

  private class Rectangle {
    int x1;
    int y1;
    int x2;
    int y2;
  
    Rectangle (int x1, int y1, int x2, int y2) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
    }
  
    public boolean isBelong (Point point) {
      return (point.x <= x2) && (point.x >= x1) && (point.y <= y2) && (point.y >= y1);  
    }
  
    @Override
    public String toString () {
      return String.format("%d %d %d %d", x1, y1, x2, y2);
    }
  }
  
  public static void load (Scanner scanner) {
    Main main = new Main();

    ARRAY_SIZE = scanner.nextInt(); 

    rectangles = new Rectangle[ARRAY_SIZE];

    for (int i = 0; i < ARRAY_SIZE; ++i) {
      rectangles[i] = main.new Rectangle(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
    }


    int POINT_COUNT = scanner.nextInt();

    points = new Point[POINT_COUNT];

    for (int i = 0; i < POINT_COUNT; ++i) {
      points[i] = main.new Point(scanner.nextInt(), scanner.nextInt());
    }

    scanner.close();
  }


  public static void firstAlgorithm () {
    for (Point point : points) {
      int count = 0;
      for (Rectangle rectangle : rectangles) {
        if (rectangle.isBelong(point)) ++count;
      }

      System.out.print(count + " ");
    }

    System.out.println();
  }

  public static void main (String[] args) {
    load(new Scanner(System.in));

    firstAlgorithm();
  }

}
