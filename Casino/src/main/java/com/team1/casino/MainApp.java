package com.team1.casino;

import Baccara.Baccara;
import Blackjack.Blackjack;
import Roulette.Roulette;
import Yatzy.Yatzy;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    
    private Stage stage;
    
    public Stage getStage() {
        return this.stage;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Casino Central");
        stage.setScene(scene);
        stage.show();
        CasinoController controller = loader.getController();
        controller.setCasinoModel(new CasinoModel(this));
    }
    
    public void startBaccara() {
        Baccara baccara = new Baccara(this);
        baccara.startGame();
    }
    
    public void startBlackJack() {
        Blackjack blackJack = new Blackjack(this);
        blackJack.startGame();
    }
    
    public void startRoulette() {
        Roulette roulette = new Roulette(this);
        roulette.startGame();
    }
    
    public void startYatzy() {
        Yatzy yatzy = new Yatzy(this);
        yatzy.startGame();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
