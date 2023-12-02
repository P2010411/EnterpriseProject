package com.mycompany.carstore.Beans;
import java.math.BigDecimal;
public class ProductBean {
    private int id;
    private String modelName;
    private int year;
    private String optionsPackage;
    private String color;
    private double motorSize;
    private BigDecimal price;
    private double acceleration;
    private String fuelType;
    private String image;
    private String branch;
    public ProductBean() {
    }
    public ProductBean(int id, String modelName, int year, String optionsPackage, String color, double motorSize, BigDecimal price, double acceleration, String fuelType, String image, String branch) {
        this.id = id;
        this.modelName = modelName;
        this.year = year;
        this.optionsPackage = optionsPackage;
        this.color = color;
        this.motorSize = motorSize;
        this.price = price;
        this.acceleration = acceleration;
        this.fuelType = fuelType;
        this.image = image;
        this.branch = branch;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getOptionsPackage() { return optionsPackage; }
    public void setOptionsPackage(String optionsPackage) { this.optionsPackage = optionsPackage; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public double getMotorSize() { return motorSize; }
    public void setMotorSize(double motorSize) { this.motorSize = motorSize; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public double getAcceleration() { return acceleration; }
    public void setAcceleration(double acceleration) { this.acceleration = acceleration; }
    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }
    @Override
    public String toString() {
        return "ProductBean{" + "id=" + id + ", modelName=" + modelName + ", year=" + year + ", optionsPackage=" + optionsPackage + ", color=" + color + ", motorSize=" + motorSize + ", price=" + price + ", acceleration=" + acceleration + ", fuelType=" + fuelType + ", image=" + image + ", branch=" + branch + '}';
    }  
}
