package hu.becseimiklos.prt.hw.service;

import hu.becseimiklos.prt.hw.vo.CarVO;

import java.util.List;

/**
 * Contains methods which can be used to access and manipulate {@link hu.becseimiklos.prt.hw.entity.Car} entities.
 */
public interface CarService {

    /**
     * Deletes the {@link hu.becseimiklos.prt.hw.entity.Car} with the given id.
     *
     * @param id The id of the {@link hu.becseimiklos.prt.hw.entity.Car} to be deleted.
     */
    void delete(Long id);

    /**
     * Saves a Car to the database.
     *
     * @param carVo The value object containing the data of the Car.
     * @return The saved Car.
     */
    CarVO save(CarVO carVo);

    /**
     * Finds all Cars.
     *
     * @return All stored cars.
     */
    List<CarVO> findAll();

    /**
     * Finds a car by license plate number.
     *
     * @param licensePlateNumber The license plate to search by.
     * @return The Car with the matching license plate.
     */
    CarVO findByLicensePlateNumber(String licensePlateNumber);

}
