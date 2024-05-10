package carRental.reservation.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carRental.reservation.model.Reservation;
import carRental.reservation.model.ReservationDao;
import carRental.reservation.model.ReservationRequestDto;
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
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);

		request.getRequestDispatcher("/reservationUpdatePage").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserResponseDto user = (UserResponseDto)session.getAttribute("user");
		
		int id = Integer.parseInt(request.getParameter("id"));
		int carId = Integer.parseInt(request.getParameter("carId"));
		LocalDate startDate = LocalDate.parse(request.getParameter("rentalStartDate"));
		LocalDate endDate = LocalDate.parse(request.getParameter("rentalEndDate"));
	}

}
