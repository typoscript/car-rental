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
		String sql = "SELECT id, brand, name, type, fuel_type, year, img_url, fee, mileage FROM cars";
		List<CarResponseDto> cars = new ArrayList<>();
		
		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: findCarAll");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return cars;
	}
}