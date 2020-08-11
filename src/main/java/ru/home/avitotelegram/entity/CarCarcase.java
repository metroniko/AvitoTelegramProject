package ru.home.avitotelegram.entity;

import javax.persistence.*;

@Entity(name = "car_carcase")
public class CarCarcase {


    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "car_carcase_id_generator")
    @SequenceGenerator(name = "car_carcase_id_generator", sequenceName = "car_carcase_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "car_name")
    private String carName;

    @Column(name = "car_mark")
    private String carMark;

    @Column(name = "car_price")
    private Long carPrice;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public CarCarcase() {
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarMark() {
        return carMark;
    }

    public void setCarMark(String carMark) {
        this.carMark = carMark;
    }

    public Long getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Long carPrice) {
        this.carPrice = carPrice;
    }
}
