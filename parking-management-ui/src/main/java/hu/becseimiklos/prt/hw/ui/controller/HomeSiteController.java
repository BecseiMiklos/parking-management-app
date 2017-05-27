package hu.becseimiklos.prt.hw.ui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class HomeSiteController {

    @FXML
    private JFXButton enterButton;

    @FXML
    private JFXTextField licensePlateNumberField;

    @FXML
    private JFXButton exitButton;

}
