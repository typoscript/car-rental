package carRental.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PostDao {
	private static PostDao instance = new PostDao();
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private PostDao() { }
	
	public static PostDao getInstance() {
		return instance;
	}
}