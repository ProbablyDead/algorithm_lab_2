package forContest.thirdAlgorithm;

import java.util.*;

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

    private static void loadNcheckPoints (Scanner scanner) {
        int count = scanner.nextInt();
        for (int i = 0; i < count;i++) {
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
}

class Node {
    static HashMap<Integer, Node> roots = new HashMap<>();
    Node leftChild;
    Node rightChild;

    int startIndex;
    int endIndex;

    int value;
    int delta;

    Node (Node node) {
        leftChild = node.leftChild;
        rightChild = node.rightChild;
        startIndex = node.startIndex;
        endIndex = node.endIndex;
        value = node.value;
        delta = node.delta;
    }

    Node (int startIndex, int endIndex) {
        leftChild = null;
        rightChild = null;
        delta = 0;
        this.value = 0;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public static void updateWithSides (ArrayList<Rectangle.Side> sides) {
        int previousSide = -1;

        for (Rectangle.Side i : sides) {
            roots.put(i.x, updateWithSide(roots.get(previousSide), i.y1, i.y2, i.openORclose ? 1 : -1));
            previousSide = i.x;
        }
    }

    private boolean isAnyChild () {
        return this.leftChild != null || this.rightChild != null;
    }

    private static Node updateWithSide (Node root, int startIndex, int endIndex, int value) {
        if (startIndex > root.endIndex || root.startIndex > endIndex) {
            return root;
        }

        Node clone = new Node(root);

        if (startIndex <= root.startIndex && root.endIndex <=endIndex) {
            if (clone.isAnyChild()) {
                clone.delta += value;
            }
            else {
                clone.value += value;
            }
        }
        else {
            clone.leftChild = updateWithSide(root.leftChild, startIndex, endIndex, value);
            clone.rightChild = updateWithSide(root.rightChild, startIndex, endIndex, value);
        }

        return clone;
    }

    static Node buildEmptySegmentTree (int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            return new Node(startIndex, endIndex);
        }

        int midIndex = (startIndex + endIndex) / 2;
        Node left = buildEmptySegmentTree(startIndex, midIndex);
        Node right = buildEmptySegmentTree(midIndex + 1, endIndex);
        Node root = new Node(left.startIndex, right.endIndex);
        root.leftChild = left;
        root.rightChild = right;

        return root;
    }

    public int getResult (int yIndex, int sum) {
        if (!this.isAnyChild()) {
            return sum + this.value;
        }
        else {
            assert this.leftChild != null;
            if (yIndex >= this.leftChild.startIndex && yIndex <= this.leftChild.endIndex) {
                sum += this.leftChild.getResult(yIndex, sum);
            }
            else {
                sum += this.rightChild.getResult(yIndex, sum);
            }
        }
        return sum + this.delta;
    }

}

class Point {
    int x;
    int y;

    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int binarySearch (ArrayList<Integer> array, int key) {
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

        return left;
    }

    public int getNumberOfJunctions () {
        if (x < Rectangle.compressedX.get(0) || y < Rectangle.compressedY.get(0)) {
            return 0;
        }
        if (x > Rectangle.compressedX.get(Rectangle.compressedX.size() - 1)
                || (y > Rectangle.compressedY.get(Rectangle.compressedY.size() - 1))) {
            return 0;
        }

        int compX = binarySearch(Rectangle.compressedX, this.x);
        int compY = binarySearch(Rectangle.compressedY, this.y);

        ArrayList<Integer> setOfKeysAsList = new ArrayList<>(Node.roots.keySet());
        int tree = setOfKeysAsList.get(binarySearch(setOfKeysAsList, compX));
        return Node.roots.get(tree).getResult(compY, 0);
    }

}

class Rectangle {
    static ArrayList<Integer> compressedX = new ArrayList<>();
    static ArrayList<Integer> compressedY = new ArrayList<>();
    static Rectangle[] rectangles;
    Point leftBottomPoint;
    Point rightUpperPoint;

    public static int fillToRowerOfTwo (ArrayList<Integer> array) {
        return Math.max(1, Integer.highestOneBit(array.size() - 1) << 1);
    }

    public static void addUniqueValue (ArrayList<Integer> array, int value) {
        if (!array.contains(value)) {
            array.add(value);
        }
    }

    Rectangle (int x1, int y1, int x2, int y2) {
        leftBottomPoint = new Point(x1, y1);
        rightUpperPoint = new Point(x2, y2);
    }

    static class Side {
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
                sides.add(new Side(rectangle.leftBottomPoint.x, rectangle.leftBottomPoint.y, rectangle.rightUpperPoint.y, true));
                sides.add(new Side(rectangle.rightUpperPoint.x + 1, rectangle.leftBottomPoint.y, rectangle.rightUpperPoint.y, false));
            }

            sides.sort(Comparator.comparingInt(o -> o.x));
        }
    }

}

