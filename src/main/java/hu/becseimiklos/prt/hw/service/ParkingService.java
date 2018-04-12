package hu.becseimiklos.prt.hw.service;

import hu.becseimiklos.prt.hw.vo.CarVO;
import hu.becseimiklos.prt.hw.vo.ParkingVO;

import java.time.LocalDateTime;
import java.util.List;

public interface ParkingService {

    ParkingVO enter(ParkingVO parkingVo);

    ParkingVO exit(ParkingVO parkingVo);

    ParkingVO findByCarAndAndExitTimeIsNull(CarVO carVo);

    List<ParkingVO> findByCar(CarVO carVo);

    List<ParkingVO> findAllInProcessParking();

    int calculateParking(LocalDateTime fromTime, Boolean hasParkingPass);
}
