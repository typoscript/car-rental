package carRental.reservation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carRental.car.model.CarDao;
import carRental.car.model.CarResponseDto;
import carRental.reservation.model.ReservationDao;
import carRental.reservation.model.ReservationResponseDto;
import carRental.user.model.UserResponseDto;

/**
 * Servlet implementation class ReservationViewAction
 */
public class ReservationViewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationViewAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserResponseDto user = (UserResponseDto)session.getAttribute("user");
		
		if (user == null) {
			response.sendRedirect("/signUp");
			return;
		}

		ReservationDao reservationDao = ReservationDao.getInstance();
		CarDao carDao = CarDao.getInstance();

		List<ReservationResponseDto> reservations = reservationDao.findReservationAllByUserId(user.getId());
		List<CarResponseDto> cars = carDao.findCarAllByUserId(user.getId());
		
		request.setAttribute("reservations", reservations);
		request.setAttribute("cars", cars);
		
		request.getRequestDispatcher("/myReservationPage").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
