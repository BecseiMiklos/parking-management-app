package hu.becseimiklos.prt.hw.controller;

import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car")
@Slf4j
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/list")
    public List<CarVo> list() {
        return carService.findAll();
    }

    @PostMapping("/save")
    public CarVo save(CarVo carVo) {
        return carService.save(carVo);
    }

}
