package hu.becseimiklos.prt.hw.ui.controller;

import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarTabController {

    @Autowired
    private CarService carService;

    @FXML
    private AnchorPane carTab;

    @FXML
    private TextField licensePlateNumberField;

    @FXML
    private CheckBox parkingPassCheckBox;

    @FXML
    private Button saveCar;


    @FXML
    public void initialize() {
    }

    @FXML
    private void handleSave() {
        CarVo carVo = new CarVo();
        carVo.setLicensePlateNumber(licensePlateNumberField.getText());
        carVo.setHasParkingPass(parkingPassCheckBox.isSelected());
        carService.save(carVo);
    }
}
