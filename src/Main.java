import java.util.*;

public class Main {
    public static void main(String[] args) {
        Board chessBoard = new Board();
        Scanner sc = new Scanner(System.in);
        String input;

        int moveNum = 1;
        boolean possibleMove;
        boolean gameOver = false;

        while(!gameOver) {
            System.out.print("Your Move #" + moveNum + " (H to help): ");
            input = sc.nextLine();

            if(input.equals("E")) {
                gameOver = true;
                sc.close();
            } else if(input.equals("P")) {
                chessBoard.printBoard();
            } else if(input.equals("H")) {
                help();
            } else {
                chessBoard.choosePiece(input);
                chessBoard.readPiece();
                chessBoard.listMoves();
            
                System.out.print("Choose where to move: ");
                input = sc.nextLine();
                possibleMove = chessBoard.makeMove(input);

                if(possibleMove) {
                    System.out.println("Move made.");
                } else {
                    System.out.println("Not a possible move.");
                }
            }

            System.out.println();
        }
    }

    public static void help() {
        System.out.println("Here are a list of commands:");
        System.out.println("E:             End the game \nP:             Print the board\n<coordinate>:  Choose a piece by location on board");
    }
}