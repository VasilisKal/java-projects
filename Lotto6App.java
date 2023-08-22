package gr.aueb.cf.ch10;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Generates combinations of numbers for a lottery game based on specific criteria.
 */
public class Lotto6App {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("c:/tmp/lotto6in.txt"));
             PrintStream ps = new PrintStream("c:/tmp/lotto6out.txt", StandardCharsets.UTF_8)) {

            final int LOTTO_SIZE = 6;
            int[] inputNumbers = new int[49];
            int[] result = new int[6];
            int[] sameEnding = new int[10];
            int[] countTen = new int[5];
            int pivot = 0;
            int num;
            int window;
            int count = 0;

            while ((num = in.nextInt()) != -1 && pivot <= 9) {
                inputNumbers[pivot++] = num;
            }

            int[] numbers = Arrays.copyOfRange(inputNumbers, 0, pivot);
            System.out.println(Arrays.toString(numbers));

            window = pivot - LOTTO_SIZE;
            for(int i = 0; i <= window; i++) {
                for (int j = i +1; j <= window + 1; j++){
                    for (int k = j + 1; k <= window + 2; k++) {
                        for (int l = k + 1; l <= window + 3; l++) {
                            for (int m = l + 1; m <= window + 4; m++) {
                                for(int p = m + 1; p <= window + 5; p++) {
                                    result[0] = numbers[i];
                                    result[1] = numbers[j];
                                    result[2] = numbers[k];
                                    result[3] = numbers[l];
                                    result[4] = numbers[m];
                                    result[5] = numbers[p];

                                    if(isMaxFourEven(result, 4) && isMaxFourOdd(result, 4) && isMaxTwoContiguous(result, 2)
                                    && isMaxThreeSameTen(result, countTen, 3) && isMaxThreeSameEnding(result, sameEnding, 3)) {
                                        ps.printf("%d %d %d %d %d %d\n",
                                                result[0], result[1], result[2], result[3], result[4], result[5]);
                                        count++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the count of even numbers is within the threshold.
     *
     * @param arr       Array of integers
     * @param threshold Maximum allowed count of even numbers
     * @return True if the count of even numbers is within the threshold, false otherwise
     */
    public static boolean isMaxFourEven(int[] arr, int threshold) {
        int even = 0;

        for (int num : arr) {
            if (num% 2 == 0) even++;
        }

        return even <= threshold;
    }

    /**
     * Checks if the count of odd numbers is within the threshold.
     *
     * @param arr       Array of integers
     * @param threshold Maximum allowed count of odd numbers
     * @return True if the count of odd numbers is within the threshold, false otherwise
     */
    public static boolean isMaxFourOdd(int[] arr, int threshold) {
        int odd = 0;

        for (int num : arr) {
            if (num % 2 != 0) odd++;
        }

        return odd <= threshold;
    }

    /**
     * Checks if there are at most two contiguous identical numbers.
     *
     * @param arr       Array of integers
     * @param threshold Maximum allowed count of contiguous identical numbers
     * @return True if there are at most two contiguous identical numbers, false otherwise
     */
    public static boolean isMaxTwoContiguous(int[] arr, int threshold) {
        int contiguous = 0;

        for(int i = 0; i <arr.length - 2; i++) {
            if (arr[i] == arr[i+1] && arr[i+1] == arr[i+2]) contiguous++;
        }
        return contiguous <= threshold;
    }

    /**
     * Checks if there are at most three numbers with the same last digit.
     *
     * @param arr1      Array of integers
     * @param arr2      Array to store counts of numbers with the same last digit
     * @param threshold Maximum allowed count of numbers with the same last digit
     * @return True if there are at most three numbers with the same last digit, false otherwise
     */
    public static boolean isMaxThreeSameEnding(int[] arr1, int[] arr2, int threshold) {
        Arrays.fill(arr2, 0);

        for (int item : arr1) {
            arr2[item % 10]++;
        }
        Arrays.sort(arr2);

        return arr2[9] <= threshold;
    }

    /**
     * Checks if there are at most three numbers with the same ten.
     *
     * @param arr1      Array of integers
     * @param arr2      Array to store counts of numbers with the same ten
     * @param threshold Maximum allowed count of numbers with the same ten
     * @return True if there are at most three numbers with the same ten, false otherwise
     */
    public static boolean isMaxThreeSameTen(int[] arr1, int[] arr2, int threshold) {
        Arrays.fill(arr2, 0);

        for (int item : arr1) {
            arr2[item / 10]++;
        }
        Arrays.sort(arr2);

        return arr2[4] <= threshold;
    }

}
