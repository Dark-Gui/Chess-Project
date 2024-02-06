public class Rook {

    // Instance variables
    private int row;         // The current row of the pawn.
    private int col;         // The current column of the pawn.
    private boolean isBlack; // The color of the pawn (true for black, false for white).

    /**
     * Constructor to initialize the Rook.
     *
     * @param row     The current row of the pawn.
     * @param col     The current column of the pawn.
     * @param isBlack The color of the pawn.
     */
    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Checks if a move to a destination square is legal for the Rook.
     *
     * @param board  The game board.
     * @param endRow The row of the destination square.
     * @param endCol The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // Check if the move is along a row or column without any pieces in between.
        if (board.verifyVertical(this.row, this.col, endRow, endCol) || board.verifyHorizontal(this.row, this.col, endRow, endCol)) {
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
