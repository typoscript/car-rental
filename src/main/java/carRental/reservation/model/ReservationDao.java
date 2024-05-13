package carRental.reservation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import carRental.util.DBManager;

import java.sql.Date;


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

	public boolean deleteReservation(ReservationRequestDto reservationDto) {
		boolean isDeleted = true;
		String sql = "DELETE FROM rental_reservations WHERE id=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, reservationDto.getId());

			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: deleteReservation");
			isDeleted = false;
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isDeleted;
	}

	public boolean updateReservation(ReservationRequestDto reservationDto) {
		String sql = 
			"UPDATE rental_reservations " +
			"SET car_id=?, start_date=?, end_date=? " +
			"WHERE id=? AND user_id=?";

		boolean isUpdated = true;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reservationDto.getCarId());
			pstmt.setDate(2, Date.valueOf(reservationDto.getStartDate()));
			pstmt.setDate(3, Date.valueOf(reservationDto.getEndDate()));
			pstmt.setInt(4, reservationDto.getId());
			pstmt.setString(5, reservationDto.getUserId());

			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: ReservationDao -> updateReservation()");
			isUpdated = false;
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isUpdated;
	}

	public boolean updateReservationStatus(ReservationRequestDto reservationDto) {
		String sql = 
			"UPDATE rental_reservations " +
			"SET status=? " +
			"WHERE id=? and user_id=?";

		boolean isUpdated = true;
				
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reservationDto.getStatus());
			pstmt.setInt(2, reservationDto.getId());
			pstmt.setString(3, reservationDto.getUserId());

			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: ReservationDao -> updateReservationStatus()");
			isUpdated = false;
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isUpdated;
	}

	public List<ReservationResponseDto> findReservationAllByUserId(String userId) {
		String sql = "SELECT id, car_id, start_date, end_date, status, creation_date "
			+ "FROM rental_reservations "
			+ "WHERE user_id=?";

		List<ReservationResponseDto> reservations = new ArrayList<>();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
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

	public List<ReservationResponseDto> findReservationDateRangesById(ReservationRequestDto reservationDto) {
		String sql = "SELECT start_date, end_date" +
		"FROM rental_reservations" +
		"WHERE car_id=?";
		
		List<ReservationResponseDto> dateRanges = null;
	
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reservationDto.getId());
			
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: findReservationDateRangesById");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return dateRanges;
	}

	public boolean isValidReservationDateRange(ReservationRequestDto reservationDto) {
		String sql = "SELECT COUNT(*) " +
			"FROM rental_reservations " +
			"WHERE (? BETWEEN start_date AND end_date " +
			"OR ? BETWEEN start_date AND end_date) " +
			"AND status=?";

		int reservationCountInDateRange = 0;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(reservationDto.getStartDate()));
			pstmt.setDate(2, Date.valueOf(reservationDto.getEndDate()));
			pstmt.setString(3, Reservation.Status.reserved);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				reservationCountInDateRange = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: findReservationsAllByUserId");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return reservationCountInDateRange == 0;
	}

	public boolean isValidMyReservationDateRange(ReservationRequestDto reservationDto) {
		String sql = "SELECT COUNT(*) " +
			"FROM rental_reservations " +
			"WHERE (? BETWEEN start_date AND end_date " +
			"OR ? BETWEEN start_date AND end_date) " +
			"AND status=? AND user_id != ?";

		int reservationCountInDateRange = 0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(reservationDto.getStartDate()));
			pstmt.setDate(2, Date.valueOf(reservationDto.getEndDate()));
			pstmt.setString(3, Reservation.Status.reserved);
			pstmt.setString(4, reservationDto.getUserId());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				reservationCountInDateRange = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: findReservationsAllByUserId");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return reservationCountInDateRange == 0;
	}
}
