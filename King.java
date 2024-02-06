public class King {

    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */
    // Implement the legal move logic for a King here.
    // Kings can move one square in any direction (horizontally, vertically, or diagonally).
    // Check if the move is within one square in any direction.
    // Return true if the move is legal, otherwise return false.
    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Checks if a move to a destination square is legal.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifyAdjacent(this.row, this.col, endRow, endCol)){
            if(board.getPiece(endRow,endCol) == null || (board.getPiece(endRow,endCol).getIsBlack() != isBlack)){
                return true;
            }
            return false;
        }else{
            // Case 3: Moving in a non-adjacent column. (illegal move)
            return false;
        }
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;

}
