package carRental.reservation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import carRental.car.model.CarResponseDto;
import carRental.util.DBManager;

public class ReservationDao {
	private static ReservationDao instance = new ReservationDao();
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private ReservationDao() { }

	public static ReservationDao getInstance() {
		return instance;
	}

	public boolean createReservation(ReservationRequestDto reservationDto) {
		String sql = "" +
				"INSERT INTO rental_reservations(user_id, car_id, start_date, end_date, status)" +
				" VALUES(?, ?, ?, ? ,?)";

		boolean isCreated = true;
				
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reservationDto.getUserId());
			pstmt.setInt(2, reservationDto.getCarId());

			pstmt.setDate(3, Date.valueOf(reservationDto.getStartDate()));
			pstmt.setDate(4, Date.valueOf(reservationDto.getEndDate()));
			pstmt.setString(5, reservationDto.getStatus());
			
			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: ReservationDao -> createReservation()");
			isCreated = false;
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isCreated;
	}

	public List<ReservationResponseDto> findReservationAllByUserId(String userId) {
		String sql = "SELECT res.id, car_id, start_date, end_date, cars.* "
			+ "FROM rental_reservations as res "
			+ "JOIN cars ON cars.id = res.car_id "
			+ "WHERE user_id=? AND status=?";

		List<ReservationResponseDto> reservations = new ArrayList<>();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, Reservation.Status.reserved);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt(1);
				LocalDate startDate = LocalDate.parse(rs.getString(2));
				LocalDate endDate = LocalDate.parse(rs.getString(3));
				String status = rs.getString(4);

				int carId = rs.getInt(5);
				String brand = rs.getString(6);
				String name = rs.getString(7);
				String type = rs.getString(8);
				String fuelType = rs.getString(9);
				int year = rs.getInt(10);
				String imgUrl = rs.getString(11);
				int fee = rs.getInt(12);
				int mileage = rs.getInt(13);
				Timestamp creationDate = rs.getTimestamp(14);
				
				CarResponseDto car = new CarResponseDto(carId, brand, name, type, fuelType, year, imgUrl, fee, mileage);
				ReservationResponseDto reservation = new ReservationResponseDto(id, userId, carId, startDate, endDate, status, creationDate);
				
				reservations.add(reservation);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: findReservationsAllByUserId");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return reservations;
	}
}
