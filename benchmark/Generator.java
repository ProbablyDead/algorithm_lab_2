package benchmark;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Generator {
  private final static int minNumber = (int) Math.pow(10, 6) + 1;
  private final static int maxNumber = (int) Math.pow(10, 7);
  private static List<Integer> primeNumbers = null; 

  Generator () {
    if (primeNumbers == null) {
      primeNumbers = primeNumbersBruteForce(); 
    }
  }

  public String generateRectanglesString (int count) {
    String resultString = Integer.toString(count) + "\n";
    for (int i = 0; i < count; ++i) {
      resultString += String.format("%d %d %d %d\n", 10*i, 10*i, 10*(2*count - i), 10*(2*count - i));
    }
    return resultString;
  }

  public String generatePointsString (int count) {
    String resultString = Integer.toString(count) + "\n";

    for (int i = 0; i < count; ++i) {
      resultString += String.format("%d %d\n", getRandomCoordinate(i), getRandomCoordinate(i));
    }

    return resultString;
  }

  private int getRandomCoordinate (int curretNum) {
    Random random = new Random();
    int p = primeNumbers.get(random.nextInt(primeNumbers.size()));
    return (int) Math.pow(p * curretNum, 31) % (20*maxNumber);
  }

  private static List<Integer> primeNumbersBruteForce() {
    List<Integer> primeNumbers = new LinkedList<>();

    for (int i = minNumber; i <= maxNumber; i += 2) {
      if (isPrimeBruteForce(i)) {
        primeNumbers.add(i);
      }
    }

    return primeNumbers;
  }

  private static boolean isPrimeBruteForce(int number) {
    for (int i = 2; i*i <= number; i++) {
        if (number % i == 0) {
            return false;
        }
    }
    return true;
  }
}
