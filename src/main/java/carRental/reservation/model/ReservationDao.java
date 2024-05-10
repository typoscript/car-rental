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
				"INSERT INTO users(user_id, car_id, start_date, end_date, status)" +
				" VALUES(?, ?, ?, ? ,?)";
	}
}
