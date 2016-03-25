package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmal.vo.MemberVo;
import com.estsoft.bookmall.dao.BookMallDBConnection;
import com.estsoft.bookmall.dao.MemberDao;

public class MemerDaoTest {

	public static void main( String[] args ) {
		insertTest();
		listTest();
	}

	public static void insertTest() {
		MemberVo vo = new MemberVo();
		MemberDao dao = new MemberDao( new BookMallDBConnection() );

		vo.setEmail( "dooly@gmail.com" );
		vo.setPassword(  "1234" );
		vo.setName( "둘리" );
		dao.insert( vo );

		vo.setEmail( "michol@gmail.com" );
		vo.setPassword(  "1234" );
		vo.setName( "마이콜" );
		dao.insert( vo );
	}
	
	public static void listTest() {
		MemberDao dao = new MemberDao( new BookMallDBConnection() );
		List<MemberVo> list = dao.getList();
		for( MemberVo vo : list ) {
			System.out.println( vo );
		}
	}

}
