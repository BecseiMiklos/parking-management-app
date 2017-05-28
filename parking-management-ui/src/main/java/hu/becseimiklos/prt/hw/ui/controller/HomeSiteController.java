package hu.becseimiklos.prt.hw.ui.controller;

import com.jfoenix.controls.JFXTextField;
import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.service.ParkingService;
import hu.becseimiklos.prt.hw.vo.CarVo;
import hu.becseimiklos.prt.hw.vo.ParkingVo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class HomeSiteController implements Initializable {

    @Autowired
    CarService carService;

    @Autowired
    ParkingService parkingService;

    @FXML
    private JFXTextField licensePlateNumberField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateAutoComplete();
    }

    public void updateAutoComplete() {
        List<CarVo> cars = carService.findAll();
        List<String> carPlates = cars.stream().map(car -> car.getLicensePlateNumber()).collect(Collectors.toList());
        TextFields.bindAutoCompletion(licensePlateNumberField, carPlates);
    }

    @FXML
    void handleEnterButton(ActionEvent event) {
        CarVo enteringCar = carService.findByLicensePlateNumber(licensePlateNumberField.getText());
        ParkingVo parkingVo = new ParkingVo();
        parkingVo.setCar(enteringCar);
        parkingService.enter(parkingVo);
    }

    @FXML
    void handleExitButton(ActionEvent event) {
        CarVo exittingCar = carService.findByLicensePlateNumber(licensePlateNumberField.getText());

        ParkingVo parkingVo = parkingService.findByCarAndAndExitTimeIsNull(exittingCar);
        parkingService.exit(parkingVo);
    }

}
