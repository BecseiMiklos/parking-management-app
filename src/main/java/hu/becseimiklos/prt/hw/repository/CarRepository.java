package hu.becseimiklos.prt.hw.repository;

import hu.becseimiklos.prt.hw.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring repository class for {@link hu.becseimiklos.prt.hw.entity.Car}.
 */
@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {

    /**
     * Finds all cars stored in the database.
     *
     * @return all {@link hu.becseimiklos.prt.hw.entity.Car}s.
     */
    List<Car> findAll();

    /**
     * Searches for car with the given license plate number.
     *
     * @param licensePlateNumber The license number to search with.
     * @return Car with the given license plate number.
     */
    Car findByLicensePlateNumber(String licensePlateNumber);

}
