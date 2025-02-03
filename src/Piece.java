import java.util.*;
import java.io.*;

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
        if (type == 4) {
            return 'K';
        } else if (type == 3) {
            return 'Q';
        } else if (type == 0 || type == 7) {
            return 'R';
        } else if (type == 2 || type == 5) {
            return 'B';
        } else if (type == 1 || type == 6) {
            return 'N';
        } else if (type == 8) {
            return 'P';
        } else {
            return ' ';
        }
    }

    @Override
    public String toString() {
        String returnString = "";

        if(!color) {
            returnString.concat("White ");
        } else {
            returnString.concat("Black ");
        }

        if (type == 4) {
            returnString.concat("King");
        } else if (type == 3) {
            returnString.concat("Queen");
        } else if (type == 0 || type == 7) {
            returnString.concat("Rook");
        } else if (type == 2 || type == 5) {
            returnString.concat("Bishop");
        } else if (type == 1 || type == 6) {
            returnString.concat("Knight");
        } else if (type == 8) {
            returnString.concat("Pawn");
        }

        return returnString;
    }
}
