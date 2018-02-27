package hu.becseimiklos;


import hu.becseimiklos.prt.hw.main.Main;
import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.vo.CarVo;
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
@TestPropertySource(locations = "classpath:test.properties")
public class CarServiceTest {

    @Autowired
    CarService carService;

    @Before
    public void prepareTheDatabase() {
        CarVo carVo = new CarVo();
        carVo.setHasParkingPass(true);
        carVo.setColor("TestColor");
        carVo.setBrand("TestBrand");
        carVo.setLicensePlateNumber("AAA000");
        carService.save(carVo);

        CarVo carVo2 = new CarVo();
        carVo.setHasParkingPass(false);
        carVo.setColor("TestColor2");
        carVo.setBrand("TestBrand2");
        carVo.setLicensePlateNumber("BBB000");
        carService.save(carVo2);
    }

    @Test
    public void saveTest() {
        CarVo carVo = new CarVo();
        carVo.setHasParkingPass(false);
        carVo.setColor("TestColor2");
        carVo.setBrand("TestBrand2");
        carVo.setLicensePlateNumber("BBB000");
        CarVo savedCarVo = carService.save(carVo);
        Assert.assertNotNull(savedCarVo);
    }

    @Test
    public void findAllTest() {
        List<CarVo> allCars = carService.findAll();
        Assert.assertNotNull(allCars);
    }

    @Test
    public void findByLicensePlateNumberTest() {
        CarVo foundCarVo = carService.findByLicensePlateNumber("AAA000");
        Assert.assertEquals(foundCarVo.getLicensePlateNumber(), "AAA000");
    }

    @Test
    public void deleteTest() {
        CarVo deleteCandidate = carService.findByLicensePlateNumber("AAA000");
        carService.delete(deleteCandidate.getId());
        CarVo nullCandidate = carService.findByLicensePlateNumber("AAA000");
        Assert.assertNull(nullCandidate);
    }
}
