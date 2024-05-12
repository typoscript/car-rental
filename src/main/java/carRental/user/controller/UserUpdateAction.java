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
		request.getRequestDispatcher("/userUpdatePage").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 

		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String newPassword = request.getParameter("newPassword");
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
		
		userDto.setId(user.getId());

		userDto.setPassword(password);
		userDao.updateUserPassword(userDto, newPassword);
		
		userDto.setAddress(address);
		userDao.updateUserAddress(userDto);

		userDto.setPhone(phone);
		userDao.updateUserPhone(userDto);

		response.sendRedirect("/myPage");
	}
}
