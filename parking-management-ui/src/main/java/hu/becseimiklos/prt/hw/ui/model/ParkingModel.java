package hu.becseimiklos.prt.hw.ui.model;

import hu.becseimiklos.prt.hw.vo.CarVo;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.util.Date;

public class ParkingModel {

    private SimpleLongProperty id;

    private SimpleObjectProperty<Date> enterTime;
    private SimpleObjectProperty<Date> exitTime;
    private SimpleObjectProperty<CarModel> car;

    public ParkingModel(){}

    public ParkingModel(Long id, Date enterTime, Date exitTime, CarVo carVo) {
        this.id = new SimpleLongProperty(id);
        this.enterTime = new SimpleObjectProperty<>(enterTime);
        this.exitTime = new SimpleObjectProperty<>(exitTime);
        this.car = new SimpleObjectProperty<>(new CarModel(carVo.getId(), carVo.getLicensePlateNumber(), carVo.getBrand(), carVo.getColor(),carVo.getHasParkingPass()));
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

    public Date getEnterTime() {
        return enterTime.get();
    }

    public SimpleObjectProperty<Date> enterTimeProperty() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = new SimpleObjectProperty<>(enterTime);
    }

    public Date getExitTime() {
        return exitTime.get();
    }

    public SimpleObjectProperty<Date> exitTimeProperty() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
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
