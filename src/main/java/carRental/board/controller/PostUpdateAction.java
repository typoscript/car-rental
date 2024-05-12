package carRental.board.controller;

import java.io.IOException;
import java.security.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carRental.board.model.PostDao;
import carRental.board.model.PostRequestDto;
import carRental.board.model.PostResponseDto;
import carRental.user.model.UserResponseDto;

/**
 * Servlet implementation class PostUpdateAction
 */
@WebServlet("/PostUpdateAction")
public class PostUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostUpdateAction() {
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

		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		boolean isNotice = Boolean.parseBoolean(request.getParameter("isNotice"));
		
		PostResponseDto post = new PostResponseDto(id, user.getId(), title, content, isNotice);
		
		request.setAttribute("post", post);

		request.getRequestDispatcher("/postUpdatePage").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 

		HttpSession session = request.getSession();
		UserResponseDto user = (UserResponseDto) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect("/login");
			return;
		}
		
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isNoticeChecked = request.getParameter("isNotice");

		boolean isNotice = isNoticeChecked == null ? false : true;
		boolean isValid = true;

		if (title == null || title.isEmpty()) {
			isValid = false;
			request.setAttribute("isInvalidTitle", true);
		}

		if (content == null || content.isEmpty()) {
			isValid = false;
			request.setAttribute("isInvalidContent", true);
		}
		
		if (!isValid) {
			request.getRequestDispatcher("/postUpdatePage").forward(request, response);
			return;
		}
		
		PostDao postDao = PostDao.getInstance();
		PostRequestDto postDto = new PostRequestDto();
		
		postDto.setId(id);
		postDto.setUserId(user.getId());
		postDto.setTitle(title);
		postDto.setContent(content);
		postDto.setNotice(isNotice);
		
		postDao.updatePost(postDto);
		
		response.sendRedirect("/postView?id=" + id);
	}
}