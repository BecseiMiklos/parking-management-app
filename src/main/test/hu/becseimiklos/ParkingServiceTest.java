package hu.becseimiklos;

import hu.becseimiklos.prt.hw.main.Main;
import hu.becseimiklos.prt.hw.service.ParkingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Main.class)
public class ParkingServiceTest {

    @Autowired
    ParkingService parkingService;

    @Test
    public void createContext() {
//        throw new RuntimeException();
    }
}
