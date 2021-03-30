package ru.job4j.poly;

public class Bus implements Transport{
    private final double PRICE = 44.5;
    private int passengers = 0;
    private boolean engine = false;

    @Override
    public void run() {
        engine = true;
    }

    @Override
    public void boarding (int passengers) {
        this.passengers += passengers;
    }

    @Override
    public double refuel(int litres) {
        return PRICE * litres;
    }
}
