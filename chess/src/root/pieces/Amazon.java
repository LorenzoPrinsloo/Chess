package root.pieces;

import root.*;

public class Amazon extends Knight {

    public Amazon(PlayerType o) {
        super(o);
        type = PieceType.AMAZON;
        owner = o;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {
        if(super.isLShapedMove(move)) {
            return super.isValidMove(move, board);
        } if(Utils.isDiagonalMove(move)){
            return Utils.isValidDiagonalMove(move, board, owner);
        } else { // We know this will be a vertical or horizontal
            return Utils.isValidLongitudinalMove(move, board, owner);
        }
    }
}
