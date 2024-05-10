package carRental.reservation.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carRental.car.model.CarResponseDto;
import carRental.reservation.model.ReservationDao;
import carRental.reservation.model.ReservationRequestDto;
import carRental.user.model.UserResponseDto;

/**
 * Servlet implementation class ReservationCreateAction
 */
public class ReservationCreateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationCreateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String carId = request.getParameter("carId");
		String brand = request.getParameter("brand");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String fuelType = request.getParameter("fuelType");
		int year = Integer.parseInt(request.getParameter("year"));
		int fee = Integer.parseInt(request.getParameter("fee"));
		int mileage = Integer.parseInt(request.getParameter("mileage"));
		String imgUrl = request.getParameter("imgUrl");

		CarResponseDto car = new CarResponseDto(carId, brand, name, type, fuelType, year, imgUrl, fee, mileage);

		request.setAttribute("car", car);

		request.getRequestDispatcher("/reservationCreatePage").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserResponseDto user = (UserResponseDto)session.getAttribute("user");

		int carId = Integer.parseInt(request.getParameter("carId"));
		LocalDate startDate = LocalDate.parse(request.getParameter("rentalStartDate"));
		LocalDate endDate = LocalDate.parse(request.getParameter("rentalEndDate"));
		
		ReservationRequestDto reservationDto = new ReservationRequestDto(user.getId(), carId, startDate, endDate);
		ReservationDao reservationDao = ReservationDao .getInstance();
				
		if (reservationDao.createReservation(reservationDto)) {
			response.sendRedirect("/myPage");
			return;
		}

		response.sendRedirect("/");
	}

}
