package com.ezen.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.dto.MemberDto;

public class MemberDao {
	private MemberDao(){}
	private static MemberDao itc = new MemberDao();
	public static MemberDao getInstance() {return itc;}
	
	// 연결 객체 준비
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 연결 정보
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "scott";
	String pwd = "tiger";
	
	// 연결, 단절 함수 생성
	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pwd);
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
		return con;
	}
	
	private void close() {
		try {
			if(rs != null)rs.close();
			if(pstmt != null)pstmt.close();
			if(con != null)con.close();
		} catch (SQLException e) {e.printStackTrace();
		}
	}

	public MemberDto getMember(String id) {
		// 전달된 id로 검색해서 해당 회원이 없으면, null 값이 리턴되도록 dto 의 초기값은 null 입니다
		// 조회된 회원이 있는 경우에 new MemberDto 가 실행되어 mdto 에 저장됩니다
		MemberDto mdto = null;
		String sql = "SELECT * FROM member WHERE userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mdto = new MemberDto();
				mdto.setName(rs.getString("name"));
				mdto.setUserid(rs.getString("userid"));
				mdto.setPwd(rs.getString("pwd"));
				mdto.setEmail(rs.getString("email"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setAdmin(rs.getInt("admin"));
			}
		} catch (SQLException e) {e.printStackTrace();
		}finally {close();
		}
		
		return mdto;
	}

	public ArrayList<MemberDto> selectMember() {
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		String sql = "SELECT * FROM member ORDER BY userid";
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
		} finally {close();}
		
		return list;
	}

	public int insertMember(MemberDto mdto) {
		int result = 0;
		String sql ="INSERT INTO member(userid, name, pwd, phone, email, admin) "
				+ " VALUES(?, ?, ?, ?, ?, ?)";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getUserid());
			pstmt.setString(2, mdto.getName());
			pstmt.setString(3, mdto.getPwd());
			pstmt.setString(4, mdto.getPhone());
			pstmt.setString(5, mdto.getEmail());
			pstmt.setInt(6, mdto.getAdmin());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
	
	public int updateMember(MemberDto mdto) {
		int result = 0;
		String sql = "UPDATE member SET name=?, pwd=?, phone=?, email=?, admin=? WHERE userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getPwd());
			pstmt.setString(3, mdto.getPhone());
			pstmt.setString(4, mdto.getEmail());
			pstmt.setInt(5, mdto.getAdmin());
			pstmt.setString(6, mdto.getUserid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
	
	public void editAdmin(String userid) {
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
			sql = "UPDATE member SET admin=? WHERE userid=?";
			pstmt = con.prepareStatement(sql);
			if(admin ==1) {
				pstmt.setInt(1, 0);
			}else {
				pstmt.setInt(1, 1);
			}
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		}finally {close();}
	}

	public void deleteMember(String userid) {
		String sql = "DELETE FROM member WHERE userid=?";
		con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		}finally {
			close();
		}
	}
}
