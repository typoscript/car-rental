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

	public PostResponseDto findPostById(int id) {
		String sql = "SELECT user_id, title, content, is_notice, creation_date, modification_date " +
				"FROM board WHERE id=?";
		PostResponseDto post = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String userId = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				boolean isNotice = rs.getBoolean(4);
				Timestamp creationDate = rs.getTimestamp(5);
				Timestamp modificationDate = rs.getTimestamp(6);
				
				post = new PostResponseDto(id, userId, title, content, isNotice, creationDate, modificationDate);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: findPostById");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return post;
	}

	public List<PostResponseDto> findPostAll() {
		String sql = "SELECT id, user_id, title, content, is_notice, creation_date, modification_date FROM board";
		List<PostResponseDto> posts = new ArrayList<>();

		try {
			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String userId = rs.getString(2);
				String title = rs.getString(3);
				String content = rs.getString(4);
				boolean isNotice = rs.getBoolean(5);
				Timestamp creationDate = rs.getTimestamp(6);
				Timestamp modificationDate = rs.getTimestamp(7);
				
				PostResponseDto post = new PostResponseDto(id, userId, title, content, isNotice, creationDate, modificationDate);
				
				posts.add(post);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: findPostAll");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return posts;
	}

	public boolean deletePost(PostRequestDto postDto) {
		boolean isDeleted = true;

		String sql = "DELETE FROM board WHERE id=? AND user_id=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, postDto.getId());
			pstmt.setString(2, postDto.getUserId());

			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: deletePost");
			isDeleted = false;
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isDeleted;
	}

	public boolean updatePost(PostRequestDto postDto) {
		boolean isUpdated = true;

		String sql = "UPDATE board " + 
			"SET title=? AND content=?, is_notice=? " +
			"WHERE id=? AND user_id=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, postDto.getTitle());
			pstmt.setString(2, postDto.getContent());
			pstmt.setBoolean(3, postDto.isNotice());

			pstmt.setInt(4, postDto.getId());
			pstmt.setString(5, postDto.getUserId());

			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: updatePost");
			isUpdated = false;
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isUpdated;
	}
}