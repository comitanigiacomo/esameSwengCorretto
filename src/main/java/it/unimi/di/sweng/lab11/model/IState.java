package it.unimi.di.sweng.lab11.model;

import java.util.List;

public interface IState {
    List<QuantitaCibo> getToBuy();
    List<QuantitaCibo> getBuyed();
}
