package it.unimi.di.sweng.lab11.model;

import it.unimi.di.sweng.lab11.Observer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TestModel {


    @Test
    void testSetterGetter() {
        Model model = new Model();

        model.addCibo("mele", 6);
        model.addCibo("pere", 8);

        assertThat(model.getToBuy())
                .hasSize(2)
                .contains(new QuantitaCibo(new Cibo("mele"), new Quantita(6)))
                .contains(new QuantitaCibo(new Cibo("pere"), new Quantita(8)));

        assertThat(model.getBuyed())
                .hasSize(0)
                .doesNotContain(new QuantitaCibo(new Cibo("mele"), new Quantita(6)))
                .doesNotContain(new QuantitaCibo(new Cibo("pere"), new Quantita(8)));
    }

    @Test
    void notifyObservers() {

        Model model = new Model();

        Observer<List<QuantitaCibo>> obs = mock();
        Observer<List<QuantitaCibo>> obs1 = mock();

        model.addObserver(obs);
        model.addObserver(obs1);

        model.addCibo("mele", 6);

        verify(obs).update(model);
        verify(obs1).update(model);
    }
}
