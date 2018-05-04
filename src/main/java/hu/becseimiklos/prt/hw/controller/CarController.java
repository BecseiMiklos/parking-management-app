package hu.becseimiklos.prt.hw.controller;

import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/car")
@Slf4j
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/list")
    public List<CarVO> list() {
        log.trace("CarService list() called");
        return carService.findAll();
    }

    @PostMapping("/save")
    public CarVO save(@RequestBody CarVO carVo) {
        return carService.save(carVo);
    }

}
