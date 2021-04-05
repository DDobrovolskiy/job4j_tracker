package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    public static void main(String[] args) {
        String test = "10. Task.";
        String[] testjh = test.split(" ");
        String[] testSecond = testjh[0].split("\\.");
        int t = Integer.parseInt(testSecond[0]);
    }

    @Override
    public int compare(String left, String right) {
        String[] leftSplit = left.split(" ")[0].split("\\.");
        String[] rightSplit = right.split(" ")[0].split("\\.");
        for (int i = 0; i < Math.min(leftSplit.length, rightSplit.length); i++) {
            int leftInt = Integer.parseInt(leftSplit[i]);
            int rightInt = Integer.parseInt(rightSplit[i]);
            int rsl = Integer.compare(leftInt, rightInt);
            if (rsl != 0) {
                return rsl;
            }
        }
        return Integer.compare(leftSplit.length, rightSplit.length);
    }
}