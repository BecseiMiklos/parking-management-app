package hu.becseimiklos.prt.hw.repository;


import hu.becseimiklos.prt.hw.entity.Car;
import hu.becseimiklos.prt.hw.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring repository class for {@link hu.becseimiklos.prt.hw.entity.Parking}.
 */
@Repository
@Transactional
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    /**
     * Finds a {@link hu.becseimiklos.prt.hw.entity.Car}'s ongoing parking.
     *
     * @param car The Car to search by.
     * @return The ongoing Parking with the given car, {@code null} if there is no ongoing parking.
     */
    Parking findByCarAndAndExitTimeIsNull(Car car);

    /**
     * Finds a list of {@link hu.becseimiklos.prt.hw.entity.Parking}s by the given Car.
     *
     * @param car The Car to search by.
     * @return A list of parking with the given car.
     */
    List<Parking> findByCar(Car car);

    /**
     * Finds all ongoing {@link hu.becseimiklos.prt.hw.entity.Parking}s.
     *
     * @return All ongoing {@link hu.becseimiklos.prt.hw.entity.Parking}s.
     */
    List<Parking> findAllByExitTimeIsNull();
}
