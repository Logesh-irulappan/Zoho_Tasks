package FifteenPuzzle;

import java.util.Scanner;

public class Main {

    // In this Program '0' (zero) indicated the empty space...
    // Moving zero places are given in options...

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the board size (n*n) => n: ");
        int n = in.nextInt();
        Puzzle game = new Puzzle(n);
    }

}
