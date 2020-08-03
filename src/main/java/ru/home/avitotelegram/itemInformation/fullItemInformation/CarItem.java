package ru.home.avitotelegram.itemInformation.fullItemInformation;

public class CarItem implements AvitoItem {


    private String bodyType;
    private String brand;
    private String color;
    private String fuelType;
    private String image;
    private String name;
    private Integer price;
    private String urlCar;

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
