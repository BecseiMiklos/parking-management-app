package hu.becseimiklos.prt.hw.vo;


import java.io.Serializable;

public class CarVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String licensePlateNumber;
    private String brand;
    private String color;
    private Boolean hasParkingPass;

    public CarVo() {}

    public CarVo(Long id, String licensePlateNumber, String brand, String color, Boolean hasParkingPass) {
        this.id = id;
        this.licensePlateNumber = licensePlateNumber;
        this.brand = brand;
        this.color = color;
        this.hasParkingPass = hasParkingPass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getHasParkingPass() {
        return hasParkingPass;
    }

    public void setHasParkingPass(Boolean hasParkingPass) {
        this.hasParkingPass = hasParkingPass;
    }

    @Override
    public String toString() {
        return "CarVo{" +
                "id=" + id +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", hasParkingPass=" + hasParkingPass +
                '}';
    }
}
