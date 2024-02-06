import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Board chessboard = new Board();
        System.out.println("Board:");
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", chessboard);

        Scanner scanner = new Scanner(System.in);

        boolean isBlackTurn = false; // Start with White's turn
        boolean gameOver = false;

        while (!gameOver) {
            System.out.println(chessboard.toString());

            String currentPlayer = isBlackTurn ? "Black" : "White";
            String playerColor = isBlackTurn ? "Black" : "White";

            System.out.println(currentPlayer + ", it's your turn (" + playerColor + ").");
            System.out.println("Enter your move in the format 'startRow startCol endRow endCol': ");
            String moveInput = scanner.nextLine();

            boolean validMove = false; // Flag for a successful move

            try {
                String[] moveParts = moveInput.split(" ");
                if (moveParts.length != 4) {
                    throw new NumberFormatException(); // This will trigger the "Invalid input" message
                }

                int startRow = Integer.parseInt(moveParts[0]);
                int startCol = Integer.parseInt(moveParts[1]);
                int endRow = Integer.parseInt(moveParts[2]);
                int endCol = Integer.parseInt(moveParts[3]);

                Piece piece = chessboard.getPiece(startRow, startCol);

                if (piece == null || piece.getIsBlack() != isBlackTurn) {
                    System.out.println("Invalid move. Try again.");
                } else if (chessboard.movePiece(startRow, startCol, endRow, endCol)) {
                    System.out.println("Move successful!");

                    if (piece.toString().charAt(0) == '\u2659' || piece.toString().charAt(0) == '\u265f') {
                         //Check for pawn promotion
                        piece.promotePawn(endRow, isBlackTurn);
                    }

                    validMove = true; // Mark the move as successful

                    if (chessboard.isGameOver()) {
                        System.out.println(chessboard.toString());
                        System.out.println(currentPlayer + " wins!");
                        gameOver = true;
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter your move in the correct format.");
            }

            if (validMove) {
                isBlackTurn = !isBlackTurn; // Toggle the turn here after a successful move
            }
        }

        scanner.close();
    }
}
