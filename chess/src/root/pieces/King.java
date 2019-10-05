package root.pieces;

import root.Board;
import root.Move;
import root.PlayerType;
import root.Utils;

import java.util.stream.Collectors;

public class King implements Piece {

    PlayerType owner;

    public King(PlayerType o) {
        this.owner = o;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {
        // To OF King
        // From of Piece TODO find out how to calc this

//
//        board.getBoardMatrix().stream().flatMap(t -> t.stream())
//                .filter(piece -> piece.getOwner() != owner)
//                .map(p -> p.isValidMove(move ,board)).findFirst().isPresent();

        // has only moved one space in any direction
        int rowDiff = Math.abs(move.getFrom().getRow() - move.getFrom().getRow());
        int colDiff = Math.abs(move.getFrom().getColumn() - move.getFrom().getColumn());

        if(Utils.isBetween(0,1, rowDiff) && Utils.isBetween(0,1, colDiff)) {

            if(board.getBoardMatrix().get(move.getTo().getRow()).get(move.getTo().getColumn()) == null){

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
