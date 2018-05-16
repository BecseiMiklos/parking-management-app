package hu.becseimiklos.prt.hw.mapper;

import hu.becseimiklos.prt.hw.entity.Parking;
import hu.becseimiklos.prt.hw.vo.ParkingVO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Utility class to convert {@link hu.becseimiklos.prt.hw.vo.ParkingVO} to {@link hu.becseimiklos.prt.hw.entity.Parking} and backwards.
 */
public class ParkingMapper {

    private static final Logger log = Logger.getLogger(ParkingMapper.class.getName());
    private static ModelMapper mapper = new ModelMapper();

    private ParkingMapper() {
    }

    /**
     * Converts a {@link hu.becseimiklos.prt.hw.entity.Parking} to a {@link hu.becseimiklos.prt.hw.vo.ParkingVO} value object.
     *
     * @param parking The {@link hu.becseimiklos.prt.hw.entity.Parking} entity to convert.
     * @return The converted {@link hu.becseimiklos.prt.hw.vo.ParkingVO} value object.
     */
    public static ParkingVO toVo(Parking parking) {
        if (parking == null) {
            log.warning("ParkingEntity is null!");
            return null;
        }
        return mapper.map(parking, ParkingVO.class);
    }

    /**
     * Converts a list of {@link hu.becseimiklos.prt.hw.entity.Parking}s to a list of {@link hu.becseimiklos.prt.hw.vo.ParkingVO} value objects.
     *
     * @param parkings The list to convert.
     * @return The converted {@link hu.becseimiklos.prt.hw.vo.ParkingVO} value object list.
     */
    public static List<ParkingVO> entityListToVo(List<Parking> parkings) {
        List<ParkingVO> parkingVos = new ArrayList<>();
        ParkingVO mappedParking;
        for (Parking parking : parkings) {
            mappedParking = mapper.map(parking, ParkingVO.class);
            parkingVos.add(mappedParking);
        }
        return parkingVos;
    }

    /**
     * Converts a {@link hu.becseimiklos.prt.hw.vo.ParkingVO} to a {@link hu.becseimiklos.prt.hw.entity.Parking} entity.
     *
     * @param parkingVo The {@link hu.becseimiklos.prt.hw.vo.ParkingVO} value object to convert.
     * @return The converted {@link hu.becseimiklos.prt.hw.entity.Parking} entity.
     */
    public static Parking toEntity(ParkingVO parkingVo) {
        if (parkingVo == null) {
            log.warning("ParkingVO is null!");
            return null;
        }
        return mapper.map(parkingVo, Parking.class);
    }
}
