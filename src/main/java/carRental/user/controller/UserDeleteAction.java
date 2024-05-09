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
import carRental.util.PasswordCrypto;

/**
 * Servlet implementation class UserDeleteAction
 */
@WebServlet("/UserDeleteAction")
public class UserDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = UserDao.getInstance();
		HttpSession session = request.getSession();
		UserResponseDto user = (UserResponseDto) session.getAttribute("user");

		String id = user.getId();
		String password = request.getParameter("password");

		UserRequestDto userDto = new UserRequestDto();

		userDto.setId(id);
		userDto.setPassword(PasswordCrypto.encrypt(password));

		boolean result = userDao.deleteUser(userDto);

		if (result) {
			response.sendRedirect("/");
			return;
		}

		response.sendRedirect("/myPage");
	}

}
