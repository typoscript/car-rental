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
		String sql = "SELECT id, car_id, start_date, end_date, status, creation_date "
			+ "FROM rental_reservations "
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
				int carId = rs.getInt(2);
				LocalDate startDate = LocalDate.parse(rs.getString(3));
				LocalDate endDate = LocalDate.parse(rs.getString(4));
				String status = rs.getString(5);
				Timestamp creationDate = rs.getTimestamp(6);
				
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
