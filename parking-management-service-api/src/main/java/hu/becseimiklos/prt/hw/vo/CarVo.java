package hu.becseimiklos.prt.hw.vo;


import java.io.Serializable;

public class CarVo implements Serializable {

    private static final long serialVersionUID = 1L;

    public CarVo() {}

    private Long id;

    private String licensePlateNumber;

    private Boolean hasParkingPass;

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
                ", hasParkingPass=" + hasParkingPass +
                '}';
    }
}
