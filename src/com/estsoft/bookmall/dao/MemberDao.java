package com.estsoft.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.bookmal.vo.MemberVo;
import com.estsoft.db.DBConnection;

public class MemberDao {
	private DBConnection dbConnection;
	
	public MemberDao(  DBConnection dbConnection ) {
		this.dbConnection = dbConnection;
	}
	
	public void insert( MemberVo memberVo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// DB 연결 가져오기
			conn = dbConnection.getConnection();
			
			//Statement 준비
			String sql = "INSERT INTO member VALUES(  null, ?, password(?), ? )";
			pstmt = conn.prepareStatement( sql );
			
			// bind
			pstmt.setString( 1, memberVo.getEmail() );
			pstmt.setString( 2, memberVo.getPassword() );
			pstmt.setString( 3, memberVo.getName() );
			
			// SQL 실행
			pstmt.executeUpdate();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류:" + ex );
		} finally {
			// 자원정리(clean-up)
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}		
	}
	
	public List<MemberVo> getList() {
		List<MemberVo> list = new ArrayList<MemberVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();
			String sql ="SELECT no, email, name FROM member ORDER BY no ASC";
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				Long no = rs.getLong( 1 );
				String email = rs.getString( 2 );
				String name = rs.getString( 3 );
				
				MemberVo memberVo = new MemberVo();
				memberVo.setNo( no );
				memberVo.setEmail( email );
				memberVo.setName( name );
				list.add( memberVo );
			}
		} catch( SQLException ex ) {
			System.out.println( "error : " + ex );
		} finally {
			try {
				if( rs != null ) {
					rs.close();
				}
				if( stmt != null ) {
					stmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
		return list;
	}	
}
