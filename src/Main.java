import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Board chessBoard = new Board();
        Scanner sc = new Scanner(System.in);
        String input;

        int moveNum = 0;
        boolean gameOver = false;

        while(!gameOver) {
            chessBoard.printBoard();

            chessBoard.readPiece("e1");

            System.out.print("Type E to end game: ");
            input = sc.nextLine();

            if(input.equals("E")) {
                gameOver = true;
            }
        }
    }
}