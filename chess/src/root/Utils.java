package root;

import com.sun.tools.javac.util.Pair;

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
}
