package hu.becseimiklos.prt.hw.ui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomeSiteController {

    private final static String REGISTER_SITE_URL = "/fxml/registerSite.fxml";
    private final static String SUMMARY_SITE_URL = "/fxml/summarySite.fxml";

    @Autowired
    StageManager stageManager = new StageManager();

    @FXML
    private AnchorPane homeSite;

    @FXML
    private JFXButton registerButton;

    @FXML
    private JFXButton summaryButton;

    @FXML
    private JFXButton enterButton;

    @FXML
    private JFXTextField licensePlateNumberField;

    @FXML
    private JFXButton exitButton;

    @FXML
    void handleRegisterButton() {
        stageManager.switchStage(registerButton, REGISTER_SITE_URL);
    }

    @FXML
    void handleSummaryButton() {
        stageManager.switchStage(summaryButton, SUMMARY_SITE_URL);
    }

}
