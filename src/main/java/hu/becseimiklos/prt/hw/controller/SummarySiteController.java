package hu.becseimiklos.prt.hw.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import hu.becseimiklos.prt.hw.main.Main;
import hu.becseimiklos.prt.hw.model.CarModel;
import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class SummarySiteController implements Initializable {

    @Autowired
    Main main;

    @Autowired
    CarService carService;

    @Autowired
    HomeSiteController homeSiteController;

    @Autowired
    CarEditDialogController carEditDialogController;

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
            CarModel carModel = new CarModel(carVo.getId(), carVo.getLicensePlateNumber(), carVo.getBrand(), carVo.getColor(), carVo.getHasParkingPass());
            carModelObservableList.add(carModel);
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
            homeSiteController.updateAutoComplete();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getMainStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Car Selected");
            alert.setContentText("Please select a car in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    void handleEditCar(ActionEvent event) {
        CarModel selectedCar = carTable.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            boolean okClicked = showCarEditDialog(selectedCar);
            if (okClicked) {
                showCarDetails(selectedCar);
            }
        }
    }

    public boolean showCarEditDialog(CarModel carModel) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/CarEditDialog.fxml"));
            loader.setController(carEditDialogController); // !important for creating a new instance of the controller class
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Car");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(main.getMainStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            carEditDialogController = loader.getController();
            carEditDialogController.setDialogStage(dialogStage);
            carEditDialogController.setCarModel(carModel);

            dialogStage.showAndWait();

            return carEditDialogController.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

