package solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class thirdAlgorithm {

  public static void main (String[] args) {
    Scanner scanner = new Scanner(System.in); 

    loadRectangles(scanner);
    Node root = Node.buildEmptySegmentTree(0, Rectangle.fillToRowerOfTwo(Rectangle.compressedY) - 1);
    Node.roots.put(-1, root);

    Rectangle.Side.fillSides();
    Node.updateWithSides(Rectangle.Side.sides);

    loadNcheckPoints(scanner);
    scanner.close();
  }

  private static int getNumberOfJunctions (Point point) {
    if (point.x < Rectangle.compressedX.get(0) || point.y < Rectangle.compressedY.get(0)) {
      return 0;
    }
    if (point.x > Rectangle.compressedX.get(Rectangle.compressedX.size() - 1)
        || (point.y > Rectangle.compressedY.get(Rectangle.compressedY.size() - 1))) {
      return 0;
    }

    int compX = Point.binarySearch(Rectangle.compressedX, point.x);
    int compY = Point.binarySearch(Rectangle.compressedY, point.y);

    ArrayList<Integer> setOfKeysAsList = new ArrayList<>(Node.roots.keySet());
    int tree = setOfKeysAsList.get(Point.binarySearch(setOfKeysAsList, compX));
    return Node.roots.get(tree).getResult(compY, 0);
  }

  private static void loadNcheckPoints (Scanner scanner) {
    int count = scanner.nextInt();
    for (int i = 0; i < count;i++) {
       System.out.print(getNumberOfJunctions(new Point(scanner.nextInt(), scanner.nextInt())) + " ");
    }
    System.out.println();
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
}


