package hu.becseimiklos.prt.hw.controller;

import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVO;
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
public class CarController {

    private static final Logger log = Logger.getLogger(CarController.class.getName());

    @Autowired
    CarService carService;

    @GetMapping("/list")
    public List<CarVO> list() {
        log.info("CarController list called");
        return carService.findAll();
    }

    @PostMapping("/save")
    public CarVO save(@RequestBody CarVO carVo) {
        return carService.save(carVo);
    }

}
