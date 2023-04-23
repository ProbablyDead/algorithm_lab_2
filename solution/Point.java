import java.util.ArrayList;

public class Point {
  public int x;
  public int y;

  public Point (int x, int y) {
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

}


