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
    private String brand;
    private String color;
    private Boolean hasParkingPass;

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public Boolean getHasParkingPass() {
        return hasParkingPass;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setHasParkingPass(Boolean hasParkingPass) {
        this.hasParkingPass = hasParkingPass;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", hasParkingPass=" + hasParkingPass +
                '}';
    }
}
