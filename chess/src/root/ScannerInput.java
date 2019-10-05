package root;

import root.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static root.Utils.columnPosition;

public class ScannerInput {

    public static List<Move> readMoves(Scanner scanner) {
        List<Move> moves = new ArrayList<>();

        while(scanner.hasNext()) {
            String[] positions = scanner.nextLine().split("-");

            char fromAlphaCharacter = positions[0].charAt(0);
            char toAlphaCharacter = positions[1].charAt(0);

            int fromColumn = columnPosition(fromAlphaCharacter);
            int toColumn = columnPosition(toAlphaCharacter);

            int fromRow = Integer.parseInt(positions[0].substring(1, 2));
            int toRow = Integer.parseInt(positions[1].substring(1, 2));

            Move m = new Move(new Position(fromColumn, fromRow), new Position(toColumn, toRow));

            moves.add(m);
        }

        return moves;
    }

    public static Piece[][] readBoard(Scanner scanner) {

        char[][] boardAsChars = new char[10][10];

        int row = 0;
        int column = 0;

        while(scanner.hasNext()) {
            column = 0;

            String[] chars = scanner.nextLine().split(" ");

            for(String c: chars) {
                boardAsChars[row][column] = c.charAt(0);
                column ++;
            }

            row++;
        }

        Piece[][] boardAsPieces = new Piece[10][10];

        row = 0;

        for(char[] r: boardAsChars) {
            column = 0;
            for(char piece: r){


            switch(piece) {
                case 'K' : boardAsPieces[row][column] = new King(PlayerType.BLACK); break;
                case 'k' : boardAsPieces[row][column] = new King(PlayerType.WHITE); break;
                case 'Q' : boardAsPieces[row][column] = new Queen(PlayerType.BLACK); break;
                case 'q' : boardAsPieces[row][column] = new Queen(PlayerType.WHITE); break;
                case 'A' : boardAsPieces[row][column] = new Amazon(PlayerType.BLACK); break;
                case 'a' : boardAsPieces[row][column] = new Amazon(PlayerType.WHITE); break;
                case 'F' : boardAsPieces[row][column] = new Dragon(PlayerType.BLACK); break;
                case 'f' : boardAsPieces[row][column] = new Dragon(PlayerType.WHITE); break;
                case 'D' : boardAsPieces[row][column] = new DrunkenPawn(PlayerType.BLACK); break;
                case 'd' : boardAsPieces[row][column] = new DrunkenPawn(PlayerType.WHITE); break;
                case 'B' : boardAsPieces[row][column] = new Bishop(PlayerType.BLACK); break;
                case 'b' : boardAsPieces[row][column] = new Bishop(PlayerType.WHITE); break;
                case 'E' : boardAsPieces[row][column] = new Elephant(PlayerType.BLACK); break;
                case 'e' : boardAsPieces[row][column] = new Elephant(PlayerType.WHITE); break;
                case 'N' : boardAsPieces[row][column] = new Knight(PlayerType.BLACK); break;
                case 'n' : boardAsPieces[row][column] = new Knight(PlayerType.WHITE); break;
                case 'P' : boardAsPieces[row][column] = new Pawn(PlayerType.BLACK); break;
                case 'p' : boardAsPieces[row][column] = new Pawn(PlayerType.WHITE); break;
                case 'W' : boardAsPieces[row][column] = new Princess(PlayerType.BLACK); break;
                case 'w' : boardAsPieces[row][column] = new Princess(PlayerType.WHITE); break;
                case 'R' : boardAsPieces[row][column] = new Rook(PlayerType.BLACK); break;
                case 'r' : boardAsPieces[row][column] = new Rook(PlayerType.WHITE); break;
                default : boardAsPieces[row][column] = null; break;
            };
                column++;
            }
            row++;
        }

        return boardAsPieces;
    }
}
