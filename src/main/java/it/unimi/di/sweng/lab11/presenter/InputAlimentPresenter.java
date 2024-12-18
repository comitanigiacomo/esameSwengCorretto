package it.unimi.di.sweng.lab11.presenter;

import it.unimi.di.sweng.lab11.model.Model;
import it.unimi.di.sweng.lab11.view.InputAlimentView;
import org.jetbrains.annotations.NotNull;

public class InputAlimentPresenter implements InputPresenter {

    private final Model model;
    private final InputAlimentView view;


    public InputAlimentPresenter(Model model, InputAlimentView view) {
        this.model = model;
        this.view = view;
        view.addHandlers(this);
    }

    @Override
    public void action(@NotNull String text, @NotNull String text1) {
        /*Cibo cibo;
        try {
            cibo = new Cibo(text);
        } catch (IllegalArgumentException e) {
            view.showError("Inserito cibo non valido");
            return;
        }

        Quantita quantita;
        try {
            quantita = Quantita.fromString(text1);
        } catch (IllegalArgumentException e) {
            view.showError("Inserita quantità non valida");
            return;
        }

        view.showSuccess();
        model.addCibo(cibo.name(), quantita.quantita());*/

        try {
            int quantita = Integer.parseInt(text1);
            if (text.isBlank()) throw new IllegalArgumentException("Inserito prodotto vuoto");
            if (quantita <= 0) throw new IllegalArgumentException("Inserita quantità non valida");
            view.showSuccess();
            model.addCibo(text, quantita);
        } catch(IllegalArgumentException e) {
            view.showError("Inserita quantità non valida");
        }
        // TODO cambiare sta roba rifacendo gli addCibo che accettano Cibo e Quantità
        // in modo che i controlli di validità siano fatti direttamente nei record
    }
}
