package hu.becseimiklos.prt.hw.ui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import hu.becseimiklos.prt.hw.data.entity.Car;
import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.ui.model.CarModel;
import hu.becseimiklos.prt.hw.vo.CarVo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class SummarySiteController implements Initializable {

    @Autowired
    Main main;

    @Autowired
    CarService carService;

    ObservableList<CarModel> carModelObservableList;

    @FXML
    private TableView<CarModel> carTable;

    @FXML
    private TableColumn<CarModel, String> licensePlateNumberColumn;

    @FXML
    private Label licensePlateNumberLabel;

    @FXML
    private Label brandLabel;

    @FXML
    private Label colorLabel;

    @FXML
    private JFXCheckBox parkingPassCheckBox;

    @FXML
    private JFXButton newButton;

    @FXML
    private JFXButton editButton;

    @FXML
    private JFXButton deleteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getCarList();
        showCarDetails(null);

        carTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCarDetails(newValue)
        );
    }

    public void getCarList() {
        List<CarVo> cars = carService.findAll();
        carModelObservableList = FXCollections.observableArrayList();

        for (CarVo carVo : cars) {
            carModelObservableList.add(new CarModel(carVo.getId(), carVo.getLicensePlateNumber(), carVo.getBrand(), carVo.getColor(), carVo.getHasParkingPass()));
        }

        carTable.setItems(carModelObservableList);
        licensePlateNumberColumn.setCellValueFactory(cell -> cell.getValue().licensePlateNumberProperty());
    }

    private void showCarDetails(CarModel carModel) {
        if (carModel != null) {
            licensePlateNumberLabel.setText(carModel.getLicensePlateNumber());
            brandLabel.setText(carModel.getBrand());
            colorLabel.setText(carModel.getColor());
            parkingPassCheckBox.setSelected(carModel.isHasParkingPass());
        } else {
            licensePlateNumberLabel.setText("");
            brandLabel.setText("");
            colorLabel.setText("");
            parkingPassCheckBox.setSelected(false);
        }
    }

    @FXML
    void handleDeleteCar(ActionEvent event) {
        int selectedIndex = carTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            carService.delete(carTable.getSelectionModel().getSelectedItem().getId());
            carTable.getItems().remove(selectedIndex);
            getCarList();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getMainStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Car Selected");
            alert.setContentText("Please select a car in the table.");
            alert.showAndWait();
        }


    }
}

