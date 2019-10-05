package main.pieces;

import main.Board;
import main.Move;
import main.Utils;

public class King implements Piece {

    public King(){
    }

    @Override
    public boolean isValidMove(Move move, Board board) {

        // has only moved one space in any direction
        int rowDiff = Math.abs(move.getFrom().getRow() - move.getFrom().getRow());
        int colDiff = Math.abs(move.getFrom().getColumn() - move.getFrom().getColumn());

        if(Utils.isBetween(0,1, rowDiff) && Utils.isBetween(0,1, colDiff)) {

            if(board.getBoardMatrix()[move.getTo().getRow()][move.getTo().getColumn()] == '.'){

            }
            return true; //Default atm change this
        } else {

            return false;
        }

    }
}
