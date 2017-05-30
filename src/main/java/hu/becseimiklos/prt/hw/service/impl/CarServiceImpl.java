package hu.becseimiklos.prt.hw.service.impl;


import hu.becseimiklos.prt.hw.entity.Car;
import hu.becseimiklos.prt.hw.mapper.CarMapper;
import hu.becseimiklos.prt.hw.repository.CarRepository;
import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Configurable
@Transactional(propagation = Propagation.REQUIRED)
public class CarServiceImpl implements CarService {


    private static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    CarRepository carRepository;

    @Override
    public void delete(Long id) {
        carRepository.delete(id);
    }

    @Override
    public CarVo save(CarVo carVo) {
        Car newCar = CarMapper.toDto(carVo);

        Car savedCar = carRepository.save(newCar);
        if (savedCar == null) {
            logger.warn("Saving of new car was unsuccessful: " + newCar.getLicensePlateNumber());
        } else {
            logger.debug("Saving successful: " + newCar);
        }
        return CarMapper.toVo(savedCar);
    }

    @Override
    public List<CarVo> findAll() {
        return CarMapper.toVo(carRepository.findAll());
    }

    @Override
    public CarVo findByLicensePlateNumber(String licensePlateNumber) {
        return CarMapper.toVo(carRepository.findByLicensePlateNumber(licensePlateNumber));
    }
}
