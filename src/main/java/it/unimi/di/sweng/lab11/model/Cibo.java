package it.unimi.di.sweng.lab11.model;

public record Cibo(String name) {
    public Cibo {
        if (name.isBlank()) throw new IllegalArgumentException("INVALID CIBO BRO");
    }
}
