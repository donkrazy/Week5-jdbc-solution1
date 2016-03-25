package com.estsoft.bookmall.daotest;

import java.util.List;

import com.estsoft.bookmal.vo.CategoryVo;
import com.estsoft.bookmall.dao.BookMallDBConnection;
import com.estsoft.bookmall.dao.CategoryDao;

public class CategoryDaoTest {

	public static void main(String[] args) {
		insertTest();
		listTest();
	}

	public static void insertTest() {
		CategoryVo vo = new CategoryVo();
		CategoryDao dao = new CategoryDao( new BookMallDBConnection() );

		vo.setName( "컴퓨터/IT" );
		dao.insert( vo );

		vo.setName( "철학" );
		dao.insert( vo );

		vo.setName( "역사" );
		dao.insert( vo );

	}
	
	public static void listTest() {
		CategoryDao dao = new CategoryDao( new BookMallDBConnection() );
		List<CategoryVo> list = dao.getList();
		for( CategoryVo vo : list ) {
			System.out.println( vo );
		}
	}
	
}
