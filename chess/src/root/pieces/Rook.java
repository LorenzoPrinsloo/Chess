package root.pieces;

import root.Board;
import root.Move;
import root.PlayerType;
import root.Utils;

import static root.Utils.isLongitudinalMove;

// AKKA Castle
public class Rook implements Piece {

    PlayerType owner;

    public Rook(PlayerType o) {
        this.owner = o;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {

        if(isLongitudinalMove(move)) {
            return Utils.isValidLongitudinalMove(move, board, owner);
        } else return false;
    }

    @Override
    public PlayerType getOwner() {
        return this.owner;
    }
}
