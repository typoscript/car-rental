package carRental.reservation.model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ReservationRequestDto {
	private int id;
	private String userId;
	private String carId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
	private Timestamp creationDate;

	public ReservationRequestDto(int id, String userId, String carId, LocalDate startDate, LocalDate endDate, String status, Timestamp creationDate) {
		this.id = id;
		this.userId = userId;
		this.carId = carId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.creationDate = creationDate;
	}

	public ReservationRequestDto(String userId, String carId, LocalDate startDate, LocalDate endDate) {
		this.userId = userId;
		this.carId = carId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
}
