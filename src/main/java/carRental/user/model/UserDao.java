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
		UserResponseDto user = null;

		String sql = "SELECT password, name, address, phone, is_admin, reg_date"
				+ " FROM users WHERE id=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String encryptedPassword = rs.getString(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String phone = rs.getString(4);
				boolean isAdmin = rs.getBoolean(5);
				Timestamp regDate = rs.getTimestamp(6);

				if (PasswordCrypto.decrypt(password, encryptedPassword))
					user = new UserResponseDto(id, name, address, phone, isAdmin, regDate);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("UserDao -> findUserByIdAndPassword()");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return user;
	}
	
	public UserResponseDto createUser(UserRequestDto userDto) {
		UserResponseDto user = null;
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

			user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: UserDao -> createUser()");
		} finally {
			DBManager.close(conn, pstmt);
		}

		return user;
	}

	public UserResponseDto updateUserPassword(UserRequestDto userDto, String newPassword) {
		UserResponseDto user = null;
		String sql = "UPDATE users SET password=? WHERE id=?";
		
		if (findUserByIdAndPassword(userDto.getId(), userDto.getPassword()) == null)
			return null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, PasswordCrypto.encrypt(newPassword));
			pstmt.setString(2, userDto.getId());

			pstmt.execute();

			User userVo = findUserById(userDto.getId());
			user = new UserResponseDto(userVo);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: updateUserPassword");
		} finally {
			DBManager.close(conn, pstmt);
		}

		return user;
	}

	public UserResponseDto updateUserAddress(UserRequestDto userDto) {
		UserResponseDto user = null;
		String sql = "UPDATE users SET address=? WHERE id=?";

		if (findUserByIdAndPassword(userDto.getId(), userDto.getPassword()) == null)
			return null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userDto.getAddress());
			pstmt.setString(2, userDto.getId());
			pstmt.setString(3, userDto.getPassword());

			pstmt.execute();

			User userVo = findUserById(userDto.getId());
			user = new UserResponseDto(userVo);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: updateUserAddress");
		} finally {
			DBManager.close(conn, pstmt);
		}

		return user;
	}

	public UserResponseDto updateUserPhone(UserRequestDto userDto) {
		UserResponseDto user = null;
		String sql = "UPDATE users SET phone=? WHERE id=?";

		if (findUserByIdAndPassword(userDto.getId(), userDto.getPassword()) == null)
			return null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userDto.getPhone());
			pstmt.setString(2, userDto.getId());
			pstmt.setString(3, userDto.getPassword());

			pstmt.execute();

			User userVo = findUserById(userDto.getId());
			user = new UserResponseDto(userVo);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: updateUserPhone");
		} finally {
			DBManager.close(conn, pstmt);
		}

		return user;
	}

	public boolean deleteUser(UserRequestDto userDto) {
		boolean isDeleted = true;

		if (findUserByIdAndPassword(userDto.getId(), userDto.getPassword()) == null)
			return false;

		String sql = "DELETE FROM users WHERE id=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userDto.getId());

			pstmt.execute();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: deleteUser");
			isDeleted = false;
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isDeleted;
	}

	private User findUserById(String id) {
		User user = null;
		String sql = "SELECT id, name, address, phone, is_admin, reg_date FROM users WHERE id=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				String name = rs.getString(2);
				String address = rs.getString(3);
				String phone = rs.getString(4);
				boolean isAdmin = rs.getBoolean(5);
				Timestamp regDate = rs.getTimestamp(6);
				
				user = new User(id, name, address, phone, isAdmin, regDate);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: findUserById");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return user;
	}

	private User findUserByPhone(String phone) {
		User user = null;
		String sql = "SELECT id, name, address, is_admin, reg_date FROM users WHERE phone=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				boolean isAdmin = rs.getBoolean(4);
				Timestamp regDate = rs.getTimestamp(5);
				
				user = new User(id, name, address, phone, isAdmin, regDate);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error: findUserById");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return user;
	}
	
	public boolean isDuplId(String id) {
		return findUserById(id) != null;
	}

	public boolean isDuplPhone(String phone) {
		return findUserByPhone(phone) != null;
	}
}