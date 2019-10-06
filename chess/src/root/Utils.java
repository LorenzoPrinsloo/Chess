package root;

import com.sun.tools.javac.util.Pair;
import root.pieces.King;
import root.pieces.Piece;
import root.pieces.Space;
import java.util.List;

public class Utils {

    private static final String columnAlphabet = "abcdefghij";

    public static int columnPosition(char inputChar) {
        return columnAlphabet.indexOf(inputChar);
    }

    public static char[] toArray(List<Character> chars) {
        char[] charArray = new char[chars.size()];

        int i = 0;
        for(char c: chars) {
            charArray[i] = c;
            i++;
        }

        return charArray;
    }

    public static boolean isBetween(int start, int finish, int value) {
        if (start <= value && value <= finish) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculate the step needed to navigate through matrix in a specific direction
     */
    public static Pair<Integer, Integer> calcStep(Move move) {
        Integer rowStep = toStep(move.getTo().getRow() - move.getFrom().getRow());
        Integer colStep = toStep(move.getTo().getColumn() - move.getFrom().getColumn());

        return new Pair<>(rowStep, colStep);
    }

    /**
     * Convert difference in number to a step for a array/iterator
     */
    private static Integer toStep(Integer i){
        if(i > 0){
            return 1;
        } else if(i < 0){
            return -1;
        } else {
            return 0;
        }
    }

    public static boolean isDiagonalMove(Move move) {
        Integer left = Math.abs(move.getFrom().getRow() - move.getTo().getRow());
        Integer right = Math.abs(move.getFrom().getColumn() - move.getTo().getColumn());

        return left == right;
    }

    public static boolean isLongitudinalMove(Move move){
        Integer rowDif = move.getFrom().getRow() - move.getTo().getRow();
        Integer colDif = move.getFrom().getColumn() - move.getTo().getColumn();

        if(rowDif == 0 || colDif == 0){
            return true;
        } else return false;
    }

    public static boolean isValidLongitudinalMove(Move move, Board board, PlayerType owner) {
        // fst is rowStep and snd is colStep
        Pair<Integer, Integer> step = Utils.calcStep(move);
        List<List<Piece>> boardMatrix = board.getBoardMatrix();

        int rowCurrent = move.getFrom().getRow() + step.fst; // this is done so first block checked isn't the piece itself
        int colCurrent = move.getFrom().getColumn() + step.snd;

        int rowDest = move.getTo().getRow();
        int colDest = move.getTo().getColumn();

        while(rowCurrent <= 9 && colCurrent <= 9){
            if((isSpace(boardMatrix.get(rowCurrent).get(colCurrent)) || boardMatrix.get(rowCurrent).get(colCurrent).getOwner() != owner) && rowCurrent == rowDest && colCurrent == colDest) {
                return true;
            } else if(!isSpace(boardMatrix.get(rowCurrent).get(colCurrent))) {
                return false;
            }

            rowCurrent = rowCurrent + step.fst;
            colCurrent = colCurrent + step.snd;
        }
        return true;
    }

    public static boolean isValidDiagonalMove(Move move, Board board, PlayerType owner) {
        // fst is rowStep and snd is colStep
        Pair<Integer, Integer> step = Utils.calcStep(move);
        List<List<Piece>> boardMatrix = board.getBoardMatrix();

        int rowCurrent = move.getFrom().getRow() + step.fst; // this is done so first block checked isn't the piece itself
        int colCurrent = move.getFrom().getColumn() + step.snd;

        int rowDest = move.getTo().getRow();
        int colDest = move.getTo().getColumn();

        while(rowCurrent <= 9 && colCurrent <= 9){
            System.out.println("row: " +rowCurrent + " col: "+colCurrent);

            if((isSpace(boardMatrix.get(rowCurrent).get(colCurrent)) || boardMatrix.get(rowCurrent).get(colCurrent).getOwner() != owner) && rowCurrent == rowDest && colCurrent == colDest) {
                System.out.println("Reached Location");
                return true;
            } else if(!isSpace(boardMatrix.get(rowCurrent).get(colCurrent))) {

                System.out.println("Got Blocked");
                return false;
            }

            rowCurrent = rowCurrent + step.fst;
            colCurrent = colCurrent + step.snd;
        }

        System.out.println("prematurely exit");
        return true;
    }

    public static Position findPositionOnBoard(final Piece piece, List<List<Piece>> matrix) throws Exception {

        Position pos = new Position(0,0);

        for(List<Piece> row: matrix){

            pos.setColumn(0);

            for(Piece p: row){
                if(p.hashCode() == piece.hashCode()){
                    return pos;
                }
                pos.incrementColumn();
            }

            pos.incrementRow();
        }

        // Hasnt found the piece on the board which shouldnt happen so we throw a exception
        throw new Exception("No Piece found on Board for "+ piece);
    }

    public static boolean isSpace(Piece p) {
        return p.getClass() == Space.class;
    }

    public static boolean isKing(Piece p) {
        return p.getClass() == King.class;
    }

    public static Piece getPiece(int row, int column, List<List<Piece>> matrix) {
        return matrix.get(row).get(column);
    }

    public static PlayerType nextPlayer(PlayerType currentPlayer) {
        if(currentPlayer == PlayerType.BLACK){
            return PlayerType.WHITE;
        } else return PlayerType.BLACK;
    }

    public static boolean isSamePosition(Position p1, Position p2){
        if(p1.getRow() == p2.getRow() && p1.getColumn() == p2.getColumn()){
            return true;
        } else {
            return false;
        }
    }

}
