



public class Piece {

    private int locX;
    private int locY;

    /*
     * Type of piece indicated by the following values:
     * 4:       King
     * 3:       Queen
     * 0, 7:    Rooks
     * 2, 5:    Bishops
     * 1, 6:    Knights
     * 8 - 15:  Pawns
     */
    private int type;

    // black pieces are 0, white pieces are 1, empty are 2
    private int color;

    // tracks moves for castles (hasMoved), first pawn move (hasMoved), and en passant (hasJustMoved)
    private boolean hasMoved;
    private boolean hasJustMoved;

    // lists possible moves after being calculated
    private String[] moveList = new String[26];

    public Piece(int locX, int locY, int type, int color) {
        this.locX = locX;
        this.locY = locY;
        this.type = type;
        this.color = color;
        hasMoved = false;
        hasJustMoved = false;
    }

    public char getColor() {
        if(color == 0) {
            return '-';
        } else {
            return ' ';
        }
    }

    public void makeMove(int[] coordinates) {
        locX = coordinates[1];
        locY = coordinates[0];
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public int getPieceType() {
        return type;
    }

    public int getColorInt() {
        return color;
    }

    public boolean enPassant() {
        return hasJustMoved;
    }

    public char getPiece() {
        switch (type) {
            case 4:
                return 'K';
            case 3:
                return 'Q';
            case 0:
            case 7:
                return 'R';
            case 2:
            case 5:
                return 'B';
            case 1:
            case 6:
                return 'N';
            case 8:
                return 'P';
            default:
                return ' ';
        }
    }

    /*
     * The following method gets all possible moves of a given piece and returns the moves
     * as a string array, where each value is a specific move
     * The end of the list is terminated by the string "end"
     */
    public String[] getMoves(Piece[][] board) {
        String[] tempMoveList;
        int tempLocX = locX;
        int tempLocY = locY;
        int moveNum = 0;
        int i = 0;
        
        switch (type) {
            // case for Kings
            case 4:
                
                break;
            // case for Queens (combination of rook and bishop moves)
            case 3:
                // calculates rook moves
                type = 0;
                tempMoveList = getMoves(board);
                while(i < 26 && !tempMoveList[i].equals("end")) {
                    moveList[moveNum] = tempMoveList[i];
                    moveNum++;
                    i++;
                }

                // calculates bishop moves
                type = 2;
                i = 0;
                tempMoveList = getMoves(board);
                while(moveNum < 26 && !tempMoveList[i].equals("end")) {
                    moveList[moveNum] = tempMoveList[i];
                    moveNum++;
                    i++;
                }

                type = 3;
                break;

            // cases for Rooks
            case 0:
            case 7:
                while(tempLocX < 7) {
                    tempLocX++;

                    // checks for pieces of the same color
                    if(board[tempLocY][tempLocX].getColorInt() == color) {
                        break;
                    }

                    moveList[moveNum] = chessVector(tempLocX, tempLocY);
                    moveNum++;

                    // checks for pieces of the opposite color
                    if(board[tempLocX][tempLocY].getPieceType() >= 0) {
                        break;
                    }
                } 
                tempLocX = locX;
                while(tempLocX > 0) {
                    tempLocX--;

                    // checks for pieces of the same color
                    if(board[tempLocY][tempLocX].getColorInt() == color) {
                        break;
                    }

                    moveList[moveNum] = chessVector(tempLocX, tempLocY);
                    moveNum++;

                    // checks for pieces of the opposite color
                    if(board[tempLocY][tempLocX].getPieceType() >= 0) {
                        break;
                    }
                }
                tempLocX = locX;
                while(tempLocY < 7) {
                    tempLocY++;

                    // checks for pieces of the same color
                    if(board[tempLocY][tempLocX].getColorInt() == color) {
                        break;
                    }

                    moveList[moveNum] = chessVector(tempLocX, tempLocY);
                    moveNum++;

                    // checks for pieces of the opposite color
                    if(board[tempLocY][tempLocX].getPieceType() >= 0) {
                        break;
                    }
                }
                tempLocY = locY;
                while(tempLocY > 0) {
                    tempLocY--;

                    // checks for pieces of the same color
                    if(board[tempLocY][tempLocX].getColorInt() == color) {
                        break;
                    }

                    moveList[moveNum] = chessVector(tempLocX, tempLocY);
                    moveNum++;

                    // checks for pieces of the opposite color
                    if(board[tempLocY][tempLocX].getPieceType() >= 0) {
                        break;
                    }
                }
                break;
            
            // case for Bishops
            case 2:
            case 5:
                while(tempLocX < 7 && tempLocY < 7) {
                    tempLocX++;
                    tempLocY++;

                    // checks for pieces of the same color
                    if(board[tempLocY][tempLocX].getColorInt() == color) {
                        break;
                    }

                    moveList[moveNum] = chessVector(tempLocX, tempLocY);
                    moveNum++;

                    // checks for pieces of the opposite color
                    if(board[tempLocY][tempLocX].getPieceType() >= 0) {
                        break;
                    }
                }
                tempLocX = locX;
                tempLocY = locY;
                while(tempLocX < 7 && tempLocY > 0) {
                    tempLocX++;
                    tempLocY--;

                    // checks for pieces of the same color
                    if(board[tempLocY][tempLocX].getColorInt() == color) {
                        break;
                    }

                    moveList[moveNum] = chessVector(tempLocX, tempLocY);
                    moveNum++;

                    // checks for pieces of the opposite color
                    if(board[tempLocY][tempLocX].getPieceType() >= 0) {
                        break;
                    }
                }
                tempLocX = locX;
                tempLocY = locY;
                while(tempLocX > 0 && tempLocY < 7) {
                    tempLocX--;
                    tempLocY++;

                    // checks for pieces of the same color
                    if(board[tempLocY][tempLocX].getColorInt() == color) {
                        break;
                    }

                    moveList[moveNum] = chessVector(tempLocX, tempLocY);
                    moveNum++;

                    // checks for pieces of the opposite color
                    if(board[tempLocY][tempLocX].getPieceType() >= 0) {
                        break;
                    }
                }
                tempLocX = locX;
                tempLocY = locY;
                while(tempLocX > 0 && tempLocY > 0) {
                    tempLocX--;
                    tempLocY--;

                    // checks for pieces of the same color
                    if(board[tempLocY][tempLocX].getColorInt() == color) {
                        break;
                    }

                    moveList[moveNum] = chessVector(tempLocX, tempLocY);
                    moveNum++;

                    // checks for pieces of the opposite color
                    if(board[tempLocY][tempLocX].getPieceType() >= 0) {
                        break;
                    }
                }
                break;

            // case for Knights
            case 1:
            case 6:
                try {
                    if(board[locY + 2][locX + 1].getColorInt() != color) {
                        moveList[moveNum] = chessVector(locX + 1, locY + 2);
                        moveNum++;
                    }
                } catch(ArrayIndexOutOfBoundsException e) {}

                try {
                    if(board[locY + 2][locX - 1].getColorInt() != color) {
                        moveList[moveNum] = chessVector(locX - 1, locY + 2);
                        moveNum++;
                    }
                } catch(ArrayIndexOutOfBoundsException e) {}

                try {
                    if(board[locY - 2][locX + 1].getColorInt() != color) {
                        moveList[moveNum] = chessVector(locX + 1, locY - 2);
                        moveNum++;
                    }
                } catch(ArrayIndexOutOfBoundsException e) {}

                try {
                    if(board[locY - 2][locX - 1].getColorInt() != color) {
                        moveList[moveNum] = chessVector(locX - 1, locY - 2);
                        moveNum++;
                    }
                } catch(ArrayIndexOutOfBoundsException e) {}

                try {
                    if(board[locY + 1][locX + 2].getColorInt() != color) {
                        moveList[moveNum] = chessVector(locX + 2, locY + 1);
                        moveNum++;
                    }
                } catch(ArrayIndexOutOfBoundsException e) {}

                try {
                    if(board[locY + 1][locX - 2].getColorInt() != color) {
                        moveList[moveNum] = chessVector(locX - 2, locY + 1);
                        moveNum++;
                    }
                } catch(ArrayIndexOutOfBoundsException e) {}

                try {
                    if(board[locY - 1][locX + 2].getColorInt() != color) {
                        moveList[moveNum] = chessVector(locX + 2, locY - 1);
                        moveNum++;
                    }
                } catch(ArrayIndexOutOfBoundsException e) {}

                try {
                    if(board[locY - 1][locX - 2].getColorInt() != color) {
                        moveList[moveNum] = chessVector(locX - 2, locY - 1);
                        moveNum++;
                    }
                } catch(ArrayIndexOutOfBoundsException e) {}

                break;
            // case for Pawns
            case 8:
                if(color == 0) {
                    // checks for pieces
                    if(board[locY + 1][locX].getPieceType() < 0) {  
                        moveList[moveNum] = chessVector(locX, locY + 1);
                        moveNum++;
                    }

                    // double move on first pawn move
                    if(!hasMoved && board[locY + 2][locX].getPieceType() >= 0){
                        moveList[moveNum] = chessVector(locX, locY + 2);
                        moveNum++;
                    }

                    // pawn capture checks
                    try{
                        if(board[locY + 1][locX + 1].getColorInt() != color && board[locY + 1][locX + 1].getPieceType() >= 0) {
                            moveList[moveNum] = chessVector(locX + 1, locY + 1);
                            moveNum++;
                        }
                    } catch(ArrayIndexOutOfBoundsException e) {}

                    try{
                        if(board[locY + 1][locX - 1].getColorInt() != color && board[locY + 1][locX - 1].getPieceType() >= 0) {
                            moveList[moveNum] = chessVector(locX - 1, locY + 1);
                            moveNum++;
                        }
                    }  catch(ArrayIndexOutOfBoundsException e) {}
                } else {
                    // checks for pieces
                    if(board[locY - 1][locX].getPieceType() < 0) {  
                        moveList[moveNum] = chessVector(locX, locY - 1);
                        moveNum++;
                    }

                    // double move on first pawn move
                    if(!hasMoved && board[locY - 2][locX].getPieceType() < 0){
                        moveList[moveNum] = chessVector(locX, locY - 2);
                        moveNum++;
                    }

                    // pawn capture checks
                    try{
                        if(board[locY - 1][locX + 1].getColorInt() != color && board[locY - 1][locX + 1].getPieceType() >= 0) {
                            moveList[moveNum] = chessVector(locX + 1, locY - 1);
                            moveNum++;
                        }
                    } catch(ArrayIndexOutOfBoundsException e) {}

                    try{
                        if(board[locY - 1][locX - 1].getColorInt() != color && board[locY - 1][locX - 1].getPieceType() >= 0) {
                            moveList[moveNum] = chessVector(locX - 1, locY - 1);
                            moveNum++;
                        }
                    }  catch(ArrayIndexOutOfBoundsException e) {}
                }
                
                break;
            default:
                
        }

        moveList[moveNum] = "end";

        return moveList;
    }

    /*
     * This converts the 2-way array coordinates of the piece to chess coordinates
     */
    private String chessVector(int x, int y) {
        String move = "";
        char file;
        int rank;

        file = (char)(x + 97);
        rank = 8 - y;

        move = move.concat(Character.toString(file));
        move = move.concat(Integer.toString(rank));

        return move;
    }

    @Override
    public String toString() {
        String returnString = "";

        if(color == 1) {
            returnString = returnString.concat("White ");
        } else if(color == 0) {
            returnString = returnString.concat("Black ");
        } else {
            return "Empty Tile";
        }

        switch (type) {
            case 4:
                returnString = returnString.concat("King");
                break;
            case 3:
                returnString = returnString.concat("Queen");
                break;
            case 0:
            case 7:
                returnString = returnString.concat("Rook");
                break;
            case 2:
            case 5:
                returnString = returnString.concat("Bishop");
                break;
            case 1:
            case 6:
                returnString = returnString.concat("Knight");
                break;
            case 8:
                returnString = returnString.concat("Pawn");
                break;
            default:
                break;
        }

        return returnString;
    }
}
