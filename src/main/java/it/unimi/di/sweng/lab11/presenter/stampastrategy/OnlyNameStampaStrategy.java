package it.unimi.di.sweng.lab11.presenter.stampastrategy;

import it.unimi.di.sweng.lab11.model.QuantitaCibo;

public class OnlyNameStampaStrategy implements StampaStrategy {
    @Override
    public String stampa(QuantitaCibo quantitaCibo) {
        return String.format("%s",
                quantitaCibo.cibo().name());
    }
}
