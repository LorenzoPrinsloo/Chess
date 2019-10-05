package root.pieces;

import root.Board;
import root.Move;
import root.PlayerType;

public class Elephant implements Piece {

    PlayerType owner;

    public Elephant(PlayerType o) {
        this.owner = o;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {
        return false;
    }

    @Override
    public PlayerType getOwner() {
        return this.owner;
    }
}
