package carRental.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carRental.board.model.PostDao;
import carRental.board.model.PostRequestDto;
import carRental.user.model.UserResponseDto;

/**
 * Servlet implementation class PostDeleteAction
 */
public class PostDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDeleteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 

		HttpSession session = request.getSession();
		UserResponseDto user = (UserResponseDto) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect("/login");
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));
		PostDao postDao = PostDao.getInstance();
		PostRequestDto postDto = new PostRequestDto();

		postDto.setId(id);
		postDto.setUserId(user.getId());
		
		postDao.deletePost(postDto);

		response.sendRedirect("/board");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/board");
	}

}
