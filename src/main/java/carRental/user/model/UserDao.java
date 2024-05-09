package carRental.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import carRental.util.DBManager;
import carRental.util.PasswordCrypto;

public class UserDao {
	private static UserDao instance = new UserDao();
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private UserDao() { }
	
	public static UserDao getInstance() {
		return instance;
	}
	
	public UserResponseDto findUserByIdAndPassword(String id, String password) {
		String sql = "SELECT password, name, address, phone, is_admin, reg_date"
				+ " FROM users WHERE id=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (!rs.next())
				return null;

			String encryptedPassword = rs.getString(1);
			String name = rs.getString(2);
			String address = rs.getString(3);
			String phone = rs.getString(4);
			boolean isAdmin = rs.getBoolean(5);
			Timestamp regDate = rs.getTimestamp(6);

			if (!PasswordCrypto.decrypt(password, encryptedPassword))
				return null;
			
			return new UserResponseDto(id, name, address, phone, isAdmin, regDate);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("UserDao -> findUserByIdAndPassword()");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return null;
	}
	
	public UserResponseDto createUser(UserRequestDto userDto) {
		String sql = "" +
				"INSERT INTO users(id, password, name, address, phone, is_admin, reg_date)" +
				" VALUES(?, ?, ?, ?, ?, ?, ?)";
				
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, PasswordCrypto.encrypt(userDto.getPassword()));
			pstmt.setString(3, userDto.getName());
			pstmt.setString(4, userDto.getAddress());
			pstmt.setString(5, userDto.getPhone());
			pstmt.setBoolean(6, userDto.isAdmin());
			pstmt.setTimestamp(7, userDto.getRegDate());
			
			pstmt.execute();

			return findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: UserDao -> createUser()");
		} finally {
			DBManager.close(conn, pstmt);
		}

		return null;
	}
}