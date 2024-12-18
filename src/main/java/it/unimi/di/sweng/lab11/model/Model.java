package it.unimi.di.sweng.lab11.model;

import it.unimi.di.sweng.lab11.Observable;
import it.unimi.di.sweng.lab11.Observer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model implements IState, Observable<List<QuantitaCibo>> {

    private final Map<Cibo, QuantitaCibo> toBuy = new HashMap<>();
    private final Map<Cibo, QuantitaCibo> buyed = new HashMap<>();
    private final List<Observer<List<QuantitaCibo>>> observers = new ArrayList<>();

    public void addCibo(String nome, int i) {
        Cibo cibo = new Cibo(nome);
        Quantita quantita = new Quantita(i);
        toBuy.put(cibo, new QuantitaCibo(cibo,quantita));
        notifyObservers();
    }

    public void buyCibo(String nome){
        Cibo cibo = new Cibo(nome);

        QuantitaCibo quantitaCibo = toBuy.get(cibo);
        if (quantitaCibo == null) {
            throw new IllegalArgumentException("Il cibo '" + nome + "' non è nella lista da acquistare.");
        }

        int quantitaCorrente = quantitaCibo.quantita().quantita();

        if (quantitaCorrente == 1) {
            toBuy.remove(cibo);
        } else {
            toBuy.put(cibo, new QuantitaCibo(cibo, new Quantita(quantitaCorrente - 1)));
        }

        buyed.put(cibo, new QuantitaCibo(cibo,
                new Quantita(buyed.getOrDefault(cibo, new QuantitaCibo(cibo, new Quantita(0))).quantita().quantita() + 1)));

        notifyObservers();
    }

    public List<QuantitaCibo> getToBuy() {
        return List.copyOf(toBuy.values());
    }
    public List<QuantitaCibo> getBuyed() {
        return List.copyOf(buyed.values());
    }

    @Override
    public void notifyObservers() {
        for (Observer<List<QuantitaCibo>> obs : observers) {
            obs.update(this);
        }
    }

    @Override
    public void addObserver(@NotNull Observer<List<QuantitaCibo>> obs) {
        observers.add(obs);
    }

    @Override
    public @NotNull List<QuantitaCibo> getStateToBuy() {
        return getToBuy();
    }

    @Override
    public @NotNull List<QuantitaCibo> getStateBuyed() {
        return getBuyed();
    }
}
