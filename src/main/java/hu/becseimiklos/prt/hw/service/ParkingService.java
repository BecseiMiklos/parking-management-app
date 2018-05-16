package hu.becseimiklos.prt.hw.service;

import hu.becseimiklos.prt.hw.vo.CarVO;
import hu.becseimiklos.prt.hw.vo.ParkingVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Contains methods which can be used to access and manipulate {@link hu.becseimiklos.prt.hw.entity.Parking} entities.
 */
public interface ParkingService {

    /**
     * Starts a Parking with the given data.
     * @param parkingVo The value object containing the data of the Parking.
     * @return The saved Parking.
     */
    ParkingVO enter(ParkingVO parkingVo);

    /**
     * Stops a Parking with the given data.
     * @param parkingVo The value object containing the data of the Parking.
     * @return The updated Parking after stop.
     */
    ParkingVO exit(ParkingVO parkingVo);

    /**
     * Finds an ongoing parking by the given Car.
     * @param carVo Car to search by.
     * @return The ongoing Parking of the given Car.
     */
    ParkingVO findByCarAndAndExitTimeIsNull(CarVO carVo);

    /**
     * Finds all Parkings by the given Car.
     * @param carVo The Car to search by.
     * @return A list of Parkings with the given Car.
     */
    List<ParkingVO> findByCar(CarVO carVo);

    /**
     * Finds all ongoing Parkings.
     * @return List of ongoing Parkings.
     */
    List<ParkingVO> findAllInProgress();

    /**
     * Calculates the parking cost of a parking.
     * @param fromTime The start time of the parking.
     * @param hasParkingPass Tells if the Car has parking pass, in this case the parking cost is zero.
     * @return The calculated parking cost.
     */
    int calculateParking(LocalDateTime fromTime, Boolean hasParkingPass);
}
