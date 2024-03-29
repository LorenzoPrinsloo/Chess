package root.pieces;

import com.sun.tools.javac.util.Pair;
import root.*;

public class Knight implements Piece {

    PlayerType owner;
    PieceType type = PieceType.KNIGHT;

    public Knight(PlayerType o) {
        this.owner = o;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {

        if(isLShapedMove(move)){
            int rowDest = move.getTo().getRow();
            int colDest = move.getTo().getColumn();

            if(Utils.isSpace(board.getBoardMatrix().get(rowDest).get(colDest)) || board.getBoardMatrix().get(rowDest).get(colDest).getOwner() != owner) {
                return true;
            } else return false;
        } else return false;
    }

    @Override
    public PlayerType getOwner() {
        return this.owner;
    }

    @Override
    public PieceType getType() {
        return this.type;
    }

    /**
     * Calculate the step needed to navigate through matrix in a specific direction
     */
    public static Pair<Integer, Integer> calcStep(Move move) {
        Integer rowStep = move.getTo().getRow() - move.getFrom().getRow();
        Integer colStep = move.getTo().getColumn() - move.getFrom().getColumn();

        return new Pair<>(rowStep, colStep);
    }

    public boolean isLShapedMove(Move move) {
        Integer rowStep = Math.abs(calcStep(move).fst);
        Integer colStep = Math.abs(calcStep(move).snd);

        if( (rowStep == 2 && colStep == 1 ) || (colStep == 2 && rowStep == 1)){
            return true;
        } else return false;
    }
}
