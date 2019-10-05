package root;

import root.pieces.Piece;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            Scanner boardScanner = new Scanner(new File("/Users/cmlprinsloogmail.com/Documents/Java/Chess/chess/src/root/input/board.txt"));  // Create a Scanner object
            Piece[][] board = ScannerInput.readBoard(boardScanner);

            for(Piece[] row: board) {
                for(Piece piece: row){
                    if(piece != null) {

                        System.out.println(piece.getClass() + " "+ piece.getOwner());
                    } else {
                        System.out.println("SPACE");
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
