

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

    // false: white's turn, true: black's turn
    private boolean turn = false;

    private String[] moveList;

    public Board() {
        board = buildBoard();
    }

    public boolean changeTurn() {
        turn = !turn;
        if(!turn) {
            for(int i = 0; i < 8; i++) {
                whitePawn[i].fixJustMoved();
            }
        } else {
            for(int i = 0; i < 8; i++) {
                blackPawn[i].fixJustMoved();
            }
        }
        return turn;
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
     *  returns true if the color chosen is correct
     */
    public boolean choosePiece(String piece) {
        int file = coordinateConvert(piece)[1];
        int rank = coordinateConvert(piece)[0];

        if(board[rank][file] != null) {
            movingPiece = board[rank][file];
        }

        if(movingPiece.getColorInt() == 1 && !turn) {
            return true;
        } else if(movingPiece.getColorInt() == 0 && turn) {
            return true;
        } else {
            return false;
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

        moveList = movingPiece.getMoves(board);

        System.out.print("Possible moves: ");

        try{
            while (i < 26) { 
                if(!moveList[i + 1].equals("end")) {
                    System.out.print(moveList[i] + ", ");
                } else {
                    System.out.println(moveList[i]);
                    break;
                }
            i++;
            }
        } catch(NullPointerException e) {
            System.out.println("No possible moves.");
        }
    }

    /*
     * For choosing moves
     */
    public boolean makeMove(String input) {
        for(int i = 0; i < 26; i++) {
            if(input.equals(moveList[i])) {
                move(moveList[i]);
                return true;
            }
        }

        return false;
    }

    private void move(String input) {
        int file = coordinateConvert(input)[1];
        int rank = coordinateConvert(input)[0];

        // case for castling kingside
        if(input.equals("o-o")) {
            if(!turn) {
                board[7][4].makeMove(coordinateConvert("g1"));
                board[7][6] = board[7][4];
                board[7][4] = new Piece(file, movingPiece.getLocY(), -1, 2);
                System.out.print("K->g1");

                board[7][7].makeMove(coordinateConvert("f1"));
                board[7][5] = board[7][7];
                board[7][7] = new Piece(file, movingPiece.getLocY(), -1, 2);
            } else {
                board[0][4].makeMove(coordinateConvert("g8"));
                board[0][6] = board[0][4];
                board[0][4] = new Piece(file, movingPiece.getLocY(), -1, 2);
                System.out.print("K->g8");

                board[0][7].makeMove(coordinateConvert("f8"));
                board[0][5] = board[0][7];
                board[0][7] = new Piece(file, movingPiece.getLocY(), -1, 2);
            }
            return;

        // case for castling queenside
        } else if(input.equals("o-o-o")) {
            if(!turn) {
                board[7][4].makeMove(coordinateConvert("c1"));
                board[7][2] = board[7][4];
                board[7][4] = new Piece(file, movingPiece.getLocY(), -1, 2);
                System.out.print("K->c1");

                board[7][0].makeMove(coordinateConvert("d1"));
                board[7][3] = board[7][0];
                board[7][0] = new Piece(file, movingPiece.getLocY(), -1, 2);
            } else {
                board[0][4].makeMove(coordinateConvert("c8"));
                board[0][2] = board[0][4];
                board[0][4] = new Piece(file, movingPiece.getLocY(), -1, 2);
                System.out.print("K->b1");

                board[0][0].makeMove(coordinateConvert("d8"));
                board[0][3] = board[0][0];
                board[0][0] = new Piece(file, movingPiece.getLocY(), -1, 2);
            }
            return;
            
        // case for en passant
        } else if(board[rank][file].getPieceType() < 0 && movingPiece.getPieceType() == 8 && file != movingPiece.getLocX()) {
            System.out.println("En Passant!");
            if(movingPiece.getColor() == 0) {
                board[movingPiece.getLocY()][file] = new Piece(file, movingPiece.getLocY(), -1, 2);
            } else {
                board[movingPiece.getLocY()][file] = new Piece(file, movingPiece.getLocY(), -1, 2);
            }
        }

        board[rank][file] = movingPiece;
        board[movingPiece.getLocY()][movingPiece.getLocX()] = new Piece(movingPiece.getLocY(), movingPiece.getLocX(), -1, 2);

        movingPiece.makeMove(coordinateConvert(input));
    }

    /*
     * Creates a new chess board with pieces at their starting positions
     */
    private Piece[][] buildBoard() {
        Piece[][] newBoard = new Piece[8][8];

        for(int i = 0; i < 8; i++) {
            white[i] = new Piece(i, 7, i, 1);
            newBoard[7][i] = white[i];
        }

        for(int i = 0; i < 8; i++) {
            whitePawn[i] = new Piece(i, 6, 8, 1);
            newBoard[6][i] = whitePawn[i];
        }

        for(int i = 0; i < 8; i++) {
            for(int j = 2; j < 6; j++) {
                newBoard[j][i] = new Piece(i, j, -1, 2);
            }
        }

        for(int i = 0; i < 8; i++) {
            blackPawn[i] = new Piece(i, 1, 8, 0);
            newBoard[1][i] = blackPawn[i];
        }

        for(int i = 0; i < 8; i++) {
            black[i] = new Piece(i, 0, i, 0);
            newBoard[0][i] = black[i];
        }

        return newBoard;
    }
}
