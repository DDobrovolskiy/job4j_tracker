package ru.job4j.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        Map<String, Integer> mapLeft = new HashMap<>();
        Map<String, Integer> mapRight = new HashMap<>();
        Arrays.asList(left.split("")).stream()
                .forEach(item -> {
                    mapLeft.computeIfPresent(item, (key, value) -> value + 1);
                    mapLeft.putIfAbsent(item, 1);
                });
        Arrays.asList(right.split("")).stream()
                .forEach(item -> {
                    mapRight.computeIfPresent(item, (key, value) -> value + 1);
                    mapRight.putIfAbsent(item, 1);
                });
        for (String key : mapRight.keySet()) {
            if (mapLeft.containsKey(key)) {
                if (mapLeft.get(key) < mapRight.get(key)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
