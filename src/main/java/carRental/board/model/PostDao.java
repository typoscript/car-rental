package carRental.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import carRental.util.DBManager;

public class PostDao {
	private static PostDao instance = new PostDao();
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private PostDao() { }
	
	public static PostDao getInstance() {
		return instance;
	}

	public boolean createPost(PostRequestDto postDto) {
		String sql = "INSERT INTO board(user_id, title, content, is_notice)"
				+ " VALUES (?, ?, ?, ?)";
		
		boolean isCreated = true;
		
		try {
			conn = DBManager.getConnection();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: createPost");
			isCreated = false;
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return isCreated;
	}
}