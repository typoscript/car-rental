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
 * Servlet implementation class PostWriteAction
 */
@WebServlet("/PostWriteAction")
public class PostWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostWriteAction() {
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
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isNoticeChecked = request.getParameter("isNotice");

		boolean isNotice = isNoticeChecked == null ? false : true;
		
		HttpSession session = request.getSession();
		UserResponseDto user = (UserResponseDto) session.getAttribute("user");
		
		PostDao postDao = PostDao.getInstance();
		PostRequestDto postDto = new PostRequestDto();
		
		postDto.setUserId(user.getId());
		postDto.setTitle(title);
		postDto.setContent(content);
		postDto.setNotice(isNotice);
		
		postDao.createPost(postDto);
		
		response.sendRedirect("/board");
	}
}
