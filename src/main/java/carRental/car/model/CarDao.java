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
			
			while (rs.next()) {
				String id = rs.getString(1);
				String brand = rs.getString(2);
				String name = rs.getString(3);
				String type = rs.getString(4);
				String fuelType = rs.getString(5);
				int year = rs.getInt(6);
				String imgUrl = rs.getString(7);
				int fee = rs.getInt(8);
				int mileage = rs.getInt(9);
				
				CarResponseDto car = new CarResponseDto(id, brand, name, type, fuelType, year, imgUrl, fee, mileage);
				
				cars.add(car);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: findCarAll");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return cars;
	}
}