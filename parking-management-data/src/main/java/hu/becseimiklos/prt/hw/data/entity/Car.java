package hu.becseimiklos.prt.hw.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "CAR_SEQ")
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
