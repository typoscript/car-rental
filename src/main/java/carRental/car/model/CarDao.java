package carRental.car.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import carRental.util.DBManager;

public class CarDao {
	private static CarDao instance = new CarDao();
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private CarDao() { }

	public static CarDao getInstance() {
		return instance;
	}

	public List<CarResponseDto> findCarAll() {
	}
}