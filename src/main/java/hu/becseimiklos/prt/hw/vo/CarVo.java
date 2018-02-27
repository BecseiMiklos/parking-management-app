package hu.becseimiklos.prt.hw.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CarVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String licensePlateNumber;
    private String brand;
    private String color;
    private Boolean hasParkingPass;

    public CarVo() {
    }

    public CarVo(Long id, String licensePlateNumber, String brand, String color, Boolean hasParkingPass) {
        this.id = id;
        this.licensePlateNumber = licensePlateNumber;
        this.brand = brand;
        this.color = color;
        this.hasParkingPass = hasParkingPass;
    }

}
