package root.pieces;

import root.Board;
import root.Move;
import root.PieceType;
import root.PlayerType;

public abstract class Piece {

    public PlayerType owner;

    public PieceType type;

    abstract public boolean isValidMove(Move move, Board board);

    public PlayerType getOwner() {
        return owner;
    }

    public PieceType getType() {
        return type;
    }
}
