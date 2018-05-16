package hu.becseimiklos.prt.hw.vo;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Value object to transfer the data of an ongoing parking.
 */
@Getter
public class InProgressParkingVO {

    private String licensePlateNumber;
    private LocalDateTime enterTime;
    private Integer parkingCost;

    private InProgressParkingVO(String licensePlateNumber, LocalDateTime enterTime, Integer parkingCost) {
        this.licensePlateNumber = licensePlateNumber;
        this.enterTime = enterTime;
        this.parkingCost = parkingCost;
    }

    /**
     * Static factory method to create a {@link hu.becseimiklos.prt.hw.vo.InProgressParkingVO}.
     * @param parkingVO The ParkingVO containing the data of the Parking.
     * @param parkingCost The calculated cost of the Parking.
     * @return A new instance of InProgressParkingVO.
     */
    public static InProgressParkingVO of(ParkingVO parkingVO, Integer parkingCost) {
        String licensePlateNumber = parkingVO.getCar().getLicensePlateNumber();
        return new InProgressParkingVO(licensePlateNumber, parkingVO.getEnterTime(), parkingCost);
    }

}
