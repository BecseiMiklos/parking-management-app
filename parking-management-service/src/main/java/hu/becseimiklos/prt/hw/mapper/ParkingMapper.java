package hu.becseimiklos.prt.hw.mapper;

import hu.becseimiklos.prt.hw.data.entity.Car;
import hu.becseimiklos.prt.hw.data.entity.Parking;
import hu.becseimiklos.prt.hw.vo.CarVo;
import hu.becseimiklos.prt.hw.vo.ParkingVo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ParkingMapper {

    private static Logger logger = LoggerFactory.getLogger(CarMapper.class);

    private static ModelMapper mapper = new ModelMapper();

    public static ParkingVo toVo(Parking parking) {
        if (parking == null) {
            logger.warn("ParkingEntity is null!");
            return null;
        }
        return mapper.map(parking, ParkingVo.class);
    }

    public static List<ParkingVo> toVo(List<Parking> parkings) {
        List<ParkingVo> parkingVos = new ArrayList<>();
        ParkingVo mappedParking;
        for (Parking parking : parkings) {
            mappedParking = mapper.map(parking, ParkingVo.class);
            parkingVos.add(mappedParking);
        }
        return parkingVos;
    }

    public static Parking toDto(ParkingVo parkingVo) {
        if (parkingVo == null) {
            logger.warn("ParkingVo is null!");
            return null;
        }
        return mapper.map(parkingVo, Parking.class);
    }
}
