package root;

import com.sun.tools.javac.util.Pair;
import root.pieces.Piece;

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
        boolean reachedLocation = false;

        // fst is rowStep and snd is colStep
        Pair<Integer, Integer> step = Utils.calcStep(move);
        List<List<Piece>> boardMatrix = board.getBoardMatrix();

        int rowCurrent = move.getFrom().getRow() + step.fst; // this is done so first block checked isn't the piece itself
        int colCurrent = move.getFrom().getColumn() + step.snd;

        int rowDest = move.getTo().getRow();
        int colDest = move.getTo().getColumn();

        while(!reachedLocation){

            if((boardMatrix.get(rowCurrent).get(colCurrent) == null || boardMatrix.get(rowCurrent).get(colCurrent).getOwner() != owner) && rowCurrent == rowDest && colCurrent == colDest) {
                reachedLocation = true;
            } else if(boardMatrix.get(rowCurrent).get(colCurrent) != null) {
                return false;
            }

            rowCurrent = rowCurrent + step.fst;
            colCurrent = colCurrent + step.snd;
        }
        return true;
    }

    public static boolean isValidDiagonalMove(Move move, Board board, PlayerType owner) {
        boolean reachedLocation = false;

        // fst is rowStep and snd is colStep
        Pair<Integer, Integer> step = Utils.calcStep(move);
        List<List<Piece>> boardMatrix = board.getBoardMatrix();

        int rowCurrent = move.getFrom().getRow() + step.fst; // this is done so first block checked isn't the piece itself
        int colCurrent = move.getFrom().getColumn() + step.snd;

        int rowDest = move.getTo().getRow();
        int colDest = move.getTo().getColumn();

        while(!reachedLocation){
            System.out.println("row: " +rowCurrent + " col: "+colCurrent);

            if((boardMatrix.get(rowCurrent).get(colCurrent) == null || boardMatrix.get(rowCurrent).get(colCurrent).getOwner() != owner) && rowCurrent == rowDest && colCurrent == colDest) {
                System.out.println("Reached Location");
                reachedLocation = true;
            } else if(boardMatrix.get(rowCurrent).get(colCurrent) != null) {

                System.out.println("Got Blocked");
                return false;
            }

            rowCurrent = rowCurrent + step.fst;
            colCurrent = colCurrent + step.snd;
        }

        System.out.println("prematurely exit");
        return true;
    }
}
