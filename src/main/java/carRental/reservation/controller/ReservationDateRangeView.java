package carRental.reservation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import carRental.reservation.model.ReservationDao;
import carRental.reservation.model.ReservationRequestDto;
import carRental.reservation.model.ReservationResponseDto;
import carRental.user.model.UserResponseDto;

/**
 * Servlet implementation class ReservationDateRangeView
 */
@WebServlet("/ReservationDateRangeView")
public class ReservationDateRangeView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationDateRangeView() {
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
			response.sendRedirect("/login");
			return;
		}

		String carId = request.getParameter("carId");
		ReservationRequestDto reservationDto = new ReservationRequestDto();
		reservationDto.setCarId(Integer.parseInt(carId));
		reservationDto.setUserId(user.getId());

		List<ReservationResponseDto> dateRanges = ReservationDao.getInstance().findReservationDateRangesByIdAndUserId(reservationDto);
		
		JSONArray dateRangeArr = new JSONArray();
		
		for (ReservationResponseDto dateRange : dateRanges) {
			JSONObject dateObject = new JSONObject();
			dateObject.put("startDate", dateRange.getStartDate());
			dateObject.put("endDate", dateRange.getEndDate());
			
			dateRangeArr.put(dateObject);
		}

		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(dateRangeArr.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
