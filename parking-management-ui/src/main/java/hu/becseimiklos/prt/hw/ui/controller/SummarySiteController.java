package hu.becseimiklos.prt.hw.ui.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

public class SummarySiteController {

    private final static String HOME_SITE_URL = "/fxml/homeSite.fxml";
    private final static String REGISTER_SITE_URL = "/fxml/registerSite.fxml";

    @Autowired
    StageManager stageManager = new StageManager();

    @FXML
    private AnchorPane summarySite;

    @FXML
    private JFXButton homeButton;

    @FXML
    private JFXButton registerButton;

    @FXML
    void handleHomeButton() {
        stageManager.switchStage(homeButton, HOME_SITE_URL);
    }

    @FXML
    void handleRegisterButton() {
        stageManager.switchStage(registerButton, REGISTER_SITE_URL);
    }
}
