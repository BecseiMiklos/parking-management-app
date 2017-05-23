package hu.becseimiklos.prt.hw.service.impl;


import hu.becseimiklos.prt.hw.data.entity.Car;
import hu.becseimiklos.prt.hw.data.repository.CarRepository;
import hu.becseimiklos.prt.hw.mapper.CarMapper;
import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CarServiceImpl implements CarService {

    private static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;

    @Override
    public CarVo save(CarVo carVo) {
        Car newCar = CarMapper.toDto(carVo);

        Car savedCar = carRepository.save(newCar);
        if (savedCar == null) {
            logger.warn("Saving of new car was unsuccessful: " + newCar.getLicensePlateNumber());
        } else {
            logger.debug("Saving successful: " + newCar.getLicensePlateNumber());
        }
        return CarMapper.toVo(savedCar);
    }
}
