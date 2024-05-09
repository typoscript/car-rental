package carRental.user.model;

import java.sql.Timestamp;

public class User {
	private String id;
	private String password;
	private String name;
	private String address;
	private String phone;
	private boolean isAdmin;
	private Timestamp regDate;

	public User(String id, String password, String name, String address, String phone, boolean isAdmin,
			Timestamp regDate) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.isAdmin = isAdmin;
		this.regDate = regDate;
	}

	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
}
