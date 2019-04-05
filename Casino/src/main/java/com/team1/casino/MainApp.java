package com.team1.casino;

import Baccara.Baccara;
import Blackjack.Blackjack;
import Roulette.Roulette;
import Yatzy.Yatzy;
import com.team1.casino.Player.PlayerCentral;
import com.team1.casino.Player.PlayerUtil;
import com.team1.casino.Player.Spieler;
import com.team1.casino.database.DatabaseConnection;
import com.team1.casino.database.DatabaseConnector;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage stage;

    public Stage getStage() {
        return this.stage;
    }

    private final ExecutionMode executionMode = ExecutionMode.DEVELOPMENT;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        if (this.executionMode == ExecutionMode.PRODUCTION) {
            setupForProduction();
        } else if (this.executionMode == ExecutionMode.DEBUG) {
            setupForDEBUG();
        }
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Casino Central");
        stage.setScene(scene);
        stage.show();
        CasinoController controller = loader.getController();
        controller.setCasinoModel(new CasinoModel(this));
    }

    public void setupForProduction() {
        DatabaseConnection connection = new DatabaseConnector("localhost", "3306", "Casino", "casinoworker", "", false).connectToDatabase();
    }

    public void setupForDEBUG() {
        DatabaseConnection connection = new DatabaseConnector("localhost", "3306", "Casino", "casinoworker", "", false).connectToDatabase();
        PlayerCentral.getInstance().setPlayer(new Spieler("Muster", "1234"));
        PlayerCentral.getInstance().getPlayer().setCurrentBalance(new PlayerUtil().loadCurrentBalanceFromGivenUsername("Muster"));
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

    public static Stage centerStageInScreen(Stage stage, Scene scene) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - scene.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - scene.getHeight()) / 2);
        return stage;
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
