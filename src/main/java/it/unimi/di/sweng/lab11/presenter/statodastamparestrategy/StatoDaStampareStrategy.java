package it.unimi.di.sweng.lab11.presenter.statodastamparestrategy;

import it.unimi.di.sweng.lab11.model.Model;
import it.unimi.di.sweng.lab11.model.QuantitaCibo;

import java.util.List;

public interface StatoDaStampareStrategy {

    public List<QuantitaCibo> getStatoDaStampare(Model model);

}
