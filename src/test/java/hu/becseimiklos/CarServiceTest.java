package hu.becseimiklos;


import hu.becseimiklos.prt.hw.entity.Car;
import hu.becseimiklos.prt.hw.main.Main;
import hu.becseimiklos.prt.hw.mapper.CarMapper;
import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = Main.class)
@TestPropertySource("classpath:test.properties")
public class CarServiceTest {

    @Autowired
    CarService carService;

    @Before
    public void prepareTheDatabase() {
        CarVO carVo = new CarVO();
        carVo.setHasParkingPass(true);
        carVo.setColor("TestColor");
        carVo.setBrand("TestBrand");
        carVo.setLicensePlateNumber("AAA000");
        carService.save(carVo);

        CarVO carVo2 = new CarVO();
        carVo2.setHasParkingPass(false);
        carVo2.setColor("TestColor2");
        carVo2.setBrand("TestBrand2");
        carVo2.setLicensePlateNumber("BBB000");
        carService.save(carVo2);
    }

    @Test
    public void saveTest() {
        CarVO carVo = new CarVO();
        carVo.setHasParkingPass(false);
        carVo.setColor("TestColor2");
        carVo.setBrand("TestBrand2");
        carVo.setLicensePlateNumber("BBB000");
        CarVO savedCarVo = carService.save(carVo);
        Assert.assertNotNull(savedCarVo);
    }

    @Test
    public void findAllTest() {
        List<CarVO> allCars = carService.findAll();
        Assert.assertNotNull(allCars);
    }

    @Test
    public void findByLicensePlateNumberTest() {
        CarVO foundCarVo = carService.findByLicensePlateNumber("AAA000");
        Assert.assertEquals(foundCarVo.getLicensePlateNumber(), "AAA000");
    }

    @Test
    public void deleteTest() {
        CarVO deleteCandidate = carService.findByLicensePlateNumber("AAA000");
        carService.delete(deleteCandidate.getId());
        CarVO nullCandidate = carService.findByLicensePlateNumber("AAA000");
        Assert.assertNull(nullCandidate);
    }

    @Test
    public void carMapperNullTest() {
        Car car = CarMapper.toEntity(null);
        Assert.assertNull(car);
    }
}
