package root.pieces;

import root.Board;
import root.Move;
import root.PlayerType;

public interface Piece {

    public PlayerType owner = PlayerType.NOT_SET;

    public boolean isValidMove(Move move, Board board);

    public PlayerType getOwner();
}
