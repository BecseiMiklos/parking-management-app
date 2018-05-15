package hu.becseimiklos.prt.hw.controller;

import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Manages the REST requests related to cars.
 */
@RestController
@RequestMapping("/car")
@Slf4j
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * Finds Cars which are matching the given criteria, if criteria is {@code null}, returns all cars.
     *
     * @param licensePlate The license plate number that will be applied as a criteria during search.
     * @return Cars matching to the given criteria.
     */
    @GetMapping("/list")
    public List<CarVO> list(@RequestParam("licensePlate") Optional<String> licensePlate) {
        log.trace("CarService list() called");
        if (licensePlate.isPresent()) {
            return carService.findAllByLicensePlateNumberIsLike(licensePlate.get());
        } else {
            return carService.findAll();
        }
    }

    /**
     * Saves the given Car to the database.
     *
     * @param carVO the CarVO to save.
     * @return the saved Car.
     */
    @PostMapping("/save")
    public CarVO save(@RequestBody CarVO carVO) {
        log.trace("CarService save() called");
        return carService.save(carVO);
    }

}
