package root;

import root.pieces.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            Scanner boardScanner = new Scanner(new File("/Users/cmlprinsloogmail.com/Documents/Java/Chess/chess/src/root/input/onlyForTest.txt"));  // Create a Scanner object
            Board board = ScannerInput.readBoard(boardScanner);
//
//            for(List<Piece> row: board.boardMatrix) {
//                for(Piece piece: row){
//                    if(piece != null) {
//
//                        System.out.println(piece.getClass() + " "+ piece.getOwner());
//                    } else {
//                        System.out.println("SPACE");
//                    }
//                }
//            }
//
//            System.out.println(board.pieceCount);
//            System.out.println("BLACK: King - "+ board.blackStatus.hasKingCostling + " Queen - "+ board.blackStatus.hasQueenCostling);
//            System.out.println("WHITE: King - "+ board.whiteStatus.hasKingCostling + " Queen - "+ board.whiteStatus.hasQueenCostling);
//            System.out.println(board.halfMoveClock);
//            System.out.println(board.moveCounter);
//
//
//            Dragon d = new Dragon(PlayerType.BLACK);
//
//            board.setEntryInBoardMatrix(9,9, d);
//
//            for(List<Piece> row: board.boardMatrix) {
//                for(Piece piece: row){
//                    if(piece != null) {
//
//                        System.out.println(piece.getClass() + " "+ piece.getOwner());
//                    } else {
//                        System.out.println("SPACE");
//                    }
//                }
//            }

            King k = new King(PlayerType.BLACK);
            Position kingPos = new Position(5, 9);

            System.out.println("CheckMate "+ k.isInCheckMate(kingPos, board));

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
