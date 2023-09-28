/* Hayden Holbrook
 * CSCI-333 Fall 2023
 * HW 04
 */
package edu.unca.csci333;

import java.util.Arrays;
import java.util.Random;

public class Main {

  private void countingSort(int[] input, int[] output, int largestValueInInput) {
    // TODO: Implement counting sort
  }

  public int[] countingSort(int[] input) {
    // TODO: Implement counting sort wrapper
    return new int[0];
  }

  /**
   * Randomly arranges the elements of the given array.
   *
   * @param array The array to mix up.
   */
  private static <T> void scrambleArray(T[] array) {
    Random rand = new Random();
    for (int i = 0; i < array.length; i++) {
      int randomIndexToSwap = rand.nextInt(array.length);
      T temp = array[randomIndexToSwap];
      array[randomIndexToSwap] = array[i];
      array[i] = temp;
    }
  }

  public static void main(String[] args) {
    Integer[] arr = {1, 2, 3, 4, 5};
    scrambleArray(arr);
    int orderStat = 3;

    System.out.println("Selecting order statistic " + orderStat + " from array:");
    System.out.println(Arrays.toString(arr));
    Quickselect<Integer> qs = new Quickselect<Integer>(arr);
    Integer result = qs.randomizedQuickSelect(orderStat);
    System.out.println("Result: " + result);
  }
}
