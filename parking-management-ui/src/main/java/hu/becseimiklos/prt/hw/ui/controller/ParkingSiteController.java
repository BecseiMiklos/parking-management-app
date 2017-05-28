package hu.becseimiklos.prt.hw.ui.controller;

import hu.becseimiklos.prt.hw.service.ParkingService;
import hu.becseimiklos.prt.hw.ui.model.CarModel;
import hu.becseimiklos.prt.hw.ui.model.ParkingModel;
import hu.becseimiklos.prt.hw.vo.CarVo;
import hu.becseimiklos.prt.hw.vo.ParkingVo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ParkingSiteController implements Initializable {

    private ObservableList<ParkingModel> inProcessParkingObservableList;

    @Autowired
    ParkingService parkingService;

    @FXML
    private TableView<ParkingModel> parkingTable;

    @FXML
    private TableColumn<ParkingModel, String> licensePlateNumberColumn;

    @FXML
    private Label licensePlateNumberLabel;

    @FXML
    private Label enterTimeLabel;

    @FXML
    private Label parkingCostLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getParkingList();
        showParkingDetails(null);

        parkingTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showParkingDetails(newValue)
        );
    }

    public void getParkingList() {
        List<ParkingVo> inProcessParkings = parkingService.findAllInProcessParking();
        inProcessParkingObservableList = FXCollections.observableArrayList();

        for (ParkingVo parkingVo : inProcessParkings) {
            ParkingModel parkingModel = new ParkingModel(parkingVo.getId(), parkingVo.getEnterTime(), parkingVo.getExitTime(), parkingVo.getCar());
            inProcessParkingObservableList.add(parkingModel);
        }

        parkingTable.setItems(inProcessParkingObservableList);
        licensePlateNumberColumn.setCellValueFactory(cell -> cell.getValue().getCar().licensePlateNumberProperty());
    }

    private void showParkingDetails(ParkingModel parkingModel) {
        if (parkingModel != null) {
            licensePlateNumberLabel.setText(parkingModel.getCar().getLicensePlateNumber());
            enterTimeLabel.setText(parkingModel.getEnterTime().toString());
//            parkingCostLabel.setText(calculateParkingCost());
        } else {
            licensePlateNumberLabel.setText("");
            enterTimeLabel.setText("");
        }
    }
}
