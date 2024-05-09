package carRental.car.model;

public class Car {
	private String id;
	private String brand;
	private String name;
	private String type;
	private String fuelType;
	private int year;
	private String imgUrl;
	private int fee;
	private int mileage;

	public String getId() {
		return id;
	}
	public String getBrand() {
		return brand;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getFuelType() {
		return fuelType;
	}
	public int getYear() {
		return year;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public int getFee() {
		return fee;
	}
	public int getMileage() {
		return mileage;
	}
}
