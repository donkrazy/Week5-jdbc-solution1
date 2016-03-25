package com.estsoft.bookmall.app;

import java.util.List;

import com.estsoft.bookmal.vo.CategoryVo;
import com.estsoft.bookmal.vo.MemberVo;
import com.estsoft.bookmall.dao.BookMallDBConnection;
import com.estsoft.bookmall.dao.CategoryDao;
import com.estsoft.bookmall.dao.MemberDao;

public class BookMall {

	public static void main(String[] args) {

		// 1. MemberDao의  회원 생성
		MemberVo memberVo = new MemberVo();
		MemberDao memberDao = new MemberDao( new BookMallDBConnection() );

		memberVo.setEmail( "dooly@gmail.com" );
		memberVo.setPassword(  "1234" );
		memberVo.setName( "둘리" );
		memberDao.insert( memberVo );

		memberVo.setEmail( "michol@gmail.com" );
		memberVo.setPassword(  "1234" );
		memberVo.setName( "마이콜" );
		memberDao.insert( memberVo );
		
		// 2. MemberDao의  회원 리스트
		System.out.println( "****************************** 회원 리스트 ******************************" );
		List<MemberVo> list = memberDao.getList();
		for( MemberVo vo : list ) {
			System.out.println( vo );
		}		
		
		
		// 3. CategoryDao의 카테고리 생성
		CategoryVo categoryVo = new CategoryVo();
		CategoryDao categoryDao = new CategoryDao( new BookMallDBConnection() );

		categoryVo.setName( "컴퓨터/IT" );
		categoryDao.insert( categoryVo );
		categoryVo.setName( "철학" );
		categoryDao.insert( categoryVo );
		categoryVo.setName( "역사" );
		categoryDao.insert( categoryVo );	
		
		// 4. CategoryDao의 카테고리 리스트
		System.out.println( "****************************** 카테고리 리스트 ******************************" );
		List<CategoryVo> listCatergoryVo = categoryDao.getList();
		for( CategoryVo vo : listCatergoryVo ) {
			System.out.println( vo );
		}
		
		// 5. BookDao의 서적(상품) 생성
		// 6. BookDao의  서적(상품) 리스트

		// 7. CartDao의 장바구니 정보 생성
		// 8. CartDao의  장바구니 내용 리스트

		// 9. OrderDao의 주문 생성
		//10. OrderDao의  주문 리스트
		//11. OrderDao의 주문 서적 리스트  
		
		
	}

}
