package ru.home.avitotelegram.itemInformation.DTO;

import java.util.ArrayList;
import java.util.List;

public class CarDTO {

    static int carIterator;
    private List<Long> userSubscribes = new ArrayList<>();
    private String carName;
    private String carMark;
    private String carPrice;

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public int getCarDTOId() {
        return carDTOId;
    }

    private int carDTOId;

    public CarDTO() {
        carIterator++;
        carDTOId = CarDTO.carIterator;
    }

    public List<Long> getUserSubscribes() {
        return userSubscribes;
    }

    public void setUserSubscribes(List<Long> userSubscribes) {
        this.userSubscribes = userSubscribes;
    }

    public String getCarMark() {
        return carMark;
    }

    public void setCarMark(String carMark) {
        this.carMark = carMark;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
}