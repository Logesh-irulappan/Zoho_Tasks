package MineSweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int level = 0, boardSize = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Level (int) 1. Easy    2. Medium    3. Hard  => ");

        try {
            level = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input, Enter integer type of Choice");
            main(new String[0]);
        }

        switch (level) {
            case 1: {
                boardSize = 6;
                break;
            }
            case 2: {
                boardSize = 8;
                break;
            }
            case 3: {
                boardSize = 10;
                break;
            }
            default: {
                System.out.println("Invalid Chocie");
                main(new String[0]);
                return;
            }
        }

        MineSweeper game = MineSweeper.getInstance(boardSize);
        game.playGame();
    }

}
