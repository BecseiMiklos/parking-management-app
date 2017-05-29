package hu.becseimiklos.prt.hw.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ParkingVo implements Serializable {

    private Long id;
    private LocalDateTime enterTime;
    private LocalDateTime exitTime;
    private CarVo car;
    private Integer paidCost;
}
