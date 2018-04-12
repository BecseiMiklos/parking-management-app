package hu.becseimiklos.prt.hw.mapper;

import hu.becseimiklos.prt.hw.controller.CarController;
import hu.becseimiklos.prt.hw.entity.Parking;
import hu.becseimiklos.prt.hw.vo.ParkingVO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ParkingMapper {

    private static final Logger log = Logger.getLogger(ParkingMapper.class.getName());
    private static ModelMapper mapper = new ModelMapper();

    public static ParkingVO toVo(Parking parking) {
        if (parking == null) {
            log.warning("ParkingEntity is null!");
            return null;
        }
        return mapper.map(parking, ParkingVO.class);
    }

    public static List<ParkingVO> toVo(List<Parking> parkings) {
        List<ParkingVO> parkingVos = new ArrayList<>();
        ParkingVO mappedParking;
        for (Parking parking : parkings) {
            mappedParking = mapper.map(parking, ParkingVO.class);
            parkingVos.add(mappedParking);
        }
        return parkingVos;
    }

    public static Parking toEntity(ParkingVO parkingVo) {
        if (parkingVo == null) {
            log.warning("ParkingVO is null!");
            return null;
        }
        return mapper.map(parkingVo, Parking.class);
    }
}
