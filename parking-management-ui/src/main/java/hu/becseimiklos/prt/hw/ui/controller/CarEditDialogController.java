package hu.becseimiklos.prt.hw.ui.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.ui.model.CarModel;
import hu.becseimiklos.prt.hw.vo.CarVo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CarEditDialogController implements Initializable {

    private Stage dialogStage;
    private CarModel carModel;
    private boolean okClicked = false;

    @Autowired
    CarService carService;

    public CarEditDialogController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private JFXTextField licensePlateNumberField;

    @FXML
    private JFXTextField brandField;

    @FXML
    private JFXTextField colorField;

    @FXML
    private JFXCheckBox parkingPassCheckBox;


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;

        licensePlateNumberField.setText(carModel.getLicensePlateNumber());
        brandField.setText(carModel.getBrand());
        colorField.setText(carModel.getColor());
        parkingPassCheckBox.setSelected(carModel.isHasParkingPass());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    void handleOk(ActionEvent event) {
        carModel.setLicensePlateNumber(licensePlateNumberField.getText());
        carModel.setBrand(brandField.getText());
        carModel.setColor(colorField.getText());
        carModel.setHasParkingPass(parkingPassCheckBox.isSelected());

        CarVo modifiedCar = new CarVo(carModel.getId(), carModel.getLicensePlateNumber(), carModel.getBrand(), carModel.getColor(), carModel.isHasParkingPass());
        carService.save(modifiedCar);

        okClicked = true;
        dialogStage.close();
    }

    @FXML
    void handleCancel(ActionEvent event) {
        dialogStage.close();
    }



}
