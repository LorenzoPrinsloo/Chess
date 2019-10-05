package root.pieces;

import root.Board;
import root.Move;
import root.PlayerType;

public class Pawn implements Piece {

    PlayerType owner;

    public Pawn(PlayerType o) {
        this.owner = o;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {
        return false;
    }

    public Piece makeOfficer() {
        return new King(owner);
    }

    @Override
    public PlayerType getOwner() {
        return this.owner;
    }
}
