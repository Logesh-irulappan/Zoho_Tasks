package MineSweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MineSweeper {

    private GameBoard gameBoard;
    private Scanner scanner;

    private static MineSweeper instance;

    private MineSweeper(int boardSize) {
        gameBoard = new GameBoard(boardSize);
        scanner = new Scanner(System.in);
    }

    public static MineSweeper getInstance(int boardSize) {
        if (instance == null)
            instance = new MineSweeper(boardSize);
        return instance;
    }


    public void playGame() {

        boolean firstMove = true;

        do {
            gameBoard.displayCharBoard();

            System.out.println("Flags: " + gameBoard.getFlag());
            System.out.println(" 1. Touch   2. Flag   3.Quit ");

            // User Option -> Runtime Error Handling
            int option = 0;
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
                continue;
            }

            // Option -> 3 will exit without getting row and col index from User.
            if (option == 3) {
                System.out.println("Thank you, See you again!.");
                break;
            }
            // Invalid option check, If true then without getting row and col index from User,
            // the loop continues to next iteration.
            else if (option < 1 || option > 3) {
                System.out.println("Invalid Option");
                continue;
            }

            System.out.println("Enter row and col (index): ");
            int rowIndex = scanner.nextInt();
            int colIndex = scanner.nextInt();

            if (!gameBoard.isValidPosition(rowIndex, colIndex)) {
                System.out.println("Invalid index Position!..");
                continue;
            }

            switch (option) {
                case 1: {
                    if (firstMove) {
                        gameBoard.initializeInternalGameBoard(rowIndex, colIndex);
                        gameBoard.zeroBreak(rowIndex, colIndex);
                        firstMove = false;
                    }
                    else {
                        if (gameBoard.isFlagged(rowIndex, colIndex))
                            System.out.println("Index is Flagged, UnFlag to touch!");
                        else {
                            if (gameBoard.isBomb(rowIndex, colIndex)) {
                                gameBoard.openAllBomb();
                                gameBoard.displayCharBoard();
                                System.out.println("GAME OVER..");
                                return;
                            } else
                                gameBoard.userMove(rowIndex, colIndex);
                        }
                    }
                    break;
                }
                case 2: {
                    gameBoard.setFlag(rowIndex, colIndex);
                    break;
                }
                default:
                    break;
            }

            if (gameBoard.getFlag() == 0) {
                if (gameBoard.winCheck()) {
                    gameBoard.displayCharBoard();
                    System.out.println("YOU WON");
                    break;
                }
            }

        } while (true);

    }

}
