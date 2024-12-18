package it.unimi.di.sweng.lab11.presenter;

import it.unimi.di.sweng.lab11.Observable;
import it.unimi.di.sweng.lab11.Observer;
import it.unimi.di.sweng.lab11.model.Model;
import it.unimi.di.sweng.lab11.model.QuantitaCibo;
import it.unimi.di.sweng.lab11.presenter.stampastrategy.StampaStrategy;
import it.unimi.di.sweng.lab11.presenter.statodastamparestrategy.StatoDaStampareStrategy;
import it.unimi.di.sweng.lab11.view.DisplayView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VisualizzaListaPresenter implements Observer<List<QuantitaCibo>> {

    private final Model model;
    private final DisplayView view;
    private final StampaStrategy stampaStrategy;
    private final StatoDaStampareStrategy getStateStrategy;

    public VisualizzaListaPresenter(Model model, DisplayView view, StampaStrategy stampaStrategy, StatoDaStampareStrategy getStateStrategy) {
        this.model = model;
        this.view = view;
        this.stampaStrategy = stampaStrategy;
        this.getStateStrategy = getStateStrategy;
        model.addObserver(this);
    }


    @Override
    public void update(@NotNull Observable<List<QuantitaCibo>> subject) {
        List<QuantitaCibo> cibiComprati = getStateStrategy.getStatoDaStampare(model);

        for (int i = 0; i < 8; i++){
            if (i < cibiComprati.size()){
                view.set(i, stampaStrategy.stampa(cibiComprati.get(i)));
            }else{
                view.set(i, "");
            }
        }
    }
}
