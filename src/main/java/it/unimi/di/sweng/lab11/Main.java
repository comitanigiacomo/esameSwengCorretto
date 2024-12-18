package it.unimi.di.sweng.lab11;

import it.unimi.di.sweng.lab11.model.Model;
import it.unimi.di.sweng.lab11.presenter.CompratorePresenter;
import it.unimi.di.sweng.lab11.presenter.InputAlimentPresenter;
import it.unimi.di.sweng.lab11.presenter.VisualizzaListaPresenter;
import it.unimi.di.sweng.lab11.presenter.stampastrategy.OnlyNameStampaStrategy;
import it.unimi.di.sweng.lab11.presenter.stampastrategy.StampaStrategy;
import it.unimi.di.sweng.lab11.presenter.stampastrategy.WithQuantityStampaStrategy;
import it.unimi.di.sweng.lab11.presenter.statodastamparestrategy.ComprateStrategy;
import it.unimi.di.sweng.lab11.presenter.statodastamparestrategy.DaComprareStrategy;
import it.unimi.di.sweng.lab11.presenter.statodastamparestrategy.StatoDaStampareStrategy;
import it.unimi.di.sweng.lab11.view.CommandView;
import it.unimi.di.sweng.lab11.view.DisplayView;
import it.unimi.di.sweng.lab11.view.InputAlimentView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
  //TODO da completare

  public static final int MAX_FOOD = 8;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    primaryStage.setTitle("Grocery List");

    CommandView[] command = new CommandView[2];

    InputAlimentView input = new InputAlimentView();
    DisplayView display = new DisplayView(MAX_FOOD, "Cose da comprare");
    DisplayView display2 = new DisplayView(MAX_FOOD, "Cose comprate");

    GridPane gridPane = new GridPane();
    gridPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    gridPane.setPadding(new Insets(10, 10, 10, 10));

    gridPane.add(input, 0, 0);
    GridPane.setColumnSpan(input, GridPane.REMAINING);
    for (int i = 0; i < 2; i++) {
      command[i] = new CommandView("Buy", MAX_FOOD, "Compratore#" + i);
      gridPane.add(command[i], i, 1);
    }

    gridPane.add(display, 3, 1);
    gridPane.add(display2, 4, 1);

    // TODO da completare creando modello e presenter e collegandoli opportunamente
    Model model = new Model();
    InputAlimentPresenter presenter = new InputAlimentPresenter(model, input);

    StampaStrategy stampaStrategyWithQuantity = new WithQuantityStampaStrategy();
    StampaStrategy stampaStrategyOnlyName = new OnlyNameStampaStrategy();

    StatoDaStampareStrategy comprateStrategy = new ComprateStrategy();
    StatoDaStampareStrategy daComprareStrategy = new DaComprareStrategy();

    CompratorePresenter presenter1 = new CompratorePresenter(model, command[0], stampaStrategyOnlyName);
    CompratorePresenter presenter2 = new CompratorePresenter(model, command[1], stampaStrategyOnlyName);
    VisualizzaListaPresenter presenter3 = new VisualizzaListaPresenter(model, display, stampaStrategyWithQuantity, daComprareStrategy);
    VisualizzaListaPresenter presenter4 = new VisualizzaListaPresenter(model, display2, stampaStrategyWithQuantity, comprateStrategy);

    model.notifyObservers();

    Scene scene = new Scene(gridPane);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
