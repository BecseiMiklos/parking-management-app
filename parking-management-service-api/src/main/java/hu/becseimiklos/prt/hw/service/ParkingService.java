package hu.becseimiklos.prt.hw.service;

import hu.becseimiklos.prt.hw.vo.CarVo;
import hu.becseimiklos.prt.hw.vo.ParkingVo;

import java.time.LocalDateTime;
import java.util.List;

public interface ParkingService {

    ParkingVo enter(ParkingVo parkingVo);

    ParkingVo exit(ParkingVo parkingVo);

    ParkingVo findByCarAndAndExitTimeIsNull(CarVo carVo);

    List<ParkingVo> findByCar(CarVo carVo);

    List<ParkingVo> findAllInProcessParking();

    int calculateParking(LocalDateTime fromTime, Boolean hasParkingPass);
}
