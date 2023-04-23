import java.util.ArrayList;
import java.util.HashMap;

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
