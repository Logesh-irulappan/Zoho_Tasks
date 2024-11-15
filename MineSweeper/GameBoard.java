package MineSweeper;

public class GameBoard {

    private final int boardSize;
    private int flag;
    private int[][] internalBoard;
    private char[][] displaySideBoard;

    GameBoard(int boardSize) {
        this.boardSize = boardSize;
        this.flag = boardSize;
        internalBoard = new int[boardSize][boardSize];
        displaySideBoard = new char[boardSize][boardSize];
        setCharBoard();
    }

    public int getFlag() {
        return flag;
    }

    public void initializeInternalGameBoard(int row, int col) {
        int bombCount = boardSize;
        while(bombCount > 0) {
            // Getting random index for inserting bomb...
            int x = (int) (Math.random() * boardSize);
            int y = (int) (Math.random() * boardSize);

            // Neglecting the user's index position and inserting the bomb...
            if( (x == row || x == (row -1) || x == (row +1)) && ( y== col || y == (col -1) || y == (col +1) ) ){
                continue;
            }
            else if(internalBoard[x][y] != -1) {
                // -1 indicates the bomb in the internalBoard..
                internalBoard[x][y] = -1;
                bombCount--;
                setAroundNumbers(x, y);
            }
        }
    }

    public void setAroundNumbers(int rowIndex, int colIndex) {
        for(int row = -1; row <= 1; row++) {
            for(int col = -1; col <= 1; col++) {
                if( !isValidPosition(rowIndex +row, colIndex +col) )
                    continue;
                else if( row == 0 && col == 0)
                    continue;
                else if( internalBoard[rowIndex +row][colIndex +col] != -1 )
                    internalBoard[rowIndex +row][colIndex +col] += 1;
            }
        }
    }

    public boolean isValidPosition(int row, int col) {
        return ( row > -1 && row < boardSize) && ( col > -1 && col < boardSize);
    }

    // Actions
    public void zeroBreak(int rowIndex, int colIndex) {
        // -9 representing the zero's at internalBoard array to break the cyclic recursion
        internalBoard[rowIndex][colIndex] = -9;
        displaySideBoard[rowIndex][colIndex] = ' ';
        // nested loop for accessing all 8 cells in 2D array
        for(int row = -1; row <= 1; row++) {
            for(int col = -1; col <= 1; col++) {
                // checking for only valid index of our 2D array
                if(isValidPosition(rowIndex +row, colIndex +col)) {
                    // Detecting number in surrounding 8 tiles...
                    if (internalBoard[rowIndex + row][colIndex + col] > 0) {
                        displaySideBoard[rowIndex + row][colIndex + col] = String.valueOf(internalBoard[rowIndex + row][colIndex + col]).charAt(0);
                    }
                    // if zero is found repeat this function
                    else if (internalBoard[rowIndex +row][colIndex +col] == 0) {
                        zeroBreak(rowIndex +row, colIndex +col);
                    }
                }
            }
        }
    }

    public boolean isBomb(int row, int col) {
        return internalBoard[row][col] == -1;
    }


    // After Player losing the game, the wrongly placed flags and actual bomb places are opened..
    public void openAllBomb() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                // wrong flag placed mark
                if( internalBoard[row][col] != -1 && displaySideBoard[row][col] == 'F' )
                    displaySideBoard[row][col] = '/';
                // bomb places
                else if( internalBoard[row][col] == -1 ) {
                    displaySideBoard[row][col] = 'B';
                }
            }
        }
    }

    public void userMove(int row, int col) {
        if(displaySideBoard[row][col] != '*') {
            System.out.println("This Cell is Already opened");
        }
        else if(internalBoard[row][col] == 0) {
            zeroBreak(row, col);
        }
        else {
            displaySideBoard[row][col] = String.valueOf(internalBoard[row][col]).charAt(0);
        }
    }

    public void setFlag(int row, int col) {
        if(displaySideBoard[row][col] != 'F' && displaySideBoard[row][col] != '*') {
            System.out.println("Index is Already Opened, Flag cannot be inserted.");
        }
        else if(displaySideBoard[row][col] == 'F') {
            displaySideBoard[row][col] = '*';
            flag += 1;
        }
        else {
            displaySideBoard[row][col] = 'F';
            flag -= 1;
        }
    }

    // If an unopened index is flag by user, User has to unFlag it first, inorder to open that index.
    public boolean isFlagged(int row, int col) {
        return displaySideBoard[row][col] == 'F';
    }

    // player winning check, only when all flags are placed
    public boolean winCheck() {
        if( !isAllCellsOpened() )
            return false;
        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                if( internalBoard[row][col] == -1 && displaySideBoard[row][col] != 'F' )
                    return false;
            }
        }
        return true;
    }

    // checking for Unopened cells before going into win checking..
    public boolean isAllCellsOpened() {
        int breakCount = 0;
        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                if( internalBoard[row][col] == -1 )
                    continue;
                else if( displaySideBoard[row][col] == '*' )
                    return false;
            }
        }
        return true;
    }

    // Displaying and setting the internalBoard to initial stage
    public void setCharBoard() {
        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++)
                displaySideBoard[row][col] = '*';
        }
    }

    public void displayCharBoard() {
        System.out.print(" ");
        for (int row = 0; row < boardSize; row++)
            System.out.print("  "+row);
        System.out.println();
        for(int row = 0; row < boardSize; row++) {
            System.out.print(row+"  ");
            for(int col = 0; col < boardSize; col++)
                System.out.print(displaySideBoard[row][col]+"  ");
            System.out.println();
        }
        System.out.println();
    }

}
