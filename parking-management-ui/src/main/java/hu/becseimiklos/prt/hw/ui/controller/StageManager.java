package hu.becseimiklos.prt.hw.ui.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageManager {

    public void switchStage(JFXButton handledButton, String fxmlFilePath) {
        Stage stage;
        Parent root;

        stage = (Stage) handledButton.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
