/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Entity;

import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 *
 * @author Nick Flückiger
 */
public class PasswordChanger {

    private String newPassword;
    private boolean validPasswordChange = false;

    /**
     * Dialog used from https://code.makery.ch/blog/javafx-dialogs-official/
     * Changed for the need of validating the new repeat.
     *
     * @return
     */
    public boolean displayDialog() {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Ändere dein Passwort");
        dialog.setHeaderText("Passwort ändern");

        ButtonType loginButtonType = new ButtonType("Ändern", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, cancelButton);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        PasswordField password = new PasswordField();
        password.setPromptText("Passwort");
        PasswordField repeat = new PasswordField();
        repeat.setPromptText("Wiederholen");
        grid.add(new Label("Passwort:"), 0, 0);
        grid.add(password, 1, 0);
        grid.add(new Label("Wiederholen:"), 0, 1);
        grid.add(repeat, 1, 1);
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> password.requestFocus());
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(password.getText(), repeat.getText());
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(passwordValidation -> {
            if (passwordValidation.getKey().equals(passwordValidation.getValue())) {
                validPasswordChange = true;
                newPassword = passwordValidation.getKey();
            }
        });
        return validPasswordChange;
    }

    /**
     * Dialog used from https://code.makery.ch/blog/javafx-dialogs-official/
     *
     * @param titel
     * @param message
     */
    public void displayMessage(String titel, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titel);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public String getNewPassword() {
        return this.newPassword;
    }
}
