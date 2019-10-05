package root.pieces;

import root.Board;
import root.Move;
import root.PlayerType;
import root.Utils;

public class King implements Piece {

    PlayerType owner;

    public King(PlayerType o) {
        this.owner = o;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {

        // has only moved one space in any direction
        int rowDiff = Math.abs(move.getFrom().getRow() - move.getFrom().getRow());
        int colDiff = Math.abs(move.getFrom().getColumn() - move.getFrom().getColumn());

        if(Utils.isBetween(0,1, rowDiff) && Utils.isBetween(0,1, colDiff)) {

            if(board.getBoardMatrix()[move.getTo().getRow()][move.getTo().getColumn()] == null){

            }
            return true; //Default atm change this
        } else {

            return false;
        }

    }

    @Override
    public PlayerType getOwner() {
        return this.owner;
    }
}
