package root.pieces;

import root.*;

import static root.Utils.isDiagonalMove;

public class Bishop extends Piece {

    public Bishop(PlayerType o) {
       owner = o;
       type = PieceType.BISHOP;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {
        if(isDiagonalMove(move)){
            return Utils.isValidDiagonalMove(move, board, owner);
        } else { return false;}
    }
}
