package root.pieces;

import com.sun.tools.javac.util.Pair;
import root.Board;
import root.Move;
import root.PlayerType;
import root.Utils;

import java.util.List;

public class Bishop implements Piece {

    PlayerType owner;

    public Bishop(PlayerType o) {
        this.owner = o;
    }

    public boolean isDiagonalMove(Move move) {
        Integer left = Math.abs(move.getFrom().getRow() - move.getTo().getRow());
        Integer right = Math.abs(move.getFrom().getColumn() - move.getTo().getColumn());

        return left == right;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {

        boolean reachedLocation = false;

        if(isDiagonalMove(move)){
            int rowCurrent = move.getFrom().getRow();
            int colCurrent = move.getTo().getColumn();

            int rowDest = move.getTo().getRow();
            int colDest = move.getTo().getColumn();

            // fst is rowStep and snd is colStep
            Pair<Integer, Integer> step = Utils.calcStep(move);
            List<List<Piece>> boardMatrix = board.getBoardMatrix();

            while(!reachedLocation){

                if((boardMatrix.get(rowCurrent).get(colCurrent) == null || boardMatrix.get(rowCurrent).get(colCurrent).getOwner() != owner) && rowCurrent == rowDest && colCurrent == colDest) {
                    reachedLocation = true;
                } else if(boardMatrix.get(rowCurrent).get(colCurrent) != null) {
                    return false;
                }

                rowCurrent = rowCurrent + step.fst;
                colCurrent = colCurrent + step.snd;
            }
            return true; // This should never happen
        } else return false;
    }

    @Override
    public PlayerType getOwner() {
        return this.owner;
    }
}
