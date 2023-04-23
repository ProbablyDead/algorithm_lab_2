import java.util.Scanner;

public class firstAlgorithm {

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
      System.out.print(count + " ");
    }
  }

  public static void main (String[] args) {
    Scanner scanner = new Scanner(System.in);

    loadRectangles(scanner);
    loadPoints(scanner);

    System.out.println();
    scanner.close();
  }

}

