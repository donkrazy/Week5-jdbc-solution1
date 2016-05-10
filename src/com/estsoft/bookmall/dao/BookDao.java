package com.estsoft.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.bookmal.vo.BookVo;
import com.estsoft.db.DBConnection;

public class BookDao {
	private DBConnection dbConnection;
	
	public BookDao(  DBConnection dbConnection ) {
		this.dbConnection = dbConnection;
	}
	
	public void insert( BookVo bookVo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// DB 연결 가져오기
			conn = dbConnection.getConnection();
			
			//Statement 준비
			String sql = "INSERT INTO book VALUES(  null, ?, ?, ? )";
			pstmt = conn.prepareStatement( sql );
			
			// bind
			pstmt.setString( 1, bookVo.getTitle() );
			pstmt.setLong( 2, bookVo.getCategoryNo() );
			pstmt.setLong(3,  bookVo.getPrice() );
			
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
	
	public List<BookVo> getList() {
		List<BookVo> list = new ArrayList<BookVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();
			String sql =
				"      SELECT a.no, a.title, b.name" +
				"       FROM book a, category b" +
				"     WHERE a.category_no = b.no" +
				" ORDER BY a.no ASC";
			
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				Long no = rs.getLong( 1 );
				String title = rs.getString( 2 );
				String categoryName = rs.getString( 3 );
				
				BookVo bookVo = new BookVo();
				bookVo.setNo( no );
				bookVo.setTitle( title );
				bookVo.setCategoryName( categoryName );
				list.add( bookVo );
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
