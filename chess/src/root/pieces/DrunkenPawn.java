package root.pieces;

import root.Board;
import root.Move;
import root.PlayerType;

public class DrunkenPawn extends Pawn {

    public DrunkenPawn(PlayerType o) {
        super(o);
    }

    @Override
    public boolean isValidMove(Move move, Board board) {
        return super.isValidMove(move, board);
    }

    @Override
    public Piece makeOfficer(char requested, Board board) {
        return super.makeOfficer(requested, board);
    }
}
