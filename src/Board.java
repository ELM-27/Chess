
public class Board {
    private Piece[][] board;

    // Pieces are defined individually for easy access
    /*
     * Location of pieces in index (from left to right when multiple of the same piece are present)
     * Index 4:       King
     * Index 3:       Queen
     * Index 0, 7:    Rooks
     * Index 2, 5:    Bishops
     * Index 1, 6:    Knights
     * Index 8 - 15:  Pawns
     */
    private Piece[] white = new Piece[8];
    private Piece[] whitePawn = new Piece[8];
    private Piece[] black = new Piece[8];
    private Piece[] blackPawn = new Piece[8];

    private Piece movingPiece = null;

    public Board() {
        board = buildBoard();
    }

    public void printBoard() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                System.out.print(board[i][j].getPiece() + "" + board[i][j].getColor() + " ");
            }

            System.out.println();
        }
    }

    /*
     * This method converts chess coordinates to coordinates of the board 2-way array
     *  input:              the chess coordinates
     *  coordinateVector:   the vector coordinates
     *           index 0:   rank
     *           index 1:   file
     */
    public int[] coordinateConvert(String input) {
        int[] coordinateVector = new int[2];

        char fileChar;
        char rankChar;

        fileChar = input.charAt(0);
        rankChar = input.charAt(1);

        coordinateVector[1] = fileChar - 97;
        coordinateVector[0] = 7 - (rankChar - 49);

        return coordinateVector;
    }

    /*
     * This method chooses a piece from the board (specifiec by a coordinate) and assigns it to movingPiece
     *  piece:  The corrdinate specified by the call to the piece
     */
    public void choosePiece(String piece) {
        int file = coordinateConvert(piece)[1];
        int rank = coordinateConvert(piece)[0];

        if(board[rank][file] != null) {
            movingPiece = board[rank][file];
        }
    }

    /*
     * This method returns the current chosen piece as a string
     */
    public void readPiece() {
        System.out.println(movingPiece.toString());
    }

    /*
     * Lists all possible moves for the chosen piece
     */
    public void listMoves() {
        int i = 0;

        String[] moveList = movingPiece.getMoves();
        String move;

        System.out.print("Possible moves: ");

        while (i < 26) { 
            move = moveList[i];
            if(!move.equals("end")) {
                System.out.print(move + ", ");
            } else {
                break;
            }

            i++;
        }

        System.out.println();
    }

    /*
     * Creates a new chess board with pieces at their starting positions
     */
    private Piece[][] buildBoard() {
        Piece[][] newBoard = new Piece[8][8];

        for(int i = 0; i < 8; i++) {
            white[i] = new Piece(i, 7, i, false);
            newBoard[7][i] = white[i];
        }

        for(int i = 0; i < 8; i++) {
            whitePawn[i] = new Piece(i, 6, 8, false);
            newBoard[6][i] = whitePawn[i];
        }

        for(int i = 0; i < 8; i++) {
            for(int j = 2; j < 6; j++) {
                newBoard[j][i] = new Piece(i, j, -1, false);
            }
        }

        for(int i = 0; i < 8; i++) {
            blackPawn[i] = new Piece(i, 1, 8, true);
            newBoard[1][i] = blackPawn[i];
        }

        for(int i = 0; i < 8; i++) {
            black[i] = new Piece(i, 0, i, true);
            newBoard[0][i] = black[i];
        }

        return newBoard;
    }
}
