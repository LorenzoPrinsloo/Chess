package root.pieces;

import com.sun.tools.javac.util.Pair;
import root.Board;
import root.Move;
import root.PlayerType;

public class Knight implements Piece {

    PlayerType owner;

    public Knight(PlayerType o) {
        this.owner = o;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {

        if(isLShapedMove(move)){
            int rowDest = move.getTo().getRow();
            int colDest = move.getTo().getColumn();

            if(board.getBoardMatrix().get(rowDest).get(colDest) == null || board.getBoardMatrix().get(rowDest).get(colDest).getOwner() != owner) {
                return true;
            } else return false;
        } else return false;
    }

    @Override
    public PlayerType getOwner() {
        return this.owner;
    }

    /**
     * Calculate the step needed to navigate through matrix in a specific direction
     */
    public static Pair<Integer, Integer> calcStep(Move move) {
        Integer rowStep = move.getTo().getRow() - move.getFrom().getRow();
        Integer colStep = move.getTo().getColumn() - move.getFrom().getColumn();

        return new Pair<>(rowStep, colStep);
    }

    public static boolean isLShapedMove(Move move) {
        Integer rowStep = Math.abs(calcStep(move).fst);
        Integer colStep = Math.abs(calcStep(move).snd);

        if( (rowStep == 2 && colStep == 1 ) || (colStep == 2 && rowStep == 1)){
            return true;
        } else return false;
    }
}
