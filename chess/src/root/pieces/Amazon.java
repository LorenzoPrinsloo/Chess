package root.pieces;

import root.Board;
import root.Move;
import root.PlayerType;
import root.Utils;

public class Amazon extends Knight {

    PlayerType owner;

    public Amazon(PlayerType o) {
        super(o);
        this.owner = o;
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

    @Override
    public PlayerType getOwner() {
        return this.owner;
    }
}
