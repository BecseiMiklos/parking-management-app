package hu.becseimiklos.prt.hw.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ParkingVO implements Serializable {

    private static final long serialVersionUID = 1703019798430469876L;

    private Long id;
    private LocalDateTime enterTime;
    private LocalDateTime exitTime;
    private CarVO car;
    private Integer paidCost;

}
