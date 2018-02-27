package hu.becseimiklos.prt.hw.service.impl;


import hu.becseimiklos.prt.hw.entity.Car;
import hu.becseimiklos.prt.hw.mapper.CarMapper;
import hu.becseimiklos.prt.hw.repository.CarRepository;
import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Configurable
@Transactional(propagation = Propagation.REQUIRED)
@Slf4j
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Override
    public void delete(Long id) {
        carRepository.delete(id);
    }

    /**
     * Saves a Car entity to the database
     * @param carVo
     * @return the saved entity
     */
    @Override
    public CarVo save(CarVo carVo) {
        Car newCar = CarMapper.toEntity(carVo);

        Car savedCar = carRepository.save(newCar);
        if (savedCar == null) {
            log.warn("Saving of new car was unsuccessful: " + newCar.getLicensePlateNumber());
        } else {
            log.debug("Saving successful: " + newCar);
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
