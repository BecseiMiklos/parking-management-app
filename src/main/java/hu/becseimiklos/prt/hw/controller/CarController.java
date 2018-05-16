package hu.becseimiklos.prt.hw.controller;

import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVO;
import hu.becseimiklos.prt.hw.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * Finds all cars stored in the database.
     *
     * @return all cars.
     */
    @GetMapping("/list")
    public ResponseVO<CarVO> list() {
        ResponseVO<CarVO> ret = new ResponseVO<>();
        log.trace("CarService list() called");
        return ret.setData(carService.findAll());
    }

    /**
     * Saves the given Car to the database.
     *
     * @param carVO the CarVO to save.
     * @return the saved Car.
     */
    @PostMapping("/save")
    public ResponseVO<CarVO> save(@RequestBody CarVO carVO) {
        ResponseVO<CarVO> ret = new ResponseVO<>();
        log.trace("CarService save() called");
        try {
            ret.setSingleData(carService.save(carVO));
        } catch (DataIntegrityViolationException e) {
            return ret.setFailure("License plate number must be unique!");
        }
        return ret;
    }

    /**
     * Searches the {@link hu.becseimiklos.prt.hw.entity.Car} with the given {@code id}, and deletes it.
     *
     * @param id {@link hu.becseimiklos.prt.hw.entity.Car} id.
     * @return {@link hu.becseimiklos.prt.hw.vo.ResponseVO} representing the outcome of the request.
     */
    @PostMapping("/delete/{id}")
    public ResponseVO<Void> delete(@PathVariable Long id) {
        ResponseVO<Void> ret = new ResponseVO<>();
        log.trace("CarService delete() called");
        carService.delete(id);
        return ret;
    }

}
