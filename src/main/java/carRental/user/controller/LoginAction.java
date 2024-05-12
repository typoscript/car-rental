package carRental.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carRental.user.model.UserDao;
import carRental.user.model.UserResponseDto;

/**
 * Servlet implementation class LoginAction
 */
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
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

		request.setAttribute("hasError", false);
		request.getRequestDispatcher("/loginPage").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();

		boolean isValid = true;
		UserDao userDao = UserDao.getInstance();
		UserResponseDto user = userDao.findUserByIdAndPassword(id, password);
		
		if (id == null || id.isEmpty())
			isValid = false;
		else if (password == null || password.isEmpty())
			isValid = false;	

		if (!isValid || user == null) {
			request.setAttribute("hasError", true);
			request.setAttribute("isValidLogin", false);
			request.getRequestDispatcher("/loginPage").forward(request, response);
			return;
		}

		session.setAttribute("user", user);
		response.sendRedirect("/myPage");
	}
}
