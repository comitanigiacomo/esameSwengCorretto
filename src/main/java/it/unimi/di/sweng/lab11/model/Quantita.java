package it.unimi.di.sweng.lab11.model;

public record Quantita(int quantita) {
    public Quantita {
        // if (quantita <= 0) throw new IllegalArgumentException("INVALID QUANTITA: sotto zero");
    }

    public static Quantita fromString(String quantita) {
        try {
            Integer.parseInt(quantita);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("INVALID QUANTITA: non numero");
        }
        return new Quantita(Integer.parseInt(quantita));
    }
}
