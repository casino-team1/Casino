/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara;

import Baccara.Controller.BaccaraGameViewController;
import Baccara.Controller.BaccaraMenuViewController;
import Baccara.Model.BaccaraGameModel;
import Baccara.Model.BaccaraMenuModel;
import com.team1.casino.MainApp;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nick Flückiger
 */
public class BaccaraHandler {

    private final MainApp mainApplication;
    private Stage stage;
    private BaccaraMenuModel menuModel;
    private BaccaraGameModel gameModel;
    private Scene scene;

    public BaccaraHandler(MainApp mainApplication) {
        this.mainApplication = mainApplication;
        this.stage = mainApplication.getStage();
        //in order to preserve the ratio of the images, the user is not allowed to change the size of the window.
        this.stage.setResizable(false);
        this.menuModel = new BaccaraMenuModel(this);
        this.gameModel = new BaccaraGameModel(this);
    }

    public Scene getScene() {
        return this.scene;
    }

    public void displayMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BaccaraMenuView.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Baccara Menu");
            stage.setScene(scene);
            this.scene = scene;
            stage.show();
            BaccaraMenuViewController controller = loader.getController();
            controller.setMenuModel(this.menuModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayGame() {
        try {
            this.gameModel = new BaccaraGameModel(this);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BaccaraGameView.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Baccara!");
            stage.setScene(scene);
            this.scene = scene;
            stage = MainApp.centerStageInScreen(stage, scene);
            stage.show();
            BaccaraGameViewController controller = loader.getController();
            controller.setBaccaraHandler(this);
            controller.setBaccaraGameModel(this.gameModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayMainMenu() throws Exception {
        this.mainApplication.displayMainMenu();
    }

}
