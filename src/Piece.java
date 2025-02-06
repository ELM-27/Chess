
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

    // black pieces are true, white pieces are false
    private boolean color;

    public Piece(int locX, int locY, int type, boolean color) {
        this.locX = locX;
        this.locY = locY;
        this.type = type;
        this.color = color;
    }

    public char getColor() {
        if(color) {
            return '-';
        } else {
            return ' ';
        }
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
    public String[] getMoves() {
        String[] moveList = new String[26];
        
        moveList[0] = chessVector(locX, locY);
        moveList[1] = "end";

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

        if(!color) {
            returnString = returnString.concat("White ");
        } else {
            returnString = returnString.concat("Black ");
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
