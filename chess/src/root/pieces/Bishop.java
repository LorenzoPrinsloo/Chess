package root.pieces;

import root.Board;
import root.Move;
import root.PlayerType;
import root.Utils;

import static root.Utils.isDiagonalMove;

public class Bishop implements Piece {

    PlayerType owner;

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
}
