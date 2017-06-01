package hu.becseimiklos.prt.hw.mapper;


import hu.becseimiklos.prt.hw.entity.Car;
import hu.becseimiklos.prt.hw.vo.CarVo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CarMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static CarVo toVo(Car car) {
        if (car == null) {
            log.warn("CarEntity is null!");
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

    public static Car toEntity(CarVo carVo) {
        if (carVo == null) {
            log.warn("CarVo is null!");
            return null;
        }
        return mapper.map(carVo, Car.class);
    }
}
