public class Queen {

    // Instance variables
    private int row;         // The current row of the Queen.
    private int col;         // The current column of the Queen.
    private boolean isBlack; // The color of the Queen (true for black, false for white).

    /**
     * Constructor to initialize the Queen.
     *
     * @param row     The current row of the Queen.
     * @param col     The current column of the Queen.
     * @param isBlack The color of the Queen.
     */
    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Checks if a move to a destination square is legal for the Queen.
     *
     * @param board  The game board.
     * @param endRow The row of the destination square.
     * @param endCol The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // Check if the move follows one of these patterns: diagonal, vertical, or horizontal.
        if (board.verifyDiagonal(this.row, this.col, endRow, endCol) ||
                board.verifyVertical(this.row, this.col, endRow, endCol) ||
                board.verifyHorizontal(this.row, this.col, endRow, endCol)) {
            // Check if the destination square is empty or occupied by an opponent's piece.
            if (board.getPiece(endRow, endCol) == null || (board.getPiece(endRow, endCol).getIsBlack() != isBlack)) {
                return true; // Legal move.
            }
            return false; // Destination square is occupied by own piece.
        } else {
            // Case 3: Moving in a non-adjacent column (illegal move).
            return false;
        }
    }
}
