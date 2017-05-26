package hu.becseimiklos.prt.hw.ui.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVo;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterSiteController {

    @Autowired
    private CarService carService;

    @FXML
    private JFXTextField licensePlateNumberField;

    @FXML
    private JFXTextField brandField;

    @FXML
    private JFXTextField colorField;

    @FXML
    private JFXCheckBox parkingPassCheckBox;


    @FXML
    public void initialize() {
    }

    @FXML
    private void handleSave() {
        CarVo carVo = new CarVo();
        carVo.setLicensePlateNumber(licensePlateNumberField.getText());
//        carVo.setBrand(brandField.getText());
//        carVo.setColor(colorField.getText());
        carVo.setHasParkingPass(parkingPassCheckBox.isSelected());
        carService.save(carVo);
    }
}
