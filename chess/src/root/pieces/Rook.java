package root.pieces;

import root.*;

import static root.Utils.isLongitudinalMove;

// AKKA Castle
public class Rook implements Piece {

    PlayerType owner;

    PieceType type = PieceType.ROOK;

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

    @Override
    public PieceType getType() {
        return this.type;
    }
}
