package hu.becseimiklos.prt.hw.mapper;


import hu.becseimiklos.prt.hw.data.entity.Car;
import hu.becseimiklos.prt.hw.vo.CarVo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public static Car toDto(CarVo carVo) {
        if (carVo == null) {
            logger.warn("CarVo is null!");
            return null;
        }
        return mapper.map(carVo, Car.class);
    }
}
