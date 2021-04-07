package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int rsl = 0;
        String[] o1array = o1.split("/");
        String[] o2array = o2.split("/");
        rsl = o2array[0].compareTo(o1array[0]);
        if (rsl == 0) {
            for (int i = 1; i < Math.min(o1array.length, o2array.length); i++) {
                rsl = o1array[i].compareTo(o2array[i]);
                if (rsl != 0) {
                    return rsl;
                }
            }
        } else {
            return rsl;
        }
        return Integer.compare(o1array.length, o2array.length);
    }
}
