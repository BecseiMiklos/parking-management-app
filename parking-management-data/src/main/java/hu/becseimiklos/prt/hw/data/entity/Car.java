package hu.becseimiklos.prt.hw.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Car implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String licensePlateNumber;

    private Boolean hasParkingPass;

    public Car() {}

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public Boolean getHasParkingPass() {
        return hasParkingPass;
    }

    public void setHasParkingPass(Boolean hasParkingPass) {
        this.hasParkingPass = hasParkingPass;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                ", hasParkingPass=" + hasParkingPass +
                '}';
    }
}
