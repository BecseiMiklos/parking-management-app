package hu.becseimiklos.prt.hw.data.repository;

import hu.becseimiklos.prt.hw.data.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CarRepository extends JpaRepository <Car, Long> {

    List<Car> findAll();
}
