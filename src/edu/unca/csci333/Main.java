/* Hayden Holbrook
 * CSCI-333 Fall 2023
 * HW 04
 */
package edu.unca.csci333;

import java.util.Arrays;
import java.util.Random;

/**
 * Runs through some tests of Counting Sort and Quickselect
 * @author Hayden Holbrook <hholbroo@unca.edu>
 */
public class Main {

  /**
   * Sorts the given input using counting sort
   *
   * @param input array to be sorted
   * @param output sorted array
   * @param largestValueInInput largest value in input array
   */
  private static void countingSort(int[] input, int[] output, int largestValueInInput) {
    int[] counters = new int[largestValueInInput + 1];
      for (int j : input) {
          counters[j]++;
      }
    for (int i = 1; i <= largestValueInInput; i++) {
      counters[i] = counters[i] + counters[i - 1];
    }
    for (int i = input.length - 1; i >= 0; i--) {
      counters[input[i]]--;
      output[counters[input[i]]] = input[i];
    }
  }

  /**
   * Sorts the given array using Counting Sort
   *
   * @param input array to be sorted
   * @return sorted array
   */
  public static int[] countingSort(int[] input) {
    int largestValue = findLargestValue(input);
    int[] output = new int[input.length];
    countingSort(input, output, largestValue);
    return output;
  }

  /**
   * Finds the largest value in the given array
   *
   * @param input array to be searched
   * @return int largest value in given array
   */
  private static int findLargestValue(int[] input) {
    int lagestSoFar = Integer.MIN_VALUE;
      for (int j : input) {
          if (j > lagestSoFar)
              lagestSoFar = j;
      }
    return lagestSoFar;
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

  /**
   * Randomly arranges the elements of the given array.
   *
   * @param array The array to mix up.
   */
  private static void scrambleArray(int[] array) {
    Random rand = new Random();
    for (int i = 0; i < array.length; i++) {
      int randomIndexToSwap = rand.nextInt(array.length);
      int temp = array[randomIndexToSwap];
      array[randomIndexToSwap] = array[i];
      array[i] = temp;
    }
  }

  public static void main(String[] args) {
    Integer[] arr = {1, 2, 3, 4, 5};
    scrambleArray(arr);
    int orderStat = 3;

    int[] intArr = {1, 2, 3, 4, 5};
    scrambleArray(intArr);

    System.out.println("Selecting order statistic " + orderStat + " from array:");
    System.out.println(Arrays.toString(arr));
    Quickselect<Integer> qs = new Quickselect<Integer>(arr);
    Integer result = qs.randomizedQuickSelect(orderStat);
    System.out.println("Result: " + result);

    System.out.println();
    System.out.println("Sorting...");
    scrambleArray(arr);
    System.out.println("Before: " + Arrays.toString(arr));
    int[] sorted = countingSort(intArr);
    System.out.println("After: " + Arrays.toString(sorted));
  }
}
