package hu.becseimiklos.prt.hw.ui.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public class Car {

    private StringProperty licensePlateNumber;

    private BooleanProperty hasParkingPass;

    public Car() {}

    public Car(StringProperty licensePlateNumber, BooleanProperty hasParkingPass) {
        this.licensePlateNumber = licensePlateNumber;
        this.hasParkingPass = hasParkingPass;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber.get();
    }

    public StringProperty licensePlateNumberProperty() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber.set(licensePlateNumber);
    }

    public boolean isHasParkingPass() {
        return hasParkingPass.get();
    }

    public BooleanProperty hasParkingPassProperty() {
        return hasParkingPass;
    }

    public void setHasParkingPass(boolean hasParkingPass) {
        this.hasParkingPass.set(hasParkingPass);
    }
}
