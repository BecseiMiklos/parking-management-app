package hu.becseimiklos;

import hu.becseimiklos.prt.hw.main.Main;
import hu.becseimiklos.prt.hw.repository.ParkingRepository;
import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.service.ParkingService;
import hu.becseimiklos.prt.hw.vo.CarVo;
import hu.becseimiklos.prt.hw.vo.ParkingVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = Main.class)
@TestPropertySource(locations = "classpath:test.properties")
public class ParkingServiceTest {

    @Autowired
    CarService carService;

    @Autowired
    ParkingService parkingService;

    @Autowired
    ParkingRepository parkingRepository;

    @Before
    public void prepateTheDatabase() {
        ParkingVo parkingVo = new ParkingVo();
        parkingVo.setCar(new CarVo());
        parkingVo.setEnterTime(LocalDateTime.of(2017, 04, 27, 10, 25));
        parkingVo.setExitTime(null);
        parkingService.enter(parkingVo);

        ParkingVo parkingVo2 = new ParkingVo();
        parkingVo.setCar(new CarVo());
        parkingVo.setEnterTime(LocalDateTime.of(2017, 05, 27, 12, 25));
        parkingVo.setExitTime(null);
        parkingService.enter(parkingVo2);
    }

    @Test
    public void enterTest() {
        ParkingVo parkingVo = new ParkingVo();
        parkingVo.setCar(new CarVo());
        parkingVo.setEnterTime(LocalDateTime.of(1995, 04, 27, 10, 25));
        parkingVo.setPaidCost(150);
        ParkingVo parkedVo = parkingService.enter(parkingVo);
        Assert.assertNotNull("parkedVo shouldn't be NULL", parkedVo);
        Assert.assertEquals("parking should be 0 when entering", Integer.valueOf(0), parkedVo.getPaidCost());
        Assert.assertNotEquals(LocalDateTime.of(1995, 04, 27, 10, 25), parkedVo.getEnterTime());
    }

    @Test
    public void exitTest() {
        CarVo carVo = new CarVo();
        carVo.setHasParkingPass(false);
        carVo.setColor("Red");
        carVo.setBrand("Honda");
        carVo.setLicensePlateNumber("AAA000");
        carService.save(carVo);

        ParkingVo parkingVo = new ParkingVo();
        parkingVo.setCar(carVo);
        parkingVo.setEnterTime(LocalDateTime.of(2017, 05, 31, 9, 0));
        parkingVo.setPaidCost(0);
        ParkingVo exitedVo = parkingService.exit(parkingVo);
        Assert.assertNotNull("parkedVo shouldn't be NULL", exitedVo);
        Assert.assertNotEquals("parking shouldn't be 0 at exit", Integer.valueOf(0), exitedVo.getPaidCost());
        Assert.assertNotEquals(LocalDateTime.of(1995, 04, 27, 10, 25), exitedVo.getEnterTime());
    }

    @Test
    public void findByCarTest() {
        CarVo carVo = new CarVo();
        carVo.setHasParkingPass(true);
        carVo.setColor("TestColor");
        carVo.setBrand("TestBrand");
        carVo.setLicensePlateNumber("AAA000");
        carVo = carService.save(carVo);

        ParkingVo parkingVo = new ParkingVo();
        parkingVo.setCar(carVo);
        parkingVo.setEnterTime(LocalDateTime.of(2017, 05, 31, 9, 30));
        parkingVo.setExitTime(LocalDateTime.now());
        parkingService.enter(parkingVo);

        List<ParkingVo> foundParkingVos = parkingService.findByCar(carVo);
        Assert.assertNotNull("Should contain one element", foundParkingVos);
    }

    @Test
    public void findByCarAndAndExitTimeIsNullTest() {
        CarVo carVo = new CarVo();
        carVo.setHasParkingPass(true);
        carVo.setColor("TestColor");
        carVo.setBrand("TestBrand");
        carVo.setLicensePlateNumber("AAA000");
        carVo = carService.save(carVo);

        ParkingVo parkingVo = new ParkingVo();
        parkingVo.setCar(carVo);
        ParkingVo savedParkingVo = parkingService.enter(parkingVo);

        ParkingVo foundParkingVo = parkingService.findByCarAndAndExitTimeIsNull(carVo);
        Assert.assertEquals("Should be the same", savedParkingVo.getCar().getLicensePlateNumber(), foundParkingVo.getCar().getLicensePlateNumber());
        Assert.assertNull(foundParkingVo.getExitTime());
    }

    @Test
    public void findAllInProcessParkingTest() {
        Assert.assertNotNull(parkingService.findAllInProcessParking());
    }

    @Test
    public void calculateParkingHasParkingPassTest() {
        int parkingCost = parkingService.calculateParking(LocalDateTime.now().minusHours(1), true);
        Assert.assertEquals(0, parkingCost);
    }

    @Test
    public void calculateParkingNoParkingPassTest() {
        int parkingCost = parkingService.calculateParking(LocalDateTime.now().minusHours(1), false);
        Assert.assertEquals(250, parkingCost);
    }
}
