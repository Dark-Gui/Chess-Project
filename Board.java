import java.lang.Math;

public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8];

    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;

    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move
    // is legal, and to execute the move if it is. Your Game class should not 
    // directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece. 
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (board[startRow][startCol].isMoveLegal(this, endRow, endCol)) {
            board[endRow][endCol] = board[startRow][startCol];
            board[startRow][startCol] = null;
            board[endRow][endCol].setPosition(endRow,endCol);
            return true;
        } else {
            return false;
        }


    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        int ctr = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] != null){
                    char cha1 = (char) (board[i][j].toString().charAt(0));
                    if (cha1 == '\u265a' || cha1 == '\u2654')
                        ctr++;
                }
            }
        }
        if (ctr < 2)
         return true;
        else{
            return false;
        }
    }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < 8; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = null;
            }
        }

    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startRow <= 7 && startCol <= 7 && endRow <= 7 && endCol <= 7 && startRow >= 0 && startCol >= 0 && endRow >= 0 && endCol >= 0) {
            if (board[startRow][startCol] != null && board[startRow][startCol].getIsBlack() == isBlack) {
                if (board[endRow][endCol] != null && board[endRow][endCol].getIsBlack() == isBlack) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if (endRow == startRow || endRow == startRow - 1 || endRow == startRow + 1) {
            if (endCol == startCol + 1 || endCol == startCol - 1 || endCol == startCol)
                return true;
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow) { //checks if horizontal
            if (startCol < endCol) {//true if going right
                for (int i = startCol + 1; i < endCol; i++) {//checks if anything is between the start and end col. (only works going right)

                    if (board[endRow][i] != null) {
                        return false;
                    }
                }
                return true;
            } else {
                for (int i = startCol - 1; i > endCol; i--) { //checks if going left,same as going right
                    if (board[endRow][i] != null) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol == endCol) { //checks if Vertical
            if (startRow < endRow) {//true if going right
                for (int i = startRow + 1; i < endRow; i++) {//checks if anything is between the start and end row. (only works going right)
                    if (board[endCol][i] != null) {
                        return false;
                    }
                }
                return true;
            } else {
                for (int i = startRow - 1; i > endRow; i--) { //checks if going left,same as going right
                    if (board[endCol][i] != null) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if (Math.abs(endRow - startRow) == Math.abs(endCol - startCol)) {
            if (startCol <= endCol) {
                if (startRow < endRow) {
                    for (int i = startRow + 1, j = startCol + 1; i < endRow && j < endCol; i++, j++) {
                        if (board[i][j] != null) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    for (int i = startRow - 1, j = startCol + 1; i > endRow && j < endCol; i--, j++) {
                        if (board[i][j] != null) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            else {
                if (startRow < endRow) {
                    for (int i = startRow + 1, j = startCol - 1; i < endRow && j > endCol; i++, j--) {
                        if (board[i][j] != null) {
                            return false;
                        }
                    }
                    return true;
                }
                if (startRow > endRow) {
                    for (int i = startRow - 1, j = startCol - 1; i > endRow && j > endCol; i--, j--) {
                        if (board[i][j] != null) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

}
