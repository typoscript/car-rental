package carRental.reservation.model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ReservationRequestDto {
	private int id;
	private int userId;
	private int carId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
	private Timestamp creationDate;

	public ReservationRequestDto(int id, int userId, int carId, LocalDate startDate, LocalDate endDate, String status, Timestamp creationDate) {
		this.id = id;
		this.userId = userId;
		this.carId = carId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.creationDate = creationDate;
	}
	
}
