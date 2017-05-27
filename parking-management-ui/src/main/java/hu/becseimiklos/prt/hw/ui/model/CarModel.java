package hu.becseimiklos.prt.hw.ui.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class CarModel {
    private SimpleLongProperty id;
    private SimpleStringProperty licensePlateNumber;
    private SimpleStringProperty brand;
    private SimpleStringProperty color;
    private SimpleBooleanProperty hasParkingPass;

    public CarModel() {
    }

    public CarModel(Long id, String licensePlateNumber, String brand, String color, Boolean hasParkingPass) {
        this.id = new SimpleLongProperty(id);
        this.licensePlateNumber = new SimpleStringProperty(licensePlateNumber);
        this.brand = new SimpleStringProperty(brand);
        this.color = new SimpleStringProperty(color);
        this.hasParkingPass = new SimpleBooleanProperty(hasParkingPass);
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

    public String getLicensePlateNumber() {
        return licensePlateNumber.get();
    }

    public SimpleStringProperty licensePlateNumberProperty() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = new SimpleStringProperty(licensePlateNumber);
    }

    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = new SimpleStringProperty(brand);
    }

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color = new SimpleStringProperty(color);
    }

    public boolean isHasParkingPass() {
        return hasParkingPass.get();
    }

    public SimpleBooleanProperty hasParkingPassProperty() {
        return hasParkingPass;
    }

    public void setHasParkingPass(boolean hasParkingPass) {
        this.hasParkingPass = new SimpleBooleanProperty(hasParkingPass);
    }
}
