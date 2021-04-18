package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EasyStream {

    //private Consumer<Integer> writeValue;
    private final Consumer<Consumer<Integer>> source;

    private EasyStream(Consumer<Consumer<Integer>> source) {
        this.source = source;
    }

    public static EasyStream of(List<Integer> list) {
        return new EasyStream(
                method -> list.forEach(value -> method.accept(value)));
    }

    public EasyStream map(Function<Integer, Integer> fun) {
        return new EasyStream(method -> this.source.accept(
                value -> method.accept(fun.apply(value))));
    }

    public EasyStream filter(Predicate<Integer> fun) {
        return new EasyStream(method -> this.source.accept(value -> {
            if (fun.test(value)) {
                method.accept(value);
            }
        }));
    }

    public List<Integer> collect() {
        List<Integer> rsl = new ArrayList<>();
        this.source.accept(value -> rsl.add(value));
        return rsl;
    }
}
