package hu.becseimiklos.prt.hw.ui.model;

import hu.becseimiklos.prt.hw.vo.CarVo;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;
import java.util.Date;

public class ParkingModel {

    private SimpleLongProperty id;

    private SimpleObjectProperty<LocalDateTime> enterTime;
    private SimpleObjectProperty<LocalDateTime> exitTime;
    private SimpleIntegerProperty paidCost;
    private SimpleObjectProperty<CarModel> car;

    public ParkingModel() {
    }

    public ParkingModel(Long id, LocalDateTime enterTime, LocalDateTime exitTime, Integer paidCost, CarVo carVo) {
        this.id = new SimpleLongProperty(id);
        this.enterTime = new SimpleObjectProperty<>(enterTime);
        this.exitTime = new SimpleObjectProperty<>(exitTime);
        this.paidCost = new SimpleIntegerProperty(paidCost);
        this.car = new SimpleObjectProperty<>(new CarModel(carVo.getId(), carVo.getLicensePlateNumber(), carVo.getBrand(), carVo.getColor(), carVo.getHasParkingPass()));
    }

    public int getPaidCost() {
        return paidCost.get();
    }

    public SimpleIntegerProperty paidCostProperty() {
        return paidCost;
    }

    public void setPaidCost(int paidCost) {
        this.paidCost = new SimpleIntegerProperty(paidCost);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id = new SimpleLongProperty(id);
    }

    public LocalDateTime getEnterTime() {
        return enterTime.get();
    }

    public SimpleObjectProperty<LocalDateTime> enterTimeProperty() {
        return enterTime;
    }

    public void setEnterTime(LocalDateTime enterTime) {
        this.enterTime = new SimpleObjectProperty<>(enterTime);
    }

    public LocalDateTime getExitTime() {
        return exitTime.get();
    }

    public SimpleObjectProperty<LocalDateTime> exitTimeProperty() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = new SimpleObjectProperty<>(exitTime);
    }

    public CarModel getCar() {
        return car.get();
    }

    public SimpleObjectProperty<CarModel> carProperty() {
        return car;
    }

    public void setCar(CarModel car) {
        this.car = new SimpleObjectProperty<>(car);
    }
}
