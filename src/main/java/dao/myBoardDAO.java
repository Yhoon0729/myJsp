package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.myBoard;

public class myBoardDAO {
	public Connection getConnection() {
		// 1. driver
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kic24", "1234");
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2 connection
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public int insertBoard(myBoard board) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into myboard values (myboardseq.nextval,?,?,?,?,?,sysdate,0,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getPwd());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getFile1());
			pstmt.setString(6, board.getBoardid()); // boardid

			int num = pstmt.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public List<myBoard> myBoardList(String boardid, int pageInt, int limit) {
		Connection conn = getConnection();
		System.out.println(boardid);
		PreparedStatement pstmt = null;
		String sql = "	select *	"
				+ "	from (select rownum rn, a.* from myboard a where boardid=? order by num desc)	"
				+ "	where rn between ? and ?	";
		List<myBoard> li = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardid);
			pstmt.setInt(2, (pageInt-1)*limit+1);
			pstmt.setInt(3, pageInt*limit);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				myBoard m = new myBoard();
				m.setNum(rs.getInt("num"));
				m.setPwd(rs.getString("pwd"));
				m.setName(rs.getString("name"));
				m.setSubject(rs.getString("subject"));
				m.setContent(rs.getString("content"));
				m.setFile1(rs.getString("file1"));
				m.setRegdate(rs.getTimestamp("regdate"));
				m.setReadcnt(rs.getInt("readcnt"));
				m.setBoardid(rs.getString("boardid"));
				li.add(m);
			}
			return li;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int boardCount(String boardid) {
		Connection conn = getConnection();
		PreparedStatement pstmt=null;
		String sql = 
		"select nvl(count(*),0) from myBoard where boardid = ?"; //1
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {				
			return rs.getInt(1);
			} else {				return 0;			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				return 0;
	}
	
	public myBoard getBoard(int num) {
		Connection conn = getConnection();
		PreparedStatement pstmt=null;
		String sql = 
		"select * from myBoard where num = ?";
		//4. mapping
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				myBoard board = new myBoard();
				//id 있음
				board.setNum(rs.getInt("num"));
				board.setName(rs.getString("name"));
				board.setPwd(rs.getString("pwd"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setFile1(rs.getString("file1"));
				board.setReadcnt(rs.getInt("readcnt"));
			
				
				
			return board;
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			return null;
	}
	
	public int addReadCount(int num) {
		Connection conn = getConnection();
		PreparedStatement pstmt=null;
		String sql = 
		"update myboard set readcnt = readcnt+1 "
		+ " where num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);			
			int count = pstmt.executeUpdate();
			return count;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;		
	}
	public int boardUpdate(myBoard board) {
		Connection conn = getConnection();
		PreparedStatement pstmt=null;
		String sql = 
		"update myboard set name=?, subject=?, content=?, file1=? "
		+ " where num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getFile1());
			pstmt.setInt(5, board.getNum());			
			int num = pstmt.executeUpdate();
			return num;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	public int boardDelete(int num) {
		Connection conn = getConnection();
		PreparedStatement pstmt=null;
		String sql = 
		"delete from myboard where num=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
					
			int count = pstmt.executeUpdate();
			return count;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
} // end class