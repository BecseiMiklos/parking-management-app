package hu.becseimiklos.prt.hw.data.repository;

import hu.becseimiklos.prt.hw.data.entity.Car;
import hu.becseimiklos.prt.hw.data.entity.Parking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findAll();
    Car findByLicensePlateNumber(String licensePlateNumber);

}
