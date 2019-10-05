package main.pieces;

import main.Board;
import main.Move;

public interface Piece {
    public boolean isValidMove(Move move, Board board);

    public Piece makeOfficer();
}
