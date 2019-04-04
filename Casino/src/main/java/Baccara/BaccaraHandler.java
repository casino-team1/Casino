/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara;

import Baccara.Controller.BaccaraMenuViewController;
import Baccara.Entity.BaccaraDeck;
import Baccara.Model.BaccaraMenuModel;
import com.team1.casino.CasinoController;
import com.team1.casino.CasinoModel;
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
        this.menuModel = new BaccaraMenuModel(this);
    }
    
    public void displayMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BaccaraMenuView.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Casino Central");
        stage.setScene(scene);
        stage.show();
        BaccaraMenuViewController controller = loader.getController();
        controller.setMenuModel(this.menuModel);
    }
    
    public void displayGame() {
        
    }
    
    public void displayMainMenu() throws Exception {
        this.mainApplication.start(stage);
    }
    
}
