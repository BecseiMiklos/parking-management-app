package hu.becseimiklos.prt.hw.controller;

import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.service.ParkingService;
import hu.becseimiklos.prt.hw.vo.CarVO;
import hu.becseimiklos.prt.hw.vo.ParkingVO;
import hu.becseimiklos.prt.hw.vo.ResponseVO;
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
    public ResponseVO<ParkingVO> enter(@RequestBody ParkingVO parkingVO) {
        log.trace("ParkingController start() called");
        ResponseVO<ParkingVO> ret = new ResponseVO<>();

        ParkingVO currentParking = parkingService.findByCarAndAndExitTimeIsNull(parkingVO.getCar());
        if (currentParking != null) {
            return ret.setFailure("This car is already parking!");
        }
        return ret.setSingleData(parkingService.enter(parkingVO));
    }

    @PostMapping("/exit/{licensePlate}")
    public ResponseVO<ParkingVO> exit(@PathVariable String licensePlate) {
        ResponseVO<ParkingVO> ret = new ResponseVO<>();
        log.trace("ParkingController start() called");
        CarVO carVO = carService.findByLicensePlateNumber(licensePlate);
        if (carVO == null) {
            return ret.setFailure("There is no car with this license plate number!");
        }

        ParkingVO currentParking = parkingService.findByCarAndAndExitTimeIsNull(carVO);
        if (currentParking == null) {
            return ret.setFailure("There is no car parking with this license plate number!");
        }
        return ret.setSingleData(parkingService.exit(currentParking));
    }
}
