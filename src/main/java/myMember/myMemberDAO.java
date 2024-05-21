package myMember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class myMemberDAO {
	// DAO(Data Access Object)
	
	public Connection getConnection() {
		// 1. driver
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kic24", "1234");
			return conn;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// 2. connection
		catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return null;
		
	} // end of geConnection()
	
	public myMember getMember(String id) {
		Connection conn = getConnection();
		// 3. PreparedStatement
		PreparedStatement pstmt = null;

		String sql = "select * from myMember where id = ?";

		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				myMember m = new myMember();
				// id 있음
				m.setId(rs.getString("id"));
				m.setPwd(rs.getString("pwd"));
				m.setName(rs.getString("name"));
				m.setBirth(rs.getInt("birth"));
				m.setAddress(rs.getString("address"));
				m.setTel(rs.getString("tel"));
				m.setEmail(rs.getString("email"));
				return m;
			} else {
				return null;
			} // end of if
			
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return null;  
	} // end of getMemeber()
	
	public int insertMember(myMember my) {
		Connection conn = getConnection();
		// 3. PreparedStatement
		PreparedStatement pstmt = null;
		
		String sql = "insert into myMember values (?,?,?,?,?,?,?)";
		
		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, my.getId());
			pstmt.setString(2, my.getPwd());
			pstmt.setString(3, my.getName());
			pstmt.setInt(4, my.getBirth());
			pstmt.setString(5, my.getAddress());
			pstmt.setString(6, my.getTel());
			pstmt.setString(7, my.getEmail());
			// sql 실행
			int num = pstmt.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return 0;
	} // end of insertMember()
	
} // end of class
