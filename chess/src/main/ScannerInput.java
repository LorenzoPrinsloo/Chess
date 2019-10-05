package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static main.Utils.columnPosition;

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

    public static char[][] readBoard(Scanner scanner) {

        char[][] board = new char[10][10];

        int row = 0;

        while(scanner.hasNext()) {
            int column = 0;

            String[] chars = scanner.nextLine().split(" ");

            for(String c: chars) {
                board[row][column] = c.charAt(0);
                column ++;
            }

            row++;
        }

        return board;
    }
}
