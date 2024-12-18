package it.unimi.di.sweng.lab11.presenter;

import it.unimi.di.sweng.lab11.model.Cibo;
import it.unimi.di.sweng.lab11.model.Model;
import it.unimi.di.sweng.lab11.model.Quantita;
import it.unimi.di.sweng.lab11.model.QuantitaCibo;
import it.unimi.di.sweng.lab11.presenter.stampastrategy.StampaStrategy;
import it.unimi.di.sweng.lab11.view.DisplayView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestDaComprarePresenter {

    @Test
    void testUpdateDaComprarePresenter() {

        Model model = mock();
        DisplayView view = mock();
        StampaStrategy strategy = mock();

        DaComprarePresenter presenter = new DaComprarePresenter(model, view, strategy);

        when(model.getStateToBuy()).thenReturn(List.of(
                new QuantitaCibo(new Cibo("mele"),new Quantita(2))
        ));

        presenter.update(model);

        verify(model).getStateToBuy();
        verify(strategy).withQuantity(any());
    }
}
