package root.pieces;

import root.*;

import static root.Utils.isDiagonalMove;

public class Bishop implements Piece {

    PlayerType owner;
    PieceType type = PieceType.BISHOP;

    public Bishop(PlayerType o) {
        this.owner = o;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {
        if(isDiagonalMove(move)){
            return Utils.isValidDiagonalMove(move, board, owner);
        } else { System.out.println("Not Diagonal Move"); return false;}
    }

    @Override
    public PlayerType getOwner() {
        return this.owner;
    }

    @Override
    public PieceType getType() {
        return this.type;
    }
}
