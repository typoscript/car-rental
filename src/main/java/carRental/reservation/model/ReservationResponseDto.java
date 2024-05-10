package carRental.reservation.model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ReservationResponseDto {
	private int id;
	private int userId;
	private int carId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
	private Timestamp creationDate;
}