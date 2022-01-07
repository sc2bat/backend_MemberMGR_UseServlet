package com.ezen.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.dto.MemberDto;

public class MemberDao {
	private MemberDao() {}
	private static MemberDao ist = new MemberDao();
	public static MemberDao getInstance() {return ist;}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String driver="oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "tiger");
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
		return con;
	}
	
	private void close() {
		try {
			if(con != null)con.close();
			if(pstmt != null)pstmt.close();
			if(rs != null)rs.close();
		} catch (SQLException e) {e.printStackTrace();
		}
	}

	public MemberDto getMember(String userid) {
		MemberDto mdto = null;
		//전달되어져 온 userid 로 회원을 검색하고 그 회원의 정보를 mdto 에 담아서 리턴
		
		String sql = "SELECT * FROM member WHERE userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mdto = new MemberDto();
				mdto.setUserid(rs.getString("userid"));
				mdto.setName(rs.getString("name"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setEmail(rs.getString("email"));
				mdto.setAdmin(rs.getInt("admin"));
			}
		} catch (SQLException e) {e.printStackTrace();
		}finally {close();
		}
		return mdto;
	}

	public int insertMember(MemberDto mdto) {
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
		} catch (SQLException e) {e.printStackTrace();
		}finally {close();
		}
		return result;
	}

	public int updateMember(MemberDto mdto) {
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
		} catch (SQLException e) {e.printStackTrace();
		}finally {close();
		}
		return result;
	}

	public void deleteMember(String userid) {
		String sql = "DELETE FROM member WHERE userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		}finally {close();
		}
		
	}

	public ArrayList<MemberDto> selectMember() {
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		String sql = "SELECT * FROM member ORDER BY admin, userid";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDto mdto = new MemberDto();
				mdto.setUserid(rs.getString("userid"));
				mdto.setName(rs.getString("name"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setEmail(rs.getString("email"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setAdmin(rs.getInt("admin"));
				list.add(mdto);
			}
		} catch (SQLException e) {e.printStackTrace();
		}finally {close();
		}
		return list;
	}

	public void editAdmin(String userid, int admin) {
		String sql = "UPDATE member SET admin=? WHERE userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, admin);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		}finally {close();
		}
	}
}
