package hu.becseimiklos.prt.hw.mapper;



import hu.becseimiklos.prt.hw.entity.Car;
import hu.becseimiklos.prt.hw.vo.CarVo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CarMapper {

    private static Logger logger = LoggerFactory.getLogger(CarMapper.class);

    private static ModelMapper mapper = new ModelMapper();

    public static CarVo toVo(Car car) {
        if (car == null) {
            logger.warn("CarEntity is null!");
            return null;
        }
        return mapper.map(car, CarVo.class);
    }

    public static List<CarVo> toVo(List<Car> cars) {
        List<CarVo> carVos = new ArrayList<>();
        CarVo mappedCar;
        for (Car car : cars) {
            mappedCar = mapper.map(car, CarVo.class);
            carVos.add(mappedCar);
        }
        return carVos;
    }

    public static Car toDto(CarVo carVo) {
        if (carVo == null) {
            logger.warn("CarVo is null!");
            return null;
        }
        return mapper.map(carVo, Car.class);
    }
}
