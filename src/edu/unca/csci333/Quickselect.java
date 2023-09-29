/* Hayden Holbrook
 * CSCI-333 Fall 2023
 * HW 04
 */
package edu.unca.csci333;

import java.util.Arrays;
import java.util.Random;

/**
 * An implementation of the Quickselect algorithm
 *
 * @author Hayden Holbrook <hholbroo@unca.edu>
 */
public class Quickselect<T extends Comparable<T>> {
    T[] arr;

    /**
     * Constructor for Quickselect
     *
     * @param arr array to be sorted
     */
    public Quickselect(T[] arr) {
        this.arr = Arrays.copyOf(arr, arr.length);
    }

    /**
     * Partitions the sub-array based on start and end indexes
     *
     * @param arr        array to be partitioned
     * @param start      starting index of the partition
     * @param end        ending index of the partition
     * @param pivotIndex index of the pivot element
     */
    private int partition(T[] arr, int start, int end, int pivotIndex) {
        T pivotElem = arr[pivotIndex];
        swap(arr, pivotIndex, end);
        int i = start;

        for (int j = start; j < end; j++) {
            if (arr[j].compareTo(pivotElem) <= 0) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, end);
        return i;
    }

    /**
     * Swaps two elements in an array
     *
     * @param arr array to be modified
     * @param i   index of the first element
     * @param j   index of the second element
     */
    private void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Returns the order statistic of the internal array
     *
     * @param orderStatistic order statistic to be found
     * @return the order statistic
     */
    public T randomizedQuickSelect(int orderStatistic) {
        T[] copy = Arrays.copyOf(arr, arr.length);
        return randomizedQuickSelect(copy, 0, copy.length - 1, orderStatistic);
    }

    /**
     * Returns the order statistic of the given array
     *
     * @param arr            array to be sorted
     * @param lowerBound     lower bound of the array
     * @param upperBound     upper bound of the array
     * @param orderStatistic order statistic to be found
     * @return the order statistic if found, null if there are invalid inputs
     */
    private T randomizedQuickSelect(T[] arr, int lowerBound, int upperBound, int orderStatistic) {
        if (arr.length == 0 || orderStatistic < 1) { // bad inputs
            return null;
        } else if (orderStatistic > arr.length) {
            return null;
        } else if (lowerBound < 0 || lowerBound > arr.length - 1) {
            return null;
        } else if (upperBound < 0 || lowerBound > arr.length - 1) {
           return null;
        }

        if (lowerBound == upperBound) { // base case, we found it
            return arr[lowerBound];
        }

        Random rand = new Random();
        int pivotIndex = rand.nextInt(upperBound - lowerBound) + lowerBound;

        int q = partition(arr, lowerBound, upperBound, pivotIndex);
        int k = q - lowerBound + 1;

        if (orderStatistic == k) { // we found it
            return arr[q];
        } else if (orderStatistic < k) { // look in the left partition
            return randomizedQuickSelect(arr, lowerBound, q - 1, orderStatistic);
        } else { // look in the right partition
            return randomizedQuickSelect(arr, q + 1, upperBound, orderStatistic - k);
        }
    }
}
