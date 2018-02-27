package hu.becseimiklos.prt.hw.service.impl;


import hu.becseimiklos.prt.hw.entity.Parking;
import hu.becseimiklos.prt.hw.mapper.CarMapper;
import hu.becseimiklos.prt.hw.mapper.ParkingMapper;
import hu.becseimiklos.prt.hw.repository.ParkingRepository;
import hu.becseimiklos.prt.hw.service.ParkingService;
import hu.becseimiklos.prt.hw.vo.CarVo;
import hu.becseimiklos.prt.hw.vo.ParkingVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    ParkingRepository parkingRepository;

    @Override
    public ParkingVo enter(ParkingVo parkingVo) {
        parkingVo.setEnterTime(LocalDateTime.now());
        parkingVo.setPaidCost(0);
        Parking newParking = ParkingMapper.toEntity(parkingVo);

        Parking savedParking = parkingRepository.save(newParking);
        if (savedParking == null) {
            log.warn("Saving of new parking was unsuccessful: " + newParking);
        } else {
            log.debug("Saving successful: " + newParking);
        }
        return ParkingMapper.toVo(savedParking);
    }

    @Override
    public ParkingVo exit(ParkingVo parkingVo) {
        parkingVo.setExitTime(LocalDateTime.now());
        parkingVo.setPaidCost(calculateParking(parkingVo.getEnterTime(), parkingVo.getCar().getHasParkingPass()));
        Parking modifiedParking = ParkingMapper.toEntity(parkingVo);

        Parking savedParking = parkingRepository.save(modifiedParking);
        if (savedParking == null) {
            log.warn("Saving of new parking was unsuccessful: " + modifiedParking);
        } else {
            log.debug("Saving successful: " + modifiedParking);
        }
        return ParkingMapper.toVo(savedParking);
    }

    @Override
    public ParkingVo findByCarAndAndExitTimeIsNull(CarVo carVo) {
        return ParkingMapper.toVo(parkingRepository.findByCarAndAndExitTimeIsNull(CarMapper.toEntity(carVo)));
    }

    @Override
    public List<ParkingVo> findByCar(CarVo carVo) {
        return ParkingMapper.toVo(parkingRepository.findByCar(CarMapper.toEntity(carVo)));
    }

    @Override
    public List<ParkingVo> findAllInProcessParking() {
        return ParkingMapper.toVo(parkingRepository.findAllByExitTimeIsNull());
    }

    @Override
    public int calculateParking(LocalDateTime fromTime, Boolean hasParkingPass) {
        if (hasParkingPass) {
            return 0;
        } else {
            double elapsedSeconds = ChronoUnit.SECONDS.between(fromTime, LocalDateTime.now());
            log.debug("elapsed Seconds:" + elapsedSeconds);
            return (int) Math.round((elapsedSeconds / 3600) * 250);
        }
    }
}
