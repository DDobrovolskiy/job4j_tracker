package ru.job4j.tracker;

public interface Observer<T> {
    void receive(T model);
}
