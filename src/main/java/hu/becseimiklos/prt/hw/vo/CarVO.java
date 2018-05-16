package hu.becseimiklos.prt.hw.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Value object to transfer data of a Car.
 */
@Getter
@Setter
@ToString
public class CarVO implements Serializable {

    private static final long serialVersionUID = -5652800967347026812L;

    private Long id;
    private String licensePlateNumber;
    private String brand;
    private String color;
    private Boolean hasParkingPass;

}
