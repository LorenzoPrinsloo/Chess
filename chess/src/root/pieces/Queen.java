package root.pieces;

import root.*;


public class Queen extends Piece {

    public Queen(PlayerType o) {
        type = PieceType.QUEEN;
        owner = o;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {

        if(Utils.isDiagonalMove(move)){
            return Utils.isValidDiagonalMove(move, board, owner);
        } else { // We know this will be a vertical or horizontal
           return Utils.isValidLongitudinalMove(move, board, owner);
        }
    }
}
