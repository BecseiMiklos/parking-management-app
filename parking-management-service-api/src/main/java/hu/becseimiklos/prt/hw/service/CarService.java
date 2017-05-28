package hu.becseimiklos.prt.hw.service;

import hu.becseimiklos.prt.hw.vo.CarVo;

import java.util.List;

public interface CarService {

    void delete(Long id);

    CarVo save(CarVo carVo);

    List<CarVo> findAll();

    CarVo findByLicensePlateNumber(String licensePlateNumber);
}
