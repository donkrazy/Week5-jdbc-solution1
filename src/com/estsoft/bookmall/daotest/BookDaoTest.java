package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmal.vo.BookVo;
import com.estsoft.bookmall.dao.BookDao;
import com.estsoft.bookmall.dao.BookMallDBConnection;

public class BookDaoTest {

	public static void main( String[] args ) {
		insertTest();
		listTest();
	}

	public static void insertTest() {
		BookVo bookVo = new BookVo();
		BookDao bookDao = new BookDao( new BookMallDBConnection() );

		bookVo.setTitle( "Java의 신" );
		bookVo.setCategoryNo( 1L );
		bookDao.insert( bookVo );

		bookVo.setTitle( "곰브리치 세계사" );
		bookVo.setCategoryNo( 3L );
		bookDao.insert( bookVo );
	}
	
	public static void listTest() {
		BookDao bookDao = new BookDao( new BookMallDBConnection() );
		List<BookVo> listBookVo = bookDao.getList();
		for( BookVo vo : listBookVo ) {
			System.out.println( vo );
		}
	}

}
