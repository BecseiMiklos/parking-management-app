package hu.becseimiklos.prt.hw.controller;

import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.service.ParkingService;
import hu.becseimiklos.prt.hw.vo.CarVO;
import hu.becseimiklos.prt.hw.vo.InProgressParkingVO;
import hu.becseimiklos.prt.hw.vo.ParkingVO;
import hu.becseimiklos.prt.hw.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Starts a parking using the supplied data from the {@code ParkingVO}.
     *
     * @param parkingVO Supplies data for the parking.
     * @return The saved parking.
     */
    @PostMapping("/enter")
    public ResponseVO<ParkingVO> enter(@RequestBody ParkingVO parkingVO) {
        log.trace("ParkingController enter() called");
        ResponseVO<ParkingVO> ret = new ResponseVO<>();

        ParkingVO currentParking = parkingService.findByCarAndAndExitTimeIsNull(parkingVO.getCar());
        if (currentParking != null) {
            return ret.setFailure("This car is already parking!");
        }
        return ret.setSingleData(parkingService.enter(parkingVO));
    }

    /**
     * Stops the parking of the car with given license plate number. If there is neither car with the given license plate or active parking returns the appropriate error message.
     *
     * @param licensePlate The parking car's license plate number.
     * @return {@link hu.becseimiklos.prt.hw.vo.ParkingVO} with the stopped parking data.
     */
    @PostMapping("/exit/{licensePlate}")
    public ResponseVO<ParkingVO> exit(@PathVariable String licensePlate) {
        log.trace("ParkingController exit() called");
        ResponseVO<ParkingVO> ret = new ResponseVO<>();
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

    /**
     * Finds all parkings which are in progress.
     *
     * @return List of {@link hu.becseimiklos.prt.hw.vo.InProgressParkingVO} containing the important data of ongoing parkings.
     */
    @GetMapping("/findAllInProgress")
    public ResponseVO<InProgressParkingVO> findAllInProgress() {
        log.trace("ParkingController findAllInProgress() called");
        ResponseVO<InProgressParkingVO> ret = new ResponseVO<>();
        List<ParkingVO> allInProgress = parkingService.findAllInProgress();

        List<InProgressParkingVO> result = allInProgress.stream().map(parkingVO -> {
            int parkingCost = parkingService.calculateParking(parkingVO.getEnterTime(), parkingVO.getCar().getHasParkingPass());
            return InProgressParkingVO.of(parkingVO, parkingCost);
        }).collect(Collectors.toList());
        ret.setData(result);
        return ret;
    }

}
