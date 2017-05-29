package hu.becseimiklos.prt.hw.ui.test;


import hu.becseimiklos.prt.hw.vo.CarVo;
import hu.becseimiklos.prt.hw.vo.ParkingVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.TestContextBootstrapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@BootstrapWith(TestContextBootstrapper.class)
public class ParkingServiceTest {

    @Test
    public void testEnter() {
        ParkingVo parkingVo = new ParkingVo();
        parkingVo.setEnterTime(null);
        parkingVo.setExitTime(LocalDateTime.of(2020, 05, 30, 12, 00));
        parkingVo.setPaidCost(null);
        parkingVo.setCar(new CarVo());
        //  ParkingVo enteredParking = parkingService.enter(parkingVo);

//        Assert.assertNotNull(enteredParking.getEnterTime());
//        Assert.assertEquals(enteredParking.getExitTime(), LocalDateTime.of(2020, 05, 30, 12, 00));
//        Assert.assertNotNull(parkingVo.getPaidCost());
//        Assert.assertNull(enteredParking.getCar());
    }
}
