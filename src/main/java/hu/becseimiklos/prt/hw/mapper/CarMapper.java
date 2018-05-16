package hu.becseimiklos.prt.hw.mapper;


import hu.becseimiklos.prt.hw.entity.Car;
import hu.becseimiklos.prt.hw.vo.CarVO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Utility class to convert {@link hu.becseimiklos.prt.hw.vo.CarVO} to {@link hu.becseimiklos.prt.hw.entity.Car} and backwards.
 */
public class CarMapper {

    private static final Logger log = Logger.getLogger(CarMapper.class.getName());
    private static ModelMapper mapper = new ModelMapper();

    private CarMapper() {
    }

    /**
     * Converts a {@link hu.becseimiklos.prt.hw.entity.Car} to a {@link hu.becseimiklos.prt.hw.vo.CarVO} value object.
     *
     * @param car The {@link hu.becseimiklos.prt.hw.entity.Car} entity to convert.
     * @return The converted {@link hu.becseimiklos.prt.hw.vo.CarVO} value object.
     */
    public static CarVO toVO(Car car) {
        if (car == null) {
            log.warning("CarEntity is null!");
            return null;
        }
        return mapper.map(car, CarVO.class);
    }

    /**
     * Converts a list of {@link hu.becseimiklos.prt.hw.entity.Car}s to a list of {@link hu.becseimiklos.prt.hw.vo.CarVO} value objects.
     *
     * @param cars The list to convert.
     * @return The converted {@link hu.becseimiklos.prt.hw.vo.CarVO} value object list.
     */
    public static List<CarVO> toVO(List<Car> cars) {
        return cars.stream().map(car -> mapper.map(car, CarVO.class)).collect(Collectors.toList());
    }

    /**
     * Converts a {@link hu.becseimiklos.prt.hw.vo.CarVO} to a {@link hu.becseimiklos.prt.hw.entity.Car} entity.
     *
     * @param carVO The {@link hu.becseimiklos.prt.hw.vo.CarVO} value object to convert.
     * @return The converted {@link hu.becseimiklos.prt.hw.entity.Car} entity.
     */
    public static Car toEntity(CarVO carVO) {
        if (carVO == null) {
            log.warning("CarVO is null!");
            return null;
        }
        return mapper.map(carVO, Car.class);
    }
}
