package MineSweeper;

import java.util.Scanner;

public class MineSweeper {

    private GameBoard gb;

    MineSweeper(int n) {
        gb = new GameBoard(n);
        playGame();
    }

    public void playGame() {

        Scanner in = new Scanner(System.in);
        boolean firstMove = true;

        do {
            gb.displayCharBoard();

            System.out.println("Flags: "+gb.getFlag());
            System.out.println(" 1. Touch   2. Flag   3.Quit ");
            int option = in.nextInt();

            if(option == 3) {
                System.out.println("Thank you, See you again!.");
                break;
            }

            System.out.println("Enter row and col (index): ");
            int r = in.nextInt();
            int c = in.nextInt();

            if(!gb.isValidPosition(r, c)) {
                System.out.println("Invalid index Position!..");
                continue;
            }

            switch (option) {
                case 1: {
                    if (firstMove) {
                        gb.initializeGameBoard(r, c);
                        gb.zeroBreak(r, c);
                        firstMove = false;
                    }
                    else {
                        if ( gb.isFlagged(r, c) )
                            System.out.println("Index is Flagged, UnFlag to touch!");
                        else {
                            if(gb.isBomb(r, c)) {
                                gb.openAllBomb();
                                gb.displayCharBoard();
                                System.out.println("GAME OVER..");
                                return;
                            }
                            else
                                gb.userMove(r, c);
                        }
                    }
                    break;
                }
                case 2: {
                    gb.setFlag(r, c);
                    break;
                }
            }

            if(gb.getFlag() == 0) {
                if(gb.winCheck()) {
                    System.out.println("YOU WON");
                    break;
                }
            }

        } while(true);

    }

}
