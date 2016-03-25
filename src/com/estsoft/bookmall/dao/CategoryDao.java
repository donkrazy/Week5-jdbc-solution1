package com.estsoft.bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.bookmal.vo.CategoryVo;
import com.estsoft.db.DBConnection;

public class CategoryDao {
	private DBConnection dbConnection;
	
	public CategoryDao(  DBConnection dbConnection ) {
		this.dbConnection = dbConnection;
	}
	
	public void insert( CategoryVo CategoryVo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// DB 연결 가져오기
			conn = dbConnection.getConnection();
			
			//Statement 준비
			String sql = "insert into category values(  null, ? )";
			pstmt = conn.prepareStatement( sql );
			
			// bind
			pstmt.setString( 1, CategoryVo.getName() );
			
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
	
	public List<CategoryVo> getList() {
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();
			String sql ="SELECT no, name FROM category ORDER BY no ASC";
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				Long no = rs.getLong( 1 );
				String name = rs.getString( 2 );
				
				CategoryVo CategoryVo = new CategoryVo();
				CategoryVo.setNo( no );
				CategoryVo.setName( name );
				list.add( CategoryVo );
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