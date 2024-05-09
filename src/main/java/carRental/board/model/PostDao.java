package carRental.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, postDto.getUserId());
			pstmt.setString(2, postDto.getTitle());
			pstmt.setString(3, postDto.getContent());
			pstmt.setBoolean(4, postDto.isNotice());

			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: createPost");
			isCreated = false;
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return isCreated;
	}

	public List<PostResponseDto> findPostAll() {
		String sql = "SELECT user_id, title, content, is_notice, creation_date, modification_date FROM board";
		List<PostResponseDto> cars = new ArrayList<>();

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: findCarAll");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return cars;
	}
}