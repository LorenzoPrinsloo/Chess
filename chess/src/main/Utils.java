package main;

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
}
