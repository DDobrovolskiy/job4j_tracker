package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        char[] leftChars = left.toCharArray();
        char[] rightChars = right.toCharArray();
        int rsl = 0;
        for (int i = 0; i < Math.min(leftChars.length, rightChars.length); i++) {
            rsl = Character.compare(leftChars[i], rightChars[i]);
            if (rsl != 0) {
                return rsl;
            }
        }
        return Integer.compare(leftChars.length, rightChars.length);
    }
}
