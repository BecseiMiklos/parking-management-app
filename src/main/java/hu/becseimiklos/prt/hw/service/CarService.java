package hu.becseimiklos.prt.hw.service;

import hu.becseimiklos.prt.hw.vo.CarVO;

import java.util.List;

public interface CarService {

    void delete(Long id);

    CarVO save(CarVO carVo);

    List<CarVO> findAll();

    CarVO findByLicensePlateNumber(String licensePlateNumber);

    List<CarVO> findAllByLicensePlateNumberIsLike(String licensePlateNumber);
}
