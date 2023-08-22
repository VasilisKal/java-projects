package gr.aueb.cf.ch10;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The {@link CharacterCountApp} class reads a file, counts the occurrences of characters,
 * and prints out the characters along with their frequencies, including special characters.
 */
public class CharacterCountApp {
    public static void main(String[] args) {
        int[][] inputCharacters = new int[256][2];

        readFile("C:/tmp/characterCount.txt", inputCharacters); // Reads the file
        printCharacters(inputCharacters); // Prints the character with their frequency
    }

    /**
     * Reads a file and counts the occurrences of characters.
     *
     * @param filename The path of the file to be read.
     * @param arr An array to store character occurrences.
     */
    public static void readFile(String filename, int[][] arr) {
        try (FileInputStream fis = new FileInputStream(new File(filename))) {
            int content;
            while ((content = fis.read()) != -1) {
                arr[content][0] = content;
                arr[content][1]++;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    /**
     * Prints characters along with their frequencies.
     *
     * @param arr An array containing character occurrences.
     */
    public static void printCharacters(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] != 0) {
                char character = (char) arr[i][0];
                switch (character) {
                    case ' ':
                        System.out.println("Space: " + arr[i][1]);
                        break;
                    case '\t':
                        System.out.println("Tab: " + arr[i][1]);
                        break;
                    case '\n':
                        System.out.println("Line Feed: " + arr[i][1]);
                        break;
                    case '\r':
                        System.out.println("Carriage Return: " + arr[i][1]);
                        break;
                    default:
                        System.out.println(character + ": " + arr[i][1]);
                        break;
                }
            }
        }
    }
}
