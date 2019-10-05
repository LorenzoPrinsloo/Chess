package root.pieces;

import root.Board;
import root.Move;
import root.PlayerType;

public class Dragon extends Bishop {

    PlayerType owner;

    public Dragon(PlayerType o) {
        super(o);
        this.owner = o;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {
        Integer rowDiff = Math.abs(move.getTo().getRow() - move.getFrom().getRow());

        if(rowDiff > 2){
            return false;
        } else {
            return super.isValidMove(move, board);
        }
    }

    @Override
    public PlayerType getOwner() {
        return this.owner;
    }
}
