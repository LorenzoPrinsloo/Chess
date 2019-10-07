package root;

import com.sun.deploy.util.BlackList;
import root.pieces.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            Scanner boardScanner = new Scanner(new File("/Users/cmlprinsloogmail.com/Documents/Java/Chess/chess/src/root/input/board.txt"));
            Scanner moveScanner = new Scanner(new File("/Users/cmlprinsloogmail.com/Documents/Java/Chess/chess/src/root/input/testMove.txt"));

            Board board = ScannerInput.readBoard(boardScanner);
            List<Move> moves = ScannerInput.readMoves(moveScanner);

            int line = 1;
            for(Move move: moves){
                board.move(move, line);
                line++;
            }

            System.out.println("AFTER");
            board.printBoard();

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
