/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara;

import Baccara.Controller.BaccaraMenuViewController;
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

    private MainApp mainApplication;
    private Stage stage;
    private BaccaraMenuModel menuModel;

    public BaccaraHandler(MainApp mainApplication) {
        this.mainApplication = mainApplication;
        this.stage = mainApplication.getStage();
        this.stage.setResizable(false);
        this.menuModel = new BaccaraMenuModel(this);
    }

    public void displayMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BaccaraMenuView.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            stage.setTitle("Baccara Menu");
            stage.setScene(scene);
            stage.show();
            BaccaraMenuViewController controller = loader.getController();
            controller.setMenuModel(this.menuModel);
        } catch (IOException e) {
        }
    }

    public void displayGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BaccaraGameView.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            stage.setTitle("Baccara!");
            stage.setScene(scene);
            stage.show();
            BaccaraMenuViewController controller = loader.getController();
            controller.setMenuModel(this.menuModel);
        } catch (IOException e) {
        }
    }

    public void displayMainMenu() throws Exception {
        this.mainApplication.start(stage);
    }

}
