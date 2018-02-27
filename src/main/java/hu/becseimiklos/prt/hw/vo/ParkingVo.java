package hu.becseimiklos.prt.hw.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ParkingVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDateTime enterTime;
    private LocalDateTime exitTime;
    private CarVo car;
    private Integer paidCost;

}
