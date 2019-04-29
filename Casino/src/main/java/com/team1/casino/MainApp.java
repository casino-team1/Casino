package com.team1.casino;

import com.team1.casino.Controller.CasinoController;
import com.team1.casino.Model.CasinoModel;
import Baccara.Baccara;
import Blackjack.Blackjack;
import Roulette.Roulette;
import Yatzy.Yatzy;
import com.team1.casino.Controller.AuthenticationController;
import com.team1.casino.Controller.Statistic.GameStatisticController;
import com.team1.casino.Controller.LoginController;
import com.team1.casino.Controller.PasswordRecoveryController;
import com.team1.casino.Controller.Statistic.PlayerStatisticController;
import com.team1.casino.Controller.RegistrationViewController;
import com.team1.casino.Controller.Statistic.StatisticController;
import com.team1.casino.Model.AuthenticationModel;
import com.team1.casino.Model.CasinoLoginModel;
import com.team1.casino.Model.GameStatisticModel;
import com.team1.casino.Model.PlayerStatisticModel;
import com.team1.casino.Model.RegistrationModel;
import com.team1.casino.Model.StatisticModel;
import com.team1.casino.User.Util.UserCentral;
import com.team1.casino.database.Connection.DatabaseConnection;
import com.team1.casino.database.Connection.DatabaseConnector;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        this.stage.setResizable(false);
        return this.stage;
    }


    
    public static final ExecutionMode executionMode = ExecutionMode.DEVELOPMENT;
    
    public static final ExecutionMode EXECUTION_MODE = ExecutionMode.DEBUG;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.setResizable(false);
        this.stage.centerOnScreen();
        switch (this.EXECUTION_MODE) {
            case DEBUG:
                if (UserCentral.getInstance().getUser() != null) {
                } else {
                    setupForDEBUG();
                    displayLoginView();
                }
                break;
            case ADMINISTRATOR_TEST:
                setupForDEBUG();
                displayStatisticView();
                break;
            case PRODUCTION:
                setupForProduction();
                displayMainMenu();
                break;
            default:
                displayMainMenu();
                break;
        }
    }

    public void displayPasswordRecovery() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PasswordRecovery.fxml"));
            Parent root;
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Passwort vergessen");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            PasswordRecoveryController controller = loader.getController();
            controller.setMainApplication(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setupForProduction() {
        DatabaseConnection connection = new DatabaseConnector("localhost", "3306", "Casino", "casinoworker", "", false).connectToDatabase();
    }

    public void setupForDEBUG() {
        DatabaseConnection connection = new DatabaseConnector("localhost", "3306", "Casino", "casinoworker", "", false).connectToDatabase();
    }

    public void displayLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
            Parent root;
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Spieler einloggen");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            LoginController controller = loader.getController();
            CasinoLoginModel loginModel = new CasinoLoginModel();
            loginModel.setMainApplication(this);
            controller.setModel(loginModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayRegistrationView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegistrationView.fxml"));
            Parent root;
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Spieler registrieren");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            RegistrationViewController controller = loader.getController();
            RegistrationModel model = new RegistrationModel(this);
            controller.setModel(model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayMainMenu() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
            Parent root;
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Spiel auswahl | MountainView");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            CasinoController controller = loader.getController();
            controller.setCasinoModel(new CasinoModel(this));
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayAuthenticationWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AuthenticationView.fxml"));
            Parent root;
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Authentifizierungs Menu");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            AuthenticationController controller = loader.getController();
            AuthenticationModel authModel = new AuthenticationModel(UserCentral.getInstance().getUser().getValidationCode());
            authModel.setMainApplication(this);
            controller.setAuthenticationModel(authModel);
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayStatisticView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StatisticView.fxml"));
            Parent root;
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Adminstrator Dashboard");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            StatisticController controller = loader.getController();
            controller.setStatisticModel(new StatisticModel(this));
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayPlayerStatistic() throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayerStatisticView.fxml"));
            Parent root;
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Spieler Statistiken");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            PlayerStatisticController controller = loader.getController();
            PlayerStatisticModel playerStatModel = new PlayerStatisticModel();
            playerStatModel.loadUsernames();
            playerStatModel.setMainApplication(this);
            playerStatModel.addObserver(controller);
            controller.setPlayerStatisticModel(playerStatModel);
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayGameStatistic() throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameStatisticView.fxml"));
            Parent root;
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Spiel Statistiken");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            GameStatisticController controller = loader.getController();
            GameStatisticModel gameStatModel = new GameStatisticModel();
            gameStatModel.setMainApplication(this);
            gameStatModel.loadGameNames();
            gameStatModel.addObserver(controller);
            controller.setGameStatistics(gameStatModel);
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
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
