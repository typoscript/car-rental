package carRental.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carRental.user.model.UserDao;
import carRental.user.model.UserRequestDto;
import carRental.user.model.UserResponseDto;

/**
 * Servlet implementation class UserUpdateAction
 */
@WebServlet("/UserUpdateAction")
public class UserUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String passwordNew = request.getParameter("passwordNew");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		UserDao userDao = UserDao.getInstance();
		HttpSession session = request.getSession();
		UserResponseDto user = (UserResponseDto)session.getAttribute("user");
		UserRequestDto userDto = new UserRequestDto();

		if (userDao.findUserByIdAndPassword(user.getId(), password) == null) {
			response.sendRedirect("/userUpdate");
			return;
		}
		
		userDto.setPassword(password);
		userDao.updateUserPassword(userDto, passwordNew);
		
		userDto.setAddress(address);
		userDao.updateUserAddress(userDto);

		userDto.setPhone(phone);
		userDao.updateUserPhone(userDto);

		response.sendRedirect("/myPage");
	}
}
