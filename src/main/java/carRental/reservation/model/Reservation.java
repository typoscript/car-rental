package carRental.reservation.model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Reservation {
	private int id;
	private String userId;
	private String carId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
	private Timestamp creationDate;

	public Reservation(int id, String userId, String carId, LocalDate startDate, LocalDate endDate, String status, Timestamp creationDate) {
		this.id = id;
		this.userId = userId;
		this.carId = carId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.creationDate = creationDate;
	}

	public int getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getCarId() {
		return carId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public String getStatus() {
		return status;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}
}
