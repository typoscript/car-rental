package carRental.reservation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.sql.Date;

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
			pstmt.setString(2, reservationDto.getCarId());

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
}
