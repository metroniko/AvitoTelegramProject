package ru.home.avitotelegram.itemInformation;

import org.springframework.stereotype.Component;
import ru.home.avitotelegram.itemInformation.DTO.CarDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DTOCollector {
    private Map<Long, Integer> carUsersMarkMap = new HashMap<>();
    private List<CarDTO> carDTOSCollection = new ArrayList<>();

    public void setUserCar(Long userId, Integer carId) {
        carUsersMarkMap.put(userId, carId);
    }
    public void setCarDTOElement(CarDTO carDTO) {
        carDTOSCollection.add(carDTO);
    }
    public CarDTO getCarDTOElement(Long userId) {
        return getCarDTO(carUsersMarkMap.get(userId));
    }
    public void removeCarDTOElement(CarDTO carDTO) {
        carDTOSCollection.remove(carDTO);
    }

    private CarDTO getCarDTO(Integer carId) {
        return carDTOSCollection.stream()
                .filter(el -> el.getCarDTOId() == carId)
                .collect(Collectors.toList()).get(0);

    }
}
