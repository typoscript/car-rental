package carRental.car.model;

public class CarResponseDto {
	private String id;
	private String brand;
	private String name;
	private String type;
	private String fuelType;
	private int year;
	private String imgUrl;
	private int fee;
	private int mileage;
	
	public CarResponseDto(String id, String brand, String name, String type, String fuelType, int year, String imgUrl, int fee, int mileage) {
		this.id = id;
		this.brand = brand;
		this.name = name;
		this.type = type;
		this.fuelType = fuelType;
		this.year = year;
		this.imgUrl = imgUrl;
		this.fee = fee;
		this.mileage = mileage;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	
}
