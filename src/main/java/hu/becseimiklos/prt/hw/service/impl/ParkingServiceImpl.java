package hu.becseimiklos.prt.hw.service.impl;


import hu.becseimiklos.prt.hw.entity.Parking;
import hu.becseimiklos.prt.hw.mapper.CarMapper;
import hu.becseimiklos.prt.hw.mapper.ParkingMapper;
import hu.becseimiklos.prt.hw.repository.ParkingRepository;
import hu.becseimiklos.prt.hw.service.ParkingService;
import hu.becseimiklos.prt.hw.vo.CarVo;
import hu.becseimiklos.prt.hw.vo.ParkingVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Configurable
@Transactional(propagation = Propagation.REQUIRED)
public class ParkingServiceImpl implements ParkingService {

    private static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    ParkingRepository parkingRepository;

    @Override
    public ParkingVo enter(ParkingVo parkingVo) {
        parkingVo.setEnterTime(LocalDateTime.now());
        parkingVo.setPaidCost(0);
        Parking newParking = ParkingMapper.toDto(parkingVo);

        Parking savedParking = parkingRepository.save(newParking);
        if (savedParking == null) {
            logger.warn("Saving of new parking was unsuccessful: " + newParking);
        } else {
            logger.debug("Saving successful: " + newParking);
        }
        return ParkingMapper.toVo(savedParking);
    }

    @Override
    public ParkingVo exit(ParkingVo parkingVo) {
        parkingVo.setExitTime(LocalDateTime.now());
        parkingVo.setPaidCost(calculateParking(parkingVo.getEnterTime(), parkingVo.getCar().getHasParkingPass()));
        Parking modifiedParking = ParkingMapper.toDto(parkingVo);

        Parking savedParking = parkingRepository.save(modifiedParking);
        if (savedParking == null) {
            logger.warn("Saving of new parking was unsuccessful: " + modifiedParking);
        } else {
            logger.debug("Saving successful: " + modifiedParking);
        }
        return ParkingMapper.toVo(savedParking);
    }

    @Override
    public ParkingVo findByCarAndAndExitTimeIsNull(CarVo carVo) {
        return ParkingMapper.toVo(parkingRepository.findByCarAndAndExitTimeIsNull(CarMapper.toDto(carVo)));
    }

    @Override
    public List<ParkingVo> findByCar(CarVo carVo) {
        return ParkingMapper.toVo(parkingRepository.findByCar(CarMapper.toDto(carVo)));
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
            logger.debug("elapsed Seconds:" + elapsedSeconds);
            int parkingCost = (int) Math.round((elapsedSeconds / 3600) * 250);
            return parkingCost;
        }
    }
}
