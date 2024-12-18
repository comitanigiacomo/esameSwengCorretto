package it.unimi.di.sweng.lab11.presenter;

import it.unimi.di.sweng.lab11.Observable;
import it.unimi.di.sweng.lab11.Observer;
import it.unimi.di.sweng.lab11.model.Model;
import it.unimi.di.sweng.lab11.model.QuantitaCibo;
import it.unimi.di.sweng.lab11.presenter.stampastrategy.StampaStrategy;
import it.unimi.di.sweng.lab11.view.CommandView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CompratorePresenter implements InputPresenter, Observer<List<QuantitaCibo>> {

    private final Model model;
    private final CommandView view;
    private final StampaStrategy strategy;


    public CompratorePresenter(Model model, CommandView view, StampaStrategy strategy) {
        this.model = model;
        this.view = view;
        this.strategy = strategy;
        view.addHandlers(this);
        model.addObserver(this);
    }

    @Override
    public void action(@NotNull String text, @NotNull String text1) {
        model.buyCibo(text);
    }

    @Override
    public void update(@NotNull Observable<List<QuantitaCibo>> subject) {
        List<QuantitaCibo> cibiDaComprare = model.getStateToBuy();

        for (int i = 0; i < 8; i++){
            if (i < cibiDaComprare.size()){
                view.set(i, cibiDaComprare.get(i).cibo().name());
            }else{
                view.set(i, "---");
            }
        }
    }
}
