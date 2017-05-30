package hu.becseimiklos.prt.hw.repository;


import hu.becseimiklos.prt.hw.entity.Car;
import hu.becseimiklos.prt.hw.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    Parking findByCarAndAndExitTimeIsNull(Car car);
    List<Parking> findByCar(Car car);
    List<Parking> findAllByExitTimeIsNull();
}
