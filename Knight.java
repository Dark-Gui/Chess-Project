public class Knight {

    /**
     * Constructor.
     *
     * @param row     The current row of the pawn.
     * @param col     The current column of the pawn.
     * @param isBlack The color of the pawn.
     */
    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Checks if a move to a destination square is legal.
     *
     * @param board  The game board.
     * @param endRow The row of the destination square.
     * @param endCol The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    // Implement the legal move logic for a Knight here.
    // Knights move in an L-shape, either two squares in one direction and one square in another,
    // or one square in one direction and two squares in another.
    // Check if the move follows this pattern and is not obstructed by other pieces.
    // Return true if the move is legal, otherwise return false.
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (endRow == this.row - 2 || endRow == this.row + 2) {
            if (endCol == this.col + 1 || endCol == this.col - 1) {
                if (board.getPiece(endRow, endCol) == null || (board.getPiece(endRow, endCol).getIsBlack() != isBlack)) {
                    return true;
                }
            }
            return false;
        } else if (endRow == this.row - 1 || endRow == this.row + 1) {
            if (endCol == this.col + 2 || endCol == this.col - 2) {
                if (board.getPiece(endRow, endCol) == null || (board.getPiece(endRow, endCol).getIsBlack() != isBlack)) {
                    return true;
                }
            }
            return false;
        } else {
            // Case 3: Moving in a non-adjacent column. (illegal move)
            return false;
        }
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;

}
