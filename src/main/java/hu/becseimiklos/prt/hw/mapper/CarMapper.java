package hu.becseimiklos.prt.hw.mapper;


import hu.becseimiklos.prt.hw.entity.Car;
import hu.becseimiklos.prt.hw.vo.CarVO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CarMapper {

    private static final Logger log = Logger.getLogger(CarMapper.class.getName());
    private static ModelMapper mapper = new ModelMapper();

    public static CarVO toVO(Car car) {
        if (car == null) {
            log.warning("CarEntity is null!");
            return null;
        }
        return mapper.map(car, CarVO.class);
    }

    public static List<CarVO> toVO(List<Car> cars) {
        return cars.stream().map(car -> mapper.map(car, CarVO.class)).collect(Collectors.toList());
    }

    public static Car toEntity(CarVO carVO) {
        if (carVO == null) {
            log.warning("CarVO is null!");
            return null;
        }
        return mapper.map(carVO, Car.class);
    }
}
