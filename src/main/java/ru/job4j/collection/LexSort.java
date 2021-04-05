package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String leftSplit = left.split("\\.")[0];
        String rightSplit = right.split("\\.")[0];
        int rsl = Integer.compare(Integer.parseInt(leftSplit), Integer.parseInt(rightSplit));
        if (rsl != 0) {
            return rsl;
        }
        return Integer.compare(leftSplit.length(), rightSplit.length());
    }
}