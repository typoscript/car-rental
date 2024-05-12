package carRental.reservation.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carRental.car.model.CarDao;
import carRental.car.model.CarResponseDto;
import carRental.reservation.model.Reservation;
import carRental.reservation.model.ReservationDao;
import carRental.reservation.model.ReservationRequestDto;
import carRental.reservation.model.ReservationResponseDto;
import carRental.user.model.UserResponseDto;

/**
 * Servlet implementation class ReservationUpdateAction
 */
public class ReservationUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserResponseDto user = (UserResponseDto)session.getAttribute("user");

		int id = Integer.parseInt(request.getParameter("id"));
		LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
		LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
		
		int carId = Integer.parseInt(request.getParameter("carId"));
		String brand = request.getParameter("brand");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String fuelType = request.getParameter("fuelType");
		int year = Integer.parseInt(request.getParameter("year"));
		int fee = Integer.parseInt(request.getParameter("fee"));
		int mileage = Integer.parseInt(request.getParameter("mileage"));
		String imgUrl = request.getParameter("imgUrl");

		ReservationResponseDto reservation = new ReservationResponseDto(id, user.getId(), carId, startDate, endDate);
		CarResponseDto car = new CarResponseDto(carId, brand, name, type, fuelType, year, imgUrl, fee, mileage);

		request.setAttribute("reservation", reservation);
		request.setAttribute("car", car);

		request.getRequestDispatcher("/reservationUpdatePage").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		HttpSession session = request.getSession();
		UserResponseDto user = (UserResponseDto)session.getAttribute("user");

		if (user == null) {
			response.sendRedirect("/login");
			return;
		}
		
		int id = Integer.parseInt(request.getParameter("id"));
		String status = request.getParameter("status");
		String carIdStr = request.getParameter("carId");
		String price = request.getParameter("price");
		String payAmount = request.getParameter("payAmount");
		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");

		boolean isValid = true;
		
		if (carIdStr == null || carIdStr.isEmpty()) {
			isValid = false;
			request.setAttribute("isInvalidCarId", true);
		}

		if (price == null || price.isEmpty()) {
			isValid = false;
			request.setAttribute("isInvalidPayAmount", true);
		}

		if (payAmount == null || payAmount.isEmpty()) {
			isValid = false;
			request.setAttribute("isInvalidPayAmount", true);
		}

		if (startDateStr == null || startDateStr.isEmpty()) {
			isValid = false;
			request.setAttribute("isInvalidStartDate", true);
		}
		
		if (endDateStr == null || endDateStr.isEmpty()) {
			isValid = false;
			request.setAttribute("isInvalidEndDate", true);
		}
		
		if (startDateStr.compareTo(endDateStr) > 0) {
			isValid = false;
			request.setAttribute("isInvalidReservationDateRange", true);
		}

		int carId = Integer.parseInt(carIdStr);

		if (!isValid) {
			CarDao carDao = CarDao.getInstance();
			CarResponseDto car = carDao.findCarById(carId);

			request.setAttribute("car", car);
			request.getRequestDispatcher("/reservationCreatePage").forward(request, response);
			return;
		}
		
		if (status != null) {
			handleReservationStatusChange(response, id, user.getId(), status);
			return;
		}
		
		LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
		LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));

		if (Integer.parseInt(payAmount) < Integer.parseInt(price)) {
			isValid = false;
			request.setAttribute("isInvalidPayAmount", true);
		}

		ReservationRequestDto reservationDto = new ReservationRequestDto(id, user.getId(), carId, startDate, endDate, Reservation.Status.reserved);
		ReservationDao reservationDao = ReservationDao.getInstance();
				
		if (reservationDao.updateReservation(reservationDto)) {
			response.sendRedirect("/myPage");
			return;
		}

		response.sendRedirect("/");
	}

	private void handleReservationStatusChange(HttpServletResponse response, int id, String userId, String status) throws ServletException, IOException {
		ReservationRequestDto reservationDto = new ReservationRequestDto(id, userId, status);
		ReservationDao reservationDao = ReservationDao.getInstance();
				
		if (reservationDao.updateReservationStatus(reservationDto)) {
			response.sendRedirect("/myPage");
			return;
		}

		response.sendRedirect("/");
	}
}
