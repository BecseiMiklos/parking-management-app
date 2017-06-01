package hu.becseimiklos.prt.hw.mapper;

import hu.becseimiklos.prt.hw.entity.Parking;
import hu.becseimiklos.prt.hw.vo.ParkingVo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ParkingMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static ParkingVo toVo(Parking parking) {
        if (parking == null) {
            log.warn("ParkingEntity is null!");
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

    public static Parking toEntity(ParkingVo parkingVo) {
        if (parkingVo == null) {
            log.warn("ParkingVo is null!");
            return null;
        }
        return mapper.map(parkingVo, Parking.class);
    }
}
