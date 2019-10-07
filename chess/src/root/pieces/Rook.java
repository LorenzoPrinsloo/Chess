package root.pieces;

import root.*;

import static root.Utils.isLongitudinalMove;

// AKKA Castle
public class Rook extends Piece {

    public Rook(PlayerType o) {
        owner = o;
        type = PieceType.ROOK;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {

        if(isLongitudinalMove(move)) {
            return Utils.isValidLongitudinalMove(move, board, owner);
        } else return false;
    }
}
