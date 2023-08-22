package gr.aueb.cf.ch10;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The TheaterBookingApp class simulates a theater seat booking system.
 * Users can book or cancel seats and navigate through a menu.
 */
public class TheaterBookingApp {
    static Scanner in = new Scanner(System.in);
    private static boolean[][] theaterSeats = new boolean[30][12];

    public static void main(String[] args) {
        String menuInput;
        int menuChoice;

            do {
                displayMenu();
                menuInput = in.nextLine();
                menuChoice = Integer.parseInt(menuInput);
                try {
                    switch (menuChoice) {
                        case 1:
                            book(getRow(), getColumn());
                            break;
                        case 2:
                            cancelBooking(getRow(), getColumn());
                            break;
                    }
                } catch (InputMismatchException e) {
                e.printStackTrace();
                }
            } while (menuChoice != 3);
            in.close();
    }

    /**
     * Displays the main menu options.
     */
    public static void displayMenu() {
        System.out.println("What do you wish to do?");
        System.out.println("1. New booking");
        System.out.println("2. Cancel booking");
        System.out.println("3. Exit");
    }

    /**
     * Retrieves a valid row selection from the user.
     *
     * @return The selected row number.
     */
    public static int getRow() {
        String rowInput;
        int row;
        System.out.println("Please provide a row selection!");
        while(true) {
            try {
                rowInput = in.nextLine();
                row = Integer.parseInt(rowInput);
                if (row > 0 && row < 31) {
                    return row;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please give a valid row choice. Select a number from 1 to 30.");
            }
        }
    }

    /**
     * Retrieves a valid column selection from the user.
     *
     * @return The selected column number.
     */
    public static int getColumn() {
        String columnInput;
        int column;
        System.out.println("Please provide a column selection!");
        while(true) {
            try {
                columnInput = in.nextLine().toUpperCase();
                column = columnInput.charAt(0);

                if (column >= 65 && column <= 76) {
                    return column;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please give a valid column choice. Select a letter from A to L.");
            }
        }
    }

    /**
     * Books a seat, according to the chosen row and column of the user.
     * @param row the row choice of the user.
     * @param column the column choice of the user
     */
    public static void book(int row, int column) {
        try {
            if(theaterSeats[row - 1][column % 65] == true) {
                System.out.println("This seat is not free.");
            } else {
                theaterSeats[row - 1][column % 65] = true;
                System.out.println("You've booked seat " + row + (char)column);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cancels the booking of a seat, according to the chosen row and column of the user.
     * @param row the row choice of the user.
     * @param column the column choice of the user
     */
    public static void cancelBooking(int row, int column) {
        try {
            if (theaterSeats[row - 1][column % 65] == true) {
                theaterSeats[row - 1][column % 65] = false;
                System.out.println("You've canceled the booking of seat " + row + (char)column);
            } else {
                System.out.println("This seat is already free.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}



