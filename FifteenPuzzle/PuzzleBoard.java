package FifteenPuzzle;

import java.util.ArrayList;
import java.util.Collections;

public class PuzzleBoard {

    private int n;
    private int[][] board;
    private int emptyR, emptyC;

    PuzzleBoard(int n) {
        this.n = n;
        board = new int[n][n];
    }

    public void userMoves(int option) {
        switch (option) {
            case 1: {
                if( isValidIndex(emptyR-1, emptyC) ) {
                    board[emptyR][emptyC] = board[emptyR-1][emptyC];
                    board[emptyR-1][emptyC] = 0;
                    emptyR -= 1;
                }
                else
                    System.out.println("Invalid Move");
                break;
            }
            case 2: {
                if( isValidIndex(emptyR+1, emptyC) ) {
                    board[emptyR][emptyC] = board[emptyR+1][emptyC];
                    board[emptyR+1][emptyC] = 0;
                    emptyR += 1;
                }
                else
                    System.out.println("Invalid Move");
                break;
            }
            case 3: {
                if( isValidIndex(emptyR, emptyC-1) ) {
                    board[emptyR][emptyC] = board[emptyR][emptyC-1];
                    board[emptyR][emptyC-1] = 0;
                    emptyC -= 1;
                }
                else
                    System.out.println("Invalid Move");
                break;
            }
            case 4: {
                if( isValidIndex(emptyR, emptyC+1) ) {
                    board[emptyR][emptyC] = board[emptyR][emptyC+1];
                    board[emptyR][emptyC+1] = 0;
                    emptyC += 1;
                }
                else
                    System.out.println("Invalid Move");
                break;
            }
        }
    }

    // check that given r -> row and c -> col index are in board..
    public boolean isValidIndex(int r, int c) {
        return ( r > -1 && r < n ) && ( c > -1 && c < n );
    }

    // If zero is at last index which is [n-1][n-1], then we can check for win(), until no need
    public boolean zeroAtLast() { return board[n-1][n-1] == 0; }

    public boolean winCheck() {
        int index = 1;
        int temp = board[n-1][n-1];
        board[n-1][n-1] = n*n;
        boolean res = true;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] != index++)
                    res = false;
            }
        }
        board[n-1][n-1] = temp;
        return res;
    }

    // Initialization and display of board

    public void setBoard() {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i < n*n; i++)
            list.add(i);
        list.add(0);
        Collections.shuffle(list);
        emptyR = list.indexOf(0) / n;
        emptyC = list.indexOf(0) % n;
        int indexOfList = 0;
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++)
                board[i][j] = list.get(indexOfList++);
        }
    }

    public void displayBoard() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(" "+board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
