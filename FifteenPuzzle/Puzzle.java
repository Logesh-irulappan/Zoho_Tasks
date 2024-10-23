package FifteenPuzzle;

import java.util.Scanner;

public class Puzzle extends PuzzleBoard {

    Puzzle(int n) {
        super(n);
        startGame();
    }

    public void startGame() {
        setBoard();

        Scanner in = new Scanner(System.in);

        while(true) {
            displayBoard();
            System.out.println("  1.Up  2.Down 3.Left 4.Right 5.Quit \n ");
            int option = in.nextInt();
            if(option > 5 || option < 1) {
                System.out.println("Invalid Index!..");
                continue;
            }
            if(option == 5) {
                System.out.println("Thank you!.. See you again");
                break;
            }
            userMoves(option);
            if(zeroAtLast()) {
                if (winCheck()) {
                    displayBoard();
                    System.out.println("YOU WON");
                    break;
                }
            }
        }

    }

}
