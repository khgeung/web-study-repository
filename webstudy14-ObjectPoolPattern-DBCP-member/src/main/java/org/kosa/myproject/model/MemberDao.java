package org.kosa.myproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;


public class MemberDao {
	private static MemberDao instance = new MemberDao();
	// Database Connection Pool 객체의 표준 인터페이스 DataSource
	private DataSource dataSource;

	private MemberDao() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static MemberDao getInstance() {
		return instance;
	}

	public int getTotalMemberCount() throws SQLException {
		int totalCount = 0;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = this.dataSource.getConnection(); // dbcp로부터 connection 을 빌려온다
			String sql = "select count(*) from member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				totalCount = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}

		return totalCount;
	}

	public MemberVo findMemberById(String memberId) throws SQLException {
		MemberVo memberVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			String sql = "select id, password, name, address from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if (rs.next())
				memberVo = new MemberVo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			System.out.println(rs);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return memberVo;
	}

	public ArrayList<MemberVo> findMemberListByAddress(String address) throws SQLException {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			String sql = "select id, name, address from member where address=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, address);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// getString안에 컬럼명 써도 됨 (1, "name", 3 섞어쓰기도 가능)
				list.add(new MemberVo(rs.getString("id"), null, rs.getString("name"), rs.getString("address")));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}

		return list;
	}

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(pstmt, con);

	}

	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close(); // 컨넥션을 소멸시키는 것이 아니라 DBCP 에 컨넥션을 반납한다.
	}

}
