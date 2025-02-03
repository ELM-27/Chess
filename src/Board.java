import java.util.*;
import java.io.*;

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

    public void readPiece(String move) {
        Piece movePiece;
        int file;
        char fileChar;
        int rank;
        char rankChar;

        fileChar = move.charAt(0);
        rankChar = move.charAt(1);

        file = fileChar - 96;
        rank = rankChar - 48;

        movingPiece = board[rank][file];

        System.out.println(movingPiece.toString());
    }

    private Piece[][] buildBoard() {
        Piece[][] newBoard = new Piece[8][8];

        for(int i = 0; i < 8; i++) {
            white[i] = new Piece(7, i, i, false);
            newBoard[7][i] = white[i];
        }

        for(int i = 0; i < 8; i++) {
            whitePawn[i] = new Piece(6, i, 8, false);
            newBoard[6][i] = whitePawn[i];
        }

        for(int i = 0; i < 8; i++) {
            for(int j = 2; j < 6; j++) {
                newBoard[j][i] = new Piece(j, i, -1, false);
            }
        }

        for(int i = 0; i < 8; i++) {
            blackPawn[i] = new Piece(1, i, 8, true);
            newBoard[1][i] = blackPawn[i];
        }

        for(int i = 0; i < 8; i++) {
            black[i] = new Piece(0, i, i, true);
            newBoard[0][i] = black[i];
        }

        return newBoard;
    }
}
