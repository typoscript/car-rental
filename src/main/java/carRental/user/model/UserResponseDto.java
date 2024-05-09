package carRental.user.model;

import java.sql.Timestamp;

public class UserResponseDto {
	private String id;
	private String name;
	private String address;
	private String phone;
	private boolean isAdmin;

	public UserResponseDto(String id, String password, String name, String address, String phone, boolean isAdmin) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.isAdmin = isAdmin;
	}

	public UserResponseDto(String id, String name, String address, String phone, boolean isAdmin, Timestamp regDate) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.isAdmin = isAdmin;
	}
	
	public UserResponseDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.address = user.getAddress();
		this.phone = user.getPhone();
		this.isAdmin = user.isAdmin();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}