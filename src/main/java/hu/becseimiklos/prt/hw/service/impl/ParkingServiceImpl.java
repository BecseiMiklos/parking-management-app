package hu.becseimiklos.prt.hw.service.impl;


import hu.becseimiklos.prt.hw.entity.Parking;
import hu.becseimiklos.prt.hw.mapper.CarMapper;
import hu.becseimiklos.prt.hw.mapper.ParkingMapper;
import hu.becseimiklos.prt.hw.repository.ParkingRepository;
import hu.becseimiklos.prt.hw.service.ParkingService;
import hu.becseimiklos.prt.hw.vo.CarVO;
import hu.becseimiklos.prt.hw.vo.ParkingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Logger;

/**
 * ParkingService implementation.
 */
@Service
@Transactional
public class ParkingServiceImpl implements ParkingService {

    private static final Logger log = Logger.getLogger(ParkingServiceImpl.class.getName());

    @Autowired
    ParkingRepository parkingRepository;

    @Override
    public ParkingVO enter(ParkingVO parkingVo) {
        parkingVo.setEnterTime(LocalDateTime.now());
        parkingVo.setPaidCost(0);
        Parking newParking = ParkingMapper.toEntity(parkingVo);

        Parking savedParking = parkingRepository.save(newParking);
        if (savedParking == null) {
            log.warning(() -> "Saving of new parking was unsuccessful: " + newParking);
        } else {
            log.info("Saving successful: " + newParking);
        }
        return ParkingMapper.toVo(savedParking);
    }

    @Override
    public ParkingVO exit(ParkingVO parkingVo) {
        parkingVo.setExitTime(LocalDateTime.now());
        parkingVo.setPaidCost(calculateParking(parkingVo.getEnterTime(), parkingVo.getCar().getHasParkingPass()));
        Parking modifiedParking = ParkingMapper.toEntity(parkingVo);

        Parking savedParking = parkingRepository.save(modifiedParking);
        if (savedParking == null) {
            log.info("Saving of new parking was unsuccessful: " + modifiedParking);
        } else {
            log.info("Saving successful: " + modifiedParking);
        }
        return ParkingMapper.toVo(savedParking);
    }

    @Override
    public ParkingVO findByCarAndAndExitTimeIsNull(CarVO carVo) {
        return ParkingMapper.toVo(parkingRepository.findByCarAndAndExitTimeIsNull(CarMapper.toEntity(carVo)));
    }

    @Override
    public List<ParkingVO> findByCar(CarVO carVo) {
        return ParkingMapper.toVo(parkingRepository.findByCar(CarMapper.toEntity(carVo)));
    }

    @Override
    public List<ParkingVO> findAllInProcessParking() {
        return ParkingMapper.toVo(parkingRepository.findAllByExitTimeIsNull());
    }

    @Override
    public int calculateParking(LocalDateTime fromTime, Boolean hasParkingPass) {
        if (hasParkingPass) {
            return 0;
        } else {
            double elapsedSeconds = ChronoUnit.SECONDS.between(fromTime, LocalDateTime.now());
            log.info("elapsed Seconds:" + elapsedSeconds);
            return (int) Math.round((elapsedSeconds / 3600) * 250);
        }
    }
}
