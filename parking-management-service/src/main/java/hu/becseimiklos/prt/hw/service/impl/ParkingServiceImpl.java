package hu.becseimiklos.prt.hw.service.impl;

import hu.becseimiklos.prt.hw.data.entity.Parking;
import hu.becseimiklos.prt.hw.data.repository.ParkingRepository;
import hu.becseimiklos.prt.hw.mapper.CarMapper;
import hu.becseimiklos.prt.hw.mapper.ParkingMapper;
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

import java.time.LocalDate;
import java.util.Date;
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
        parkingVo.setEnterTime(new Date());
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
        parkingVo.setExitTime(new Date());
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
}
