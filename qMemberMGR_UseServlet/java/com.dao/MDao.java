package com.ezen.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.dto.MDto;

public class MDao {
	private MDao() {	}
	private static MDao itc = new MDao();
	public static MDao getInstance() {return itc;}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "tiger");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private void close() {
		try {
			if(con != null)con.close();
			if(pstmt != null)pstmt.close();
			if(rs != null)rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public MDto getMember(String id) {
		MDto mdto = null;
		String sql = "SELECT * FROM member WHERE userid=?";
		
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mdto = new MDto();
				mdto.setUserid(rs.getString("userid"));
				mdto.setName(rs.getString("name"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setEmail(rs.getString("email"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setAdmin(rs.getInt("admin"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return mdto;
	}

	public ArrayList<MDto> selectQM() {
		ArrayList<MDto> qlist = new ArrayList<MDto>();
		String sql = "SELECT * FROM member ORDER BY userid";
		
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MDto mdto = new MDto();
				mdto.setUserid(rs.getString("userid"));
				mdto.setName(rs.getString("name"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setEmail(rs.getString("email"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setAdmin(rs.getInt("admin"));
				qlist.add(mdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {close();}
		
		return qlist;
	}

	public void eAdmin(String userid) {
		String sql = "SELECT admin FROM member WHERE userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			int admin = 0;
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				admin = rs.getInt("admin");
			}
			pstmt.close();
//			sql = "UPDATE member SET admin=? WHERE userid=" + userid;
			sql = "UPDATE member SET admin=? WHERE userid=?";
			pstmt = con.prepareStatement(sql);
			if(admin == 0) {
				pstmt.setInt(1, 1);
			}else {
				pstmt.setInt(1, 0);
			}
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}

	public int qinsertM(MDto mdto) {
		int result = 0;
		String sql = "INSERT INTO member VALUES(?, ?, ?, ?, ?, ?)";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getUserid());
			pstmt.setString(3, mdto.getPwd());
			pstmt.setString(4, mdto.getEmail());
			pstmt.setString(5, mdto.getPhone());
			pstmt.setInt(6, mdto.getAdmin());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return result;
	}

	public int qupdateM(MDto mdto) {
		int result = 0;
		String sql = "UPDATE member SET name=?, pwd=?, email=?, phone=?, admin=? WHERE userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getPwd());
			pstmt.setString(3, mdto.getEmail());
			pstmt.setString(4, mdto.getPhone());
			pstmt.setInt(5, mdto.getAdmin());
			pstmt.setString(6, mdto.getUserid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}

	public void qdeleteM(String userid) {
		String sql = "DELETE FROM member WHERE userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
}
