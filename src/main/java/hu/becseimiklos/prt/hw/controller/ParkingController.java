package hu.becseimiklos.prt.hw.controller;

import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.service.ParkingService;
import hu.becseimiklos.prt.hw.vo.CarVO;
import hu.becseimiklos.prt.hw.vo.ParkingVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles the REST requests related to parkings.
 */
@RestController
@RequestMapping("/parking")
@Slf4j
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    @Autowired
    CarService carService;

    @PostMapping("/enter")
    public ParkingVO save(@RequestBody ParkingVO parkingVO) {
        log.trace("ParkingController start() called");
        return parkingService.enter(parkingVO);
    }

    @PostMapping("/exit/{licensePlate}")
    public ParkingVO exit(@PathVariable String licensePlate) {
        log.trace("ParkingController start() called");
        CarVO carVO = carService.findByLicensePlateNumber(licensePlate);
        ParkingVO parkingVO = parkingService.findByCarAndAndExitTimeIsNull(carVO);
        ParkingVO exit = parkingService.exit(parkingVO);
        return exit;
//        return parkingService.enter(parkingVO);
    }
}
