package it.unimi.di.sweng.lab11.presenter;

import it.unimi.di.sweng.lab11.model.Cibo;
import it.unimi.di.sweng.lab11.model.Model;
import it.unimi.di.sweng.lab11.model.Quantita;
import it.unimi.di.sweng.lab11.model.QuantitaCibo;
import it.unimi.di.sweng.lab11.presenter.stampastrategy.StampaStrategy;
import it.unimi.di.sweng.lab11.view.CommandView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class TestCompratorePresenter {

    @Test
    void testUpdatePresenter() {
        Model model = mock();
        CommandView view = mock();
        StampaStrategy strategy = mock();

        List<QuantitaCibo> cibi = List.of(
                new QuantitaCibo(new Cibo("A"), new Quantita(1)),
                new QuantitaCibo(new Cibo("B"), new Quantita(1)),
                new QuantitaCibo(new Cibo("C"), new Quantita(1))
        );

        CompratorePresenter presenter = new CompratorePresenter(model, view, strategy);

        when(model.getStateToBuy()).thenReturn(cibi);

        when(strategy.onlyName(any())).thenReturn("A", "B", "C");

        presenter.update(model);

        verify(strategy).onlyName(new QuantitaCibo(new Cibo("A"), new Quantita(1)));
        verify(strategy).onlyName(new QuantitaCibo(new Cibo("B"), new Quantita(1)));
        verify(strategy).onlyName(new QuantitaCibo(new Cibo("C"), new Quantita(1)));

        verify(view).set(0, "A");
        verify(view).set(1, "B");
        verify(view).set(2, "C");
    }

    @Test
    void testActionCompratore() {
        Model model = mock();
        CommandView view = mock();
        StampaStrategy strategy = mock();

        CompratorePresenter presenter = new CompratorePresenter(model, view, strategy);

        presenter.action("mele", "1");

        verify(model).buyCibo("mele");
    }
}