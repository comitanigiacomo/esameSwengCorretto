package it.unimi.di.sweng.lab11.presenter.stampastrategy;

import it.unimi.di.sweng.lab11.model.QuantitaCibo;

public class WithQuantityStampaStrategy implements StampaStrategy {

    @Override
    public String stampa(QuantitaCibo quantitaCibo) {
        return String.format("%s %s",
                quantitaCibo.cibo().name(), quantitaCibo.quantita().quantita());
    }
}
