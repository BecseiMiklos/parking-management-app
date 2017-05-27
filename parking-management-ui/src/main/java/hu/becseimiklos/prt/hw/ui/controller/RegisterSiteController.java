package hu.becseimiklos.prt.hw.ui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterSiteController {

    private final static String HOME_SITE_URL = "/fxml/homeSite.fxml";
    private final static String SUMMARY_SITE_URL = "/fxml/summarySite.fxml";

    @Autowired
    StageManager stageManager = new StageManager();

    @FXML
    private AnchorPane registerSite;

    @FXML
    private JFXButton homeButton;

    @FXML
    private JFXButton summaryButton;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXTextField licensePlateNumberField;

    @FXML
    private JFXTextField brandField;

    @FXML
    private JFXTextField colorField;

    @FXML
    private JFXCheckBox parkingPassCheckBox;

    @FXML
    void handleHomeButton() {
        stageManager.switchStage(homeButton, HOME_SITE_URL);
    }

    @FXML
    void handleSummaryButton( ) {
        stageManager.switchStage(summaryButton, SUMMARY_SITE_URL);
    }

}