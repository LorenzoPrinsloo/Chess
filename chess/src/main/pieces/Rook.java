package main.pieces;

import main.Board;
import main.Move;

public class Rook implements Piece {

    @Override
    public boolean isValidMove(Move move, Board board) {
        return false;
    }

    @Override
    public Piece makeOfficer() {
        return null;
    }
}