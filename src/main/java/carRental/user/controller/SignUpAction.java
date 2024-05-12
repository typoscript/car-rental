package carRental.user.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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
 * Servlet implementation class SignUpAction
 */
public class SignUpAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserResponseDto user = (UserResponseDto)session.getAttribute("user");
		
		if (user != null) {
			response.sendRedirect("/myPage");
			return;
		}

		request.getRequestDispatcher("/signUpPage").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 

		HttpSession session = request.getSession();
		
		if ((UserResponseDto)session.getAttribute("user") != null) {
			response.sendRedirect("/login");
			return;
		}

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String isAdminStr = request.getParameter("isAdmin");
		
		boolean isAdmin = isAdminStr == null ? false : true;
		Timestamp regDate = new Timestamp(new Date().getTime());

		boolean isValid = true;
		
		if (id == null || id.isEmpty())
			isValid = false;
		else if (password == null || password.isEmpty())
			isValid = false;
		else if (name == null || name.isEmpty())
			isValid = false;
		else if (address == null || address.isEmpty())
			isValid = false;
		else if (phone == null || phone.isEmpty())
			isValid = false;
		
		if (!isValid) {
			response.sendRedirect("/signUp");
			return;
		}
		
		UserRequestDto userDto = new UserRequestDto(id, password, name, address, phone, isAdmin, regDate);
		UserDao userDao = UserDao.getInstance();
		
		if (userDao.isDuplId(id)) {
			request.setAttribute("isDuplId", true);
			request.getRequestDispatcher("/signUpPage").forward(request, response);
			return;
		}

		if (userDao.isDuplPhone(phone)) {
			request.setAttribute("isDuplPhone", true);
			request.getRequestDispatcher("/signUpPage").forward(request, response);
			return;
		}

		UserResponseDto user = userDao.createUser(userDto);
		
		if (user == null) {
			response.sendRedirect("/signUp");
			return;
		}

		response.sendRedirect("/login");
	}
}
