package ru.home.avitotelegram.itemInformation.fullItemInformation;

import org.springframework.context.annotation.Bean;
import ru.home.avitotelegram.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "car_items")
public class CarItem implements AvitoItem {

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "car_id_generator")
    @SequenceGenerator(name = "car_id_generator", sequenceName = "car_id_seq", allocationSize = 1)
    @Id
    @Column(name = "car_id")
    private Long id;

    @Column(name = "body_type")
    private String bodyType;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "fuel_type")
    private String fuelType;

    public String getBodyType() {
        return bodyType;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getImage() {
        return image;
    }

    public String getUrlCar() {
        return urlCar;
    }

    @Column(name = "image")
    private String image;

    @Column(name = "car_name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "url_car")
    private String urlCar;

    //добавить таблицу с пользователями
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarItem() {
    }

    public CarItem(String bodyType, String brand,
                   String color, String fuelType,
                   String image, String name,
                   Integer price, String urlCar) {
        this.bodyType = bodyType;
        this.brand = brand;
        this.color = color;
        this.fuelType = fuelType;
        this.image = image;
        this.name = name;
        this.price = price;
        this.urlCar = urlCar;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setUrlCar(String urlCar) {
        this.urlCar = urlCar;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getURL() {
        return urlCar;
    }

    @Override
    public String toString() {
        return "CarItem{" +
                "bodyType='" + bodyType + '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", urlCar='" + urlCar + '\'' +
                '}';
    }


}
