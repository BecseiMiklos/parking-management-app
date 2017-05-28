package hu.becseimiklos.prt.hw.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ParkingVo implements Serializable {

    private Long id;
    private Date enterTime;
    private Date exitTime;
    private CarVo car;
}
