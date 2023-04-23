package forContest.secondAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class secondAlgorithm {

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);

        loadRectangles(scanner);
        createMap();
        loadNcheckPoints(scanner);

        scanner.close();
    }

    private static void loadNcheckPoints (Scanner scanner) {
        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            System.out.print(new Point(scanner.nextInt(), scanner.nextInt()).getNumberOfJunctions() + " ");
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

class Point {
    int x;
    int y;

    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    private static int binarySearch (ArrayList<Integer> array, int key) {
        int left = 0;
        int right = array.size() - 1;

        while (left + 1 != right) {
            int mid = (right + left) / 2;
            if (key < array.get(mid)) {
                right = mid;
            }
            else {
                left = mid;
            }
        }

        return left + 1;
    }

    public int getNumberOfJunctions () {
        if (x < Rectangle.compressedX.get(0) || y < Rectangle.compressedY.get(0)) {
            return 0;
        }
        if (x > Rectangle.compressedX.get(Rectangle.compressedX.size() - 1)
                || (y > Rectangle.compressedY.get(Rectangle.compressedY.size() - 1))) {
            return 0;
        }
        return Rectangle.Map[binarySearch(Rectangle.compressedX, this.x) - 1][binarySearch(Rectangle.compressedY, this.y) - 1];
    }

}

class Rectangle {
    static ArrayList<Integer> compressedX = new ArrayList<>();
    static ArrayList<Integer> compressedY = new ArrayList<>();
    static int[][] Map;
    static Rectangle[] rectangles;
    Point leftBottomPoint;
    Point rightUpperPoint;

    public static void addUniqueValue (ArrayList<Integer> array, int value) {
        if (!array.contains(value)) {
            array.add(value);
        }
    }

    Rectangle (int x1, int y1, int x2, int y2) {
        leftBottomPoint = new Point(x1, y1);
        rightUpperPoint = new Point(x2, y2);
    }

}

