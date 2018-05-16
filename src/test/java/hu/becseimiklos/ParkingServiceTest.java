package hu.becseimiklos;

import hu.becseimiklos.prt.hw.main.Main;
import hu.becseimiklos.prt.hw.mapper.ParkingMapper;
import hu.becseimiklos.prt.hw.repository.ParkingRepository;
import hu.becseimiklos.prt.hw.service.CarService;
import hu.becseimiklos.prt.hw.service.ParkingService;
import hu.becseimiklos.prt.hw.vo.CarVO;
import hu.becseimiklos.prt.hw.vo.InProgressParkingVO;
import hu.becseimiklos.prt.hw.vo.ParkingVO;
import hu.becseimiklos.prt.hw.vo.ResponseVO;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = Main.class)
@TestPropertySource("classpath:test.properties")
public class ParkingServiceTest {

    @Autowired
    CarService carService;

    @Autowired
    ParkingService parkingService;

    @Autowired
    ParkingRepository parkingRepository;

    @Before
    public void prepateTheDatabase() {
        ParkingVO parkingVo = new ParkingVO();
        parkingVo.setCar(new CarVO());
        parkingVo.setEnterTime(LocalDateTime.of(2017, 04, 27, 10, 25));
        parkingVo.setExitTime(null);
        parkingService.enter(parkingVo);

        ParkingVO parkingVo2 = new ParkingVO();
        parkingVo.setCar(new CarVO());
        parkingVo.setEnterTime(LocalDateTime.of(2017, 05, 27, 12, 25));
        parkingVo.setExitTime(null);
        parkingService.enter(parkingVo2);
    }

    @Test
    public void enterTest() {
        ParkingVO parkingVo = new ParkingVO();
        parkingVo.setCar(new CarVO());
        parkingVo.setEnterTime(LocalDateTime.of(1995, 04, 27, 10, 25));
        parkingVo.setPaidCost(150);
        ParkingVO parkedVo = parkingService.enter(parkingVo);
        assertNotNull("parkedVo shouldn't be NULL", parkedVo);
        Assert.assertEquals("parking should be 0 when entering", Integer.valueOf(0), parkedVo.getPaidCost());
        Assert.assertNotEquals(LocalDateTime.of(1995, 04, 27, 10, 25), parkedVo.getEnterTime());
    }

    @Test
    public void exitTest() {
        CarVO carVo = new CarVO();
        carVo.setHasParkingPass(false);
        carVo.setColor("Red");
        carVo.setBrand("Honda");
        carVo.setLicensePlateNumber("AAA000");
        carService.save(carVo);

        ParkingVO parkingVo = new ParkingVO();
        parkingVo.setCar(carVo);
        parkingVo.setEnterTime(LocalDateTime.of(2017, 05, 31, 9, 0));
        parkingVo.setPaidCost(0);
        ParkingVO exitedVo = parkingService.exit(parkingVo);
        assertNotNull("parkedVo shouldn't be NULL", exitedVo);
        Assert.assertNotEquals("parking shouldn't be 0 at exit", Integer.valueOf(0), exitedVo.getPaidCost());
        Assert.assertNotEquals(LocalDateTime.of(1995, 04, 27, 10, 25), exitedVo.getEnterTime());
    }

    @Test
    public void findByCarTest() {
        CarVO carVo = new CarVO();
        carVo.setHasParkingPass(true);
        carVo.setColor("TestColor");
        carVo.setBrand("TestBrand");
        carVo.setLicensePlateNumber("AAA000");
        carVo = carService.save(carVo);

        ParkingVO parkingVo = new ParkingVO();
        parkingVo.setCar(carVo);
        parkingVo.setEnterTime(LocalDateTime.of(2017, 05, 31, 9, 30));
        parkingVo.setExitTime(LocalDateTime.now());
        parkingService.enter(parkingVo);

        List<ParkingVO> foundParkingVos = parkingService.findByCar(carVo);
        assertNotNull("Should contain one element", foundParkingVos);
    }

    @Test
    public void findByCarAndAndExitTimeIsNullTest() {
        CarVO carVo = new CarVO();
        carVo.setHasParkingPass(true);
        carVo.setColor("TestColor");
        carVo.setBrand("TestBrand");
        carVo.setLicensePlateNumber("AAA000");
        carVo = carService.save(carVo);

        ParkingVO parkingVo = new ParkingVO();
        parkingVo.setCar(carVo);
        ParkingVO savedParkingVo = parkingService.enter(parkingVo);

        ParkingVO foundParkingVo = parkingService.findByCarAndAndExitTimeIsNull(carVo);
        Assert.assertEquals("Should be the same", savedParkingVo.getCar().getLicensePlateNumber(), foundParkingVo.getCar().getLicensePlateNumber());
        Assert.assertNull(foundParkingVo.getExitTime());
    }

    @Test
    public void findAllInProcessParkingTest() {
        assertNotNull(parkingService.findAllInProgress());
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

    @Test
    public void parkingMapperNullTest() {
        Assert.assertNull(ParkingMapper.toVo(null));
        Assert.assertNull(ParkingMapper.toEntity(null));
    }

    @Test
    public void inProgressParkingVOTest() {
        CarVO carVo = new CarVO();
        carVo.setHasParkingPass(true);
        carVo.setColor("TestColor");
        carVo.setBrand("TestBrand");
        carVo.setLicensePlateNumber("GGG000");
        carVo = carService.save(carVo);

        ParkingVO parkingVo = new ParkingVO();
        parkingVo.setCar(carVo);
        parkingVo.setEnterTime(LocalDateTime.of(2017, 05, 31, 9, 0));
        parkingVo.setPaidCost(0);
        InProgressParkingVO inProgressParkingVO = InProgressParkingVO.of(parkingVo, 100);
        assertNotNull(inProgressParkingVO);
        assertEquals(inProgressParkingVO.getEnterTime(), LocalDateTime.of(2017, 05, 31, 9, 0));
        assertEquals(inProgressParkingVO.getLicensePlateNumber(), "GGG000");
        assertEquals(inProgressParkingVO.getParkingCost().longValue(), (long) 100);
    }

    @Test
    public void responseVOListTest() {
        ResponseVO<Integer> ret = new ResponseVO<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        ret.setData(list);
        assertEquals(list, ret.getData());
    }

    @Test
    public void responseVOSingleDataTest() {
        ResponseVO<Integer> ret = new ResponseVO<>();
        ret.setSingleData(0);
        assertEquals(ret.getData().get(0).longValue(), (long) 0);
        assertTrue(ret.isSuccess());
    }

    @Test
    public void responseVOFailureTest() {
        ResponseVO<Integer> ret = new ResponseVO<>();
        ret.setFailure("Fatal error");
        assertFalse(ret.isSuccess());
        assertEquals(ret.getErrorMessage(), "Fatal error");
    }

    @Test
    public void responseVOSetSuccessTest() {
        ResponseVO<Integer> ret = new ResponseVO<>();
        ret.setSuccess(false);
        ret.setErrorMessage("Error!");
        assertFalse(ret.isSuccess());
        assertEquals(ret.getErrorMessage(), "Error!");
    }
}
