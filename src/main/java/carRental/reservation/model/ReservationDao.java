package carRental.reservation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

	public ReservationResponseDto createReservation(ReservationRequestDto reservationDto) {
		String sql = "" +
				"INSERT INTO rental_reservations(user_id, car_id, start_date, end_date, status)" +
				" VALUES(?, ?, ?, ? ,?)";

		ReservationResponseDto reservation = null;
				
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: ReservationDao -> createReservation()");
		} finally {
			DBManager.close(conn, pstmt);
		}

		return reservation;
	}
}
