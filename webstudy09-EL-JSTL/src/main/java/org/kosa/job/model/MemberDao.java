package org.kosa.job.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DbConfig;

//Dao : Data Access Object 데이터 제어 로직을 담당하는 객체
//Singleton Pattern : 시스템 상에 단 하나 객체 생성해서 사용하는 설계 패턴
public class MemberDao {
	// 생성자에서 throws
	private static MemberDao instance;
	
	private MemberDao() throws ClassNotFoundException {
		Class.forName(DbConfig.DRIVER);
	}
	// 지연된 로딩 : Lazy Loading
	public static MemberDao getInstance() throws ClassNotFoundException {
		if(instance == null)
			instance = new MemberDao();
		return instance;
	}
	
	
	/*
	 * method() throws SQLException { Connection PreparedStatement ResultSet try{
	 * 
	 * }finally { close } return memberVO; }
	 */

	public MemberVo findMemberById(String memberId) throws SQLException {
		MemberVo memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PASS);
			String sql = "select id, password, name, address from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next())
				memberVO = new MemberVo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			System.out.println(rs);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return memberVO;
	}

	public void register(MemberVo memberVO) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 1. 커넥션 해주기
			con = DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PASS);
			// 2. 실행할 sql문 설정하기
			String sql = "insert into member(id, password, name, address) VALUES (?,?,?,?)";
			// 3. sql문 실행하기
			pstmt = con.prepareStatement(sql);

			// 4. member에서 값 가져와서 설정하기
			// 1,2,3,4 는 ? 순서
			// set 사용으로 보안성 증가
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPassword());
			pstmt.setString(3, memberVO.getName());
			pstmt.setString(4, memberVO.getAddress());

			// 5. 반환 값 받기 (int 형)
			int result = pstmt.executeUpdate();
			System.out.println("insert count" + result);
		} finally {
			// 6. 생성 역순으로 close
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}

	}

	public ArrayList<MemberVo> findMemberByAddress(String address) throws SQLException {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. 커넥션 해주기
			con = DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PASS);
			// 2. 실행할 sql문 설정하기
			String sql = "select id, name, address from member  where address=?";
			// 3. sql문 실행하기
			pstmt = con.prepareStatement(sql);

			// 4. 첫번째 ? 에 address 할당 : 이 할당된 address로
			// sql문이 실행됨
			pstmt.setString(1, address);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// getString안에 컬럼명 써도 됨 (1, "name", 3 섞어쓰기도 가능)
				list.add(new MemberVo(rs.getString("id"), null, rs.getString("name"), rs.getString("address")));
			}
		} finally {
			// 6. 생성 역순으로 close
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}

		return list;
	}

	public int getAllMemberCount() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			con = DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PASS);
			String sql = "select count(*) from member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return count;
	}

}
