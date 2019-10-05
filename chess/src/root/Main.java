package root;

import root.pieces.Dragon;
import root.pieces.Piece;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            Scanner boardScanner = new Scanner(new File("/Users/cmlprinsloogmail.com/Documents/Java/Chess/chess/src/root/input/board.txt"));  // Create a Scanner object
            Board board = ScannerInput.readBoard(boardScanner);

            for(List<Piece> row: board.boardMatrix) {
                for(Piece piece: row){
                    if(piece != null) {

                        System.out.println(piece.getClass() + " "+ piece.getOwner());
                    } else {
                        System.out.println("SPACE");
                    }
                }
            }

            System.out.println(board.pieceCount);
            System.out.println("BLACK: King - "+ board.blackStatus.hasKingCostling + " Queen - "+ board.blackStatus.hasQueenCostling);
            System.out.println("WHITE: King - "+ board.whiteStatus.hasKingCostling + " Queen - "+ board.whiteStatus.hasQueenCostling);
            System.out.println(board.halfMoveClock);
            System.out.println(board.moveCounter);


            Dragon d = new Dragon(PlayerType.BLACK);

            board.setEntryInBoardMatrix(9,9, d);

            for(List<Piece> row: board.boardMatrix) {
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
