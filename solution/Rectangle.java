import java.util.ArrayList;
import java.util.Collections;

public class Rectangle {
  public static int[][] Map;
  public static ArrayList<Integer> compressedX = new ArrayList<>();
  public static ArrayList<Integer> compressedY = new ArrayList<>();
  public static Rectangle[] rectangles;
  public Point leftBottomPoint;
  public Point rightUpperPoint;

  public static int fillToRowerOfTwo (ArrayList<Integer> array) {
    return Math.max(1, Integer.highestOneBit(array.size() - 1) << 1);
  }

  public static void addUniqueValue (ArrayList<Integer> array, int value) {
    if (!array.contains(value)) {
      array.add(value);
    }
  }

  public Rectangle (int x1, int y1, int x2, int y2) {
    leftBottomPoint = new Point(x1, y1);
    rightUpperPoint = new Point(x2, y2);
  }

  public boolean isBelong (Point point) {
    return (point.x >= leftBottomPoint.x) && (point.x <= rightUpperPoint.x) && (point.y >= leftBottomPoint.y) && (point.y <= rightUpperPoint.y);  
  }

  public class Side {
    public static ArrayList<Side> sides = new ArrayList<>(); 
    int x;
    int y1;
    int y2;
    boolean openORclose;

    Side (int x, int y1, int y2, boolean openORclose) {
      this.x = Point.binarySearch(compressedX, x);
      this.y1 = Point.binarySearch(compressedY, y1);
      this.y2 = Point.binarySearch(compressedY, y2);
      this.openORclose = openORclose;
    }
      
    public static void fillSides () {

      for (Rectangle rectangle : rectangles) {
        sides.add(rectangle.new Side(rectangle.leftBottomPoint.x, rectangle.leftBottomPoint.y, rectangle.rightUpperPoint.y, true));
        sides.add(rectangle.new Side(rectangle.rightUpperPoint.x + 1, rectangle.leftBottomPoint.y, rectangle.rightUpperPoint.y, false));
      }

      Collections.sort(sides, (o1, o2) -> Integer.compare(o1.x, o2.x));
    }

  }
}
