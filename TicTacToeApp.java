package gr.aueb.cf.ch10;

import java.util.Scanner;

/**
 * This class implements a simple two-player Tic Tac Toe game.
 */
public class TicTacToeApp {
    static char[][] ticTacToe = new char[3][3];
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int columnChoice;
        int rowChoice;
        int counter = 2;
        boolean winDraw = false;

        for (int i = 0; i < ticTacToe.length; i++) {
            ticTacToe[i][0] = '-';
            ticTacToe[i][1] = '-';
            ticTacToe[i][2] = '-';
        }

        do {
            System.out.println("It's player's " + (counter%2 + 1) + " turn!");
            displayBoard();
            rowChoice = getRow();
            columnChoice = getColumn(rowChoice);
            fillPosition(rowChoice, columnChoice, counter);

            if (result() == 1) {
                System.out.println("The winner is player " + (counter%2 + 1) + "!");
                displayBoard();
                winDraw = true;
            }
            if (counter == 10) {
                System.out.println("It's a draw!");
                displayBoard();
                winDraw = true;
            }
            counter++;
        } while(!winDraw);

    }

    /**
     * Displays the current state of the Tic Tac Toe board.
     */
    public static void displayBoard() {
        System.out.println("  1 2 3");
        System.out.print("A ");
        System.out.print(ticTacToe[0][0] + " " + ticTacToe[0][1] + " " + ticTacToe[0][2] + "\n");
        System.out.print("B ");
        System.out.print(ticTacToe[1][0] + " " + ticTacToe[1][1] + " " + ticTacToe[1][2] + "\n");
        System.out.print("C ");
        System.out.print(ticTacToe[2][0] + " " + ticTacToe[2][1] + " " + ticTacToe[2][2] + "\n");
    }

    /**
     * Gets the row choice from the user.
     * Checks if it is a valid choice (A, B, or C).
     * Also checks if the chosen row, has at least one empty cell.
     * Catches any wrong input as an exception, and prints a message.
     * @return A valid row choice.
     */
    public static int getRow() {
        int rowChoice;
        System.out.println("Please choose a row: A, B or C");
        do{
            try {
                rowChoice = in.next().toUpperCase().charAt(0);
                if ((rowChoice == 65 || rowChoice == 66 || rowChoice == 67) &&
                (ticTacToe[rowChoice - 65][0] == '-' || ticTacToe[rowChoice - 65][1] == '-' || ticTacToe[rowChoice - 65][2] == '-' )){
                    return rowChoice;
                }
                throw new Exception();

            } catch (Exception e) {
                in.nextLine();
                System.out.println("Please give a valid row choice.");
            }
        } while (true);
    }

    /**
     * Gets the column choice from the user.
     * Checks if it is a valid choice (1, 2, or 3).
     * Also checks if the chosen row-column cell is empty.
     * Catches any wrong input as an exception, and prints a message.
     * @param rowChoice The chosen row.
     * @return A valid column choice.
     */
    public static int getColumn(int rowChoice){
        int columnChoice;
        System.out.println("Please choose a column: 1, 2 or 3");
        while (true) {
             try {
                 columnChoice = in.nextInt();

                 if (columnChoice < 1 || columnChoice > 3 || (ticTacToe[rowChoice - 65][columnChoice - 1] != '-')) {
                     throw new Exception();
                 }

                 return columnChoice;
             } catch (Exception e) {
                 in.nextLine();
                 System.out.println("Please give a valid column choice.");
             }
         }

    }

    /**
     * Sets the chosen position of ticTacToe[][] as 'X' or 'O',
     * depending on the current turn - 'X' if counter is an even number, 'O' if it's an odd.
     * @param rowChoice The row choice.
     * @param columnChoice The column choice.
     * @param counter Indicates which player is currently playing.
     */
    public static void fillPosition(int rowChoice, int columnChoice, int counter) {
        if (counter % 2 == 0) {
            ticTacToe[rowChoice - 65][columnChoice - 1] = 'X';
        } else {
            ticTacToe[rowChoice - 65][columnChoice - 1] = 'O';
        }
    }

    /**
     * Checks if there is a winner.
     * @return 1 if there is a winner, -1 otherwise.
     */
    public static int result() {
        if (
                (ticTacToe[0][0] == ticTacToe[0][1] && ticTacToe[0][1] != '-' && ticTacToe[0][1] == ticTacToe[0][2] ||
                ticTacToe[1][0] == ticTacToe[1][1] && ticTacToe[1][1] != '-' && ticTacToe[1][1] == ticTacToe[1][2] ||
                ticTacToe[2][0] == ticTacToe[2][1] && ticTacToe[2][1] != '-' && ticTacToe[2][1] == ticTacToe[2][2] ||
                ticTacToe[0][0] == ticTacToe[1][0] && ticTacToe[1][0] != '-' && ticTacToe[1][0] == ticTacToe[2][0] ||
                ticTacToe[0][1] == ticTacToe[1][1] && ticTacToe[1][1] != '-' && ticTacToe[1][1] == ticTacToe[1][2] ||
                ticTacToe[0][2] == ticTacToe[1][2] && ticTacToe[1][2] != '-' && ticTacToe[1][2] == ticTacToe[2][2] ||
                ticTacToe[0][0] == ticTacToe[1][1] && ticTacToe[1][1] != '-' && ticTacToe[1][1] == ticTacToe[2][2] ||
                ticTacToe[0][2] == ticTacToe[1][1] && ticTacToe[1][1] != '-' && ticTacToe[1][1] == ticTacToe[2][0])
        ) {
            return 1;
        }
        return -1;
    }
}

