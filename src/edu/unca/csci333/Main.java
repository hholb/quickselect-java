/* Hayden Holbrook
 * CSCI-333 Fall 2023
 * HW 04
 */
package edu.unca.csci333;

import java.util.Arrays;
import java.util.Random;

/**
 * Runs through some tests of Counting Sort and Quickselect
 *
 * @author Hayden Holbrook <hholbroo@unca.edu>
 */
public class Main {
    public static final int NUM_TESTS = 5;
    public static final int ARRAY_MAX_LENGTH = 50;
    public static final String[] ALPHABET = {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
        };

    /**
     * Sorts the given array using Counting Sort
     *
     * @param input array to be sorted
     * @return sorted array
     */
    public static int[] countingSort(int[] input) {
        if (input.length == 0) {
            return new int[0];
        }

        int largestValue = findLargestValue(input);
        int[] output = new int[input.length];
        countingSort(input, output, largestValue);
        return output;
    }

    /**
     * Sorts the given input array using counting sort
     *
     * @param input               array to be sorted
     * @param output              sorted array
     * @param largestValueInInput largest value in input array
     */
    private static void countingSort(int[] input, int[] output, int largestValueInInput) {
        int[] counters = new int[largestValueInInput + 1];
        for (int j : input) { // count up the values
            counters[j]++;
        }
        for (int i = 1; i <= largestValueInInput; i++) { // determine correct positions
            counters[i] = counters[i] + counters[i - 1];
        }
        for (int i = input.length - 1; i >= 0; i--) { // fill output array
            counters[input[i]]--;
            output[counters[input[i]]] = input[i];
        }
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
     * Creates some arrays and runs through some tests of Quickselect and Counting Sort
     * 
     * @param args
     */
    public static void main(String[] args) {
        Random rand = new Random();
        int orderStatistic;

        for (int i = 1; i <= NUM_TESTS; i++) {
            System.out.printf("==================== TEST %d ====================\n\n", i);
            Integer[] integers = new Integer[rand.nextInt(ARRAY_MAX_LENGTH) + 1];
            for (int j = 0; j < integers.length; j++) {
                integers[j] = rand.nextInt(100);
            }

            String[] strings = new String[rand.nextInt(ARRAY_MAX_LENGTH) + 1];
            for (int k = 0; k < strings.length; k++) {
                strings[k] = ALPHABET[rand.nextInt(ALPHABET.length)];
            }

            int[] ints = new int[rand.nextInt(ARRAY_MAX_LENGTH) + 1];
            for (int l = 0; l < ints.length; l++) {
                ints[l] = rand.nextInt(100);
            }

            System.out.printf("=============== Quickselect %d ===============\n\n", i);
            orderStatistic = rand.nextInt(strings.length - 1);
            Quickselect<String> strQs = new Quickselect<>(strings);
            System.out.printf("String Array: \n\t%s\n",Arrays.toString(strings));
            System.out.printf("Order statistic: %d\n", orderStatistic);
            System.out.printf("Quickselect returned: %s\n\n", strQs.randomizedQuickSelect(orderStatistic));

            orderStatistic = rand.nextInt(integers.length - 1);
            Quickselect<Integer> intQs = new Quickselect<>(integers);
            System.out.printf("Integer Array:\n\t%s\n",Arrays.toString(integers));
            System.out.printf("Order statistic: %d\n", orderStatistic);
            System.out.printf("Quickselect returned: %d\n\n", intQs.randomizedQuickSelect(orderStatistic));


            System.out.printf("=============== Counting Sort %d ===============\n\n", i);
            System.out.printf("Int Array: \n\t%s\n",Arrays.toString(ints));
            System.out.printf("Sorted: \n\t%s\n", Arrays.toString(countingSort(ints)));
        }
    }
}
