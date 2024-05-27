package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.myMember;

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

		String sql = "select * from mymember where id = ?";

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
	
	public int insertMember(myMember kic) {
		Connection conn = getConnection();
		// 3. PreparedStatement
		PreparedStatement pstmt = null;
		
		String sql = "insert into myMember values (?,?,?,?,?,?,?)";
		
		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kic.getId());
			pstmt.setString(2, kic.getPwd());
			pstmt.setString(3, kic.getName());
			pstmt.setInt(4, kic.getBirth());
			pstmt.setString(5, kic.getAddress());
			pstmt.setString(6, kic.getTel());
			pstmt.setString(7, kic.getEmail());
			// sql 실행
			int num = pstmt.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return 0;
	} // end of insertMember()
	
	public int updateMember(myMember kic) {
		Connection conn = getConnection();
		// 3. PreparedStatement
		PreparedStatement pstmt = null;
		
		String sql = "update mymember set name=?, birth=?, address=?, tel=?, email=? where id = ?";
		
		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kic.getName());
			pstmt.setInt(2, kic.getBirth());
			pstmt.setString(3, kic.getAddress());
			pstmt.setString(4, kic.getTel());
			pstmt.setString(5, kic.getEmail());
			pstmt.setString(6, kic.getId());
			// sql 실행
			int num = pstmt.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return 0;
	} // end of updateMember()
	
	public int deleteMember(String id) {
		Connection conn = getConnection();
		// 3. PreparedStatement
		PreparedStatement pstmt = null;
		
		String sql = "delete from mymember where id = ?";
		
		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			// sql 실행
			int num = pstmt.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return 0;
	} // end of deleteteMember()
	
	
	public int modifyPwd(String id, String modPwd) {
		Connection conn = getConnection();
		// 3. PreparedStatement
		PreparedStatement pstmt = null;
		
		String sql = "update mymember set pwd = ? where id = ?";
		
		// 4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modPwd);
			pstmt.setString(2, id);
			
			
			// sql 실행
			int num = pstmt.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of tryCatch
		
		return 0;
	} // end of deleteteMember()
	
	public List<myMember> memberList() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "select * from mymember";
		
		List<myMember> li = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				myMember m = new myMember();
				m.setId(rs.getString("id"));
				m.setPwd(rs.getString("pwd"));
				m.setName(rs.getString("name"));
				m.setBirth(rs.getInt("birth"));
				m.setAddress(rs.getString("address"));
				m.setTel(rs.getString("tel"));
				m.setEmail(rs.getString("email"));
				li.add(m);
			} // end of while(rs.next())
			
			return li;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} // end of tryCatch
		
	} // end of memberList()
	
} // end of class
