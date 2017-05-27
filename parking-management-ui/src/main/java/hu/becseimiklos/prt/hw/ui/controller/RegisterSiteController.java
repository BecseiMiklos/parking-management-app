package hu.becseimiklos.prt.hw.ui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterSiteController {

    @Autowired
    CarService carService;

    @Autowired
    SummarySiteController summarySiteController;

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
    void handleSaveButton(ActionEvent event) {
        CarVo newCar = new CarVo();
        newCar.setLicensePlateNumber(licensePlateNumberField.getText());
        newCar.setBrand(brandField.getText());
        newCar.setColor(colorField.getText());
        newCar.setHasParkingPass(parkingPassCheckBox.isSelected());

        carService.save(newCar);
        summarySiteController.getCarList();
    }

}
