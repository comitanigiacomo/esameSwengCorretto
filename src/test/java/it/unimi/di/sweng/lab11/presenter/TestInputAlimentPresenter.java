package it.unimi.di.sweng.lab11.presenter;

import it.unimi.di.sweng.lab11.model.Model;
import it.unimi.di.sweng.lab11.view.InputAlimentView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TestInputAlimentPresenter {

    private Model model;
    private InputAlimentView view;
    private InputAlimentPresenter presenter;

    @BeforeEach
    void setUp() {
        model = mock(Model.class);
        view = mock(InputAlimentView.class);
        presenter = new InputAlimentPresenter(model, view);
    }

    @Test
    void testActionSuccess() {
        String validProduct = "Mela";
        String validQuantity = "3";

        presenter.action(validProduct, validQuantity);

        verify(model).addCibo(validProduct, 3);
        verify(view).showSuccess();
    }

    @Test
    void testActionEmptyProduct() {
        String emptyProduct = "";
        String validQuantity = "3";

        presenter.action(emptyProduct, validQuantity);

        verify(view).showError("Inserita quantità non valida");
        verifyNoInteractions(model);
    }

    @Test
    void testActionInvalidQuantity() {
        String validProduct = "Mela";
        String invalidQuantity = "-1";

        presenter.action(validProduct, invalidQuantity);

        verify(view).showError("Inserita quantità non valida");
        verifyNoInteractions(model);
    }

    @Test
    void testActionNonNumericQuantity() {
        String validProduct = "Mela";
        String nonNumericQuantity = "abc";

        presenter.action(validProduct, nonNumericQuantity);

        verify(view).showError("Inserita quantità non valida");
        verifyNoInteractions(model);
    }
}
