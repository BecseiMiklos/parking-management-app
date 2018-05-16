package hu.becseimiklos.prt.hw.service.impl;


import hu.becseimiklos.prt.hw.entity.Car;
import hu.becseimiklos.prt.hw.mapper.CarMapper;
import hu.becseimiklos.prt.hw.repository.CarRepository;
import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * CarService implementation.
 */
@Service
@Transactional
@Slf4j
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    /**
     * Saves a Car entity to the database.
     *
     * @param carVO the car object to save
     * @return the saved entity
     */
    @Override
    public CarVO save(CarVO carVO) {
        log.error(carVO.toString());
        carVO.setLicensePlateNumber(carVO.getLicensePlateNumber().toUpperCase());
        Car newCar = CarMapper.toEntity(carVO);
        Car savedCar = carRepository.save(newCar);
        return CarMapper.toVO(savedCar);
    }

    @Override
    public void delete(Long id) {
        Optional<Car> car = carRepository.findById(id);
        car.ifPresent(car1 -> carRepository.delete(car1));
    }

    @Override
    public List<CarVO> findAll() {
        return CarMapper.toVO(carRepository.findAll());
    }

    @Override
    public CarVO findByLicensePlateNumber(String licensePlateNumber) {
        return CarMapper.toVO(carRepository.findByLicensePlateNumber(licensePlateNumber));
    }

}
