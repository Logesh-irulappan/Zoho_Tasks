package MineSweeper;

public class GameBoard {

    private final int n;
    private int flag;
    private int[][] board;
    private char[][] charBoard;

    GameBoard(int n) {
        this.n = n;
        this.flag = n;
        board = new int[n][n];
        charBoard = new char[n][n];
        setCharBoard();
    }

    public int getFlag() {
        return flag;
    }

    public void initializeGameBoard(int r, int c) {
        int bombCount = n;
        while(bombCount > 0) {
            // Getting random index for inserting bomb...
            int x = (int) (Math.random() * n);
            int y = (int) (Math.random() * n);

            // Neglecting the user's index position and inserting the bomb...
            if( (x == r || x == (r-1) || x == (r+1)) && ( y== c || y == (c-1) || y == (c+1) ) ){
                continue;
            }
            else if(board[x][y] != -1) {
                // -1 indicates the bomb in the board..
                board[x][y] = -1;
                bombCount--;
                setAroundNumbers(x, y);
            }
        }
    }

    public void setAroundNumbers(int r, int c) {
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if( !isValidPosition(r+i, c+j) )
                    continue;
                else if( i == 0 && j == 0)
                    continue;
                else if( board[r+i][c+j] != -1 )
                    board[r+i][c+j] += 1;
            }
        }
    }

    public boolean isValidPosition(int r, int c) {
        return ( r > -1 && r < n ) && ( c > -1 && c < n );
    }

    // Actions

    public void zeroBreak(int r, int c) {
        // -9 representing the zero's at board array to break the cyclic recursion
        board[r][c] = -9;
        charBoard[r][c] = ' ';
        // nested loop for accessing all 8 cells in 2D array
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                // checking for only valid index of our 2D array
                if(isValidPosition(r+i, c+j)) {
                    // Detecting number in surrounding 8 tiles...
                    if (board[r + i][c + j] > 0) {
                        charBoard[r + i][c + j] = String.valueOf(board[r + i][c + j]).charAt(0);
                    }
                    // if zero is found repeat this function
                    else if (board[r+i][c+j] == 0) {
                        zeroBreak(r+i, c+j);
                    }
                }
            }
        }
    }

    public boolean isBomb(int r, int c) {
        return board[r][c] == -1;
    }


    // After Player losing the game, the wrongly placed flags and actual bomb places are opened..
    public void openAllBomb() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // wrong flag placed mark
                if( board[i][j] != -1 && charBoard[i][j] == 'F' )
                    charBoard[i][j] = '/';
                // bomb places
                else if( board[i][j] == -1 ) {
                    charBoard[i][j] = 'B';
                }
            }
        }
    }

    public void userMove(int r, int c) {
        if(charBoard[r][c] != '*') {
            System.out.println("This Cell is Already opened");
        }
        else if(board[r][c] == 0) {
            zeroBreak(r, c);
        }
        else {
            charBoard[r][c] = String.valueOf(board[r][c]).charAt(0);
        }
    }

    public void setFlag(int r, int c) {
        if(charBoard[r][c] != 'F' && charBoard[r][c] != '*') {
            System.out.println("Index is Already Opened, Flag cannot be inserted.");
        }
        else if(charBoard[r][c] == 'F') {
            charBoard[r][c] = '*';
            flag += 1;
        }
        else {
            charBoard[r][c] = 'F';
            flag -= 1;
        }
    }

    // If an unopened index is flag by user, User has to unFlag it first, inorder to open that index.
    public boolean isFlagged(int r, int c) {
        return charBoard[r][c] == 'F';
    }

    // player winning check, only when all flags are placed
    public boolean winCheck() {
        if( !isAllCellsOpened() )
            return false;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if( board[i][j] == -1 && charBoard[i][j] != 'F' )
                    return false;
            }
        }
        return true;
    }

    // checking for Unopened cells before going into win checking..
    public boolean isAllCellsOpened() {
        int breakCount = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if( board[i][j] == -1 )
                    continue;
                else if( charBoard[i][j] == '*' )
                    return false;
            }
        }
        return true;
    }

    // Displaying and setting the board to initial stage
    public void setCharBoard() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++)
                charBoard[i][j] = '*';
        }
    }

    public void displayCharBoard() {
        System.out.print(" ");
        for (int i = 0; i < n; i++)
            System.out.print("  "+i);
        System.out.println();
        for(int i = 0; i < n; i++) {
            System.out.print(i+"  ");
            for(int j = 0; j < n; j++)
                System.out.print(charBoard[i][j]+"  ");
            System.out.println();
        }
        System.out.println();
    }

}
