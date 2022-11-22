package com.example.reactivefirst.temperature.domain;

public final class Temperature {
    private final double value;

    public Temperature(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
