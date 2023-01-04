import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    private String[][] values = {
        {" ", " ", " "},
        {" ", " ", " "},
        {" ", " ", " "},
    };
    private Scanner scanner = new Scanner(System.in);
    private int row;
    private int column;
    private int random;
    private int turns;
    private int STOP = -1;
    private String rowString;
    private String columnString;
    private String diagonalLeftString;
    private String diagonalRightString;
    private String X_WIN_CON = "XXX";
    private String O_WIN_CON = "OOO";
    private boolean xTurn;
    private boolean xWin;
    private boolean oWin;
    private boolean shouldContinue = true;
    private boolean draw;
    
    public TicTacToe() {
        // Nothing goes here
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.play();
    }

    public void play() {
        turns = 1;
        xTurn = getTurn();
        System.out.println("Type '0 0' to quit");
        while (shouldContinue) {
            printGrid();
            if (xTurn) {
                System.out.println("Turn " + turns + ": " + "X's move! (Row Column)");
            } else {
                System.out.println("Turn " + turns + ": " + "O's move! (Row Column)");
            }
            try {
                row = scanner.nextInt() - 1;
                column = scanner.nextInt() - 1;
                scanner.nextLine();
                if (row == STOP && column == STOP) {
                    shouldContinue = false;
                    continue;
                } else if (row < 0 || column < 0 || row >= values.length || column >= values[0].length) {
                    System.out.println("Please enter valid coordinates");
                    scanner.nextLine();
                    continue;
                }
                if (values[row][column].equals(" ")) {
                    if (xTurn) {
                        values[row][column] = "X";
                    } else {
                        values[row][column] = "O";
                    }
                    xTurn = !xTurn;
                    if (getRow().equals(X_WIN_CON) || getColumn().equals(X_WIN_CON) || getDiagonalLeft().equals(X_WIN_CON) || getDiagonalRight().equals(X_WIN_CON)) {
                        xWin = true;
                        shouldContinue = false;
                    } else if (getRow().equals(O_WIN_CON) || getColumn().equals(O_WIN_CON) || getDiagonalLeft().equals(O_WIN_CON) || getDiagonalRight().equals(O_WIN_CON)) {
                        oWin = true;
                        shouldContinue = false;
                    }
                    turns++;
                    if (turns > values.length * values[0].length) {
                        draw = true;
                        shouldContinue = false;
                    }
                } else {
                    System.out.println("Please choose an unoccupied space");
                }
            } catch (InputMismatchException error) {
                System.out.println("Please enter valid coordinates");
                scanner.nextLine();
            }
        }
        if (xWin) {
            printGrid();
            System.out.println("And the winner is... X!");
        } else if (oWin) {
            printGrid();
            System.out.println("And the winner is... O!");
        } else if (draw) {
            printGrid();
            System.out.println("Draw!");
        } else {
            System.out.println("Closing...");
        }
    }

    public void printGrid() {
        System.out.println(" " + values[0][0] + " | " + values[0][1] + " | " + values[0][2] + " ");
        System.out.println("---+---+---");
        System.out.println(" " + values[1][0] + " | " + values[1][1] + " | " + values[1][2] + " ");
        System.out.println("---+---+---");
        System.out.println(" " + values[2][0] + " | " + values[2][1] + " | " + values[2][2] + " ");
    }

    public boolean getTurn() {
        random = (int)(Math.random() * 2) + 1;
        return random == 1;
    }

    public String getRow() {
        rowString = "";
        for (int i = 0; i < values[0].length; i++) {
            rowString += values[row][i];
        }
        return rowString;
    }

    public String getColumn() {
        columnString = "";
        for (int i = 0; i < values.length; i++) {
            columnString += values[i][column];
        }
        return columnString;
    }

    public String getDiagonalLeft() {
        diagonalLeftString = "";
        for (int i = 0; i < values.length; i++) {
            diagonalLeftString += values[i][i];
        }
        return diagonalLeftString;
    }

    public String getDiagonalRight() {
        diagonalRightString = "";
        for (int i = 0; i < values.length; i++) {
            diagonalRightString += values[values.length - (i + 1)][i];
        }
        return diagonalRightString;
    }
}

// Draw sequence for testing: 1 1 2 2 2 1 3 1 1 3 1 2 3 2 2 3 3 3