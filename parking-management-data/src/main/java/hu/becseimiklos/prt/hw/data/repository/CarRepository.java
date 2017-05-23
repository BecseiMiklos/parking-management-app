package hu.becseimiklos.prt.hw.data.repository;

import hu.becseimiklos.prt.hw.data.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository <Car, Long> {

    List<Car> findAll();
}
