package com.kh.practice.list.library.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.practice.list.library.controller.BookController;
import com.kh.practice.list.library.model.vo.Book;

// 화면(view)용 클래스 --> 사용자에게 데이터를 표시(출력)하거나 입력을 받는 역할
public class BookMenu {
	
	private Scanner sc = new Scanner(System.in);
	private BookController bc = new BookController();
	
	public void mainMenu() {
		// 사용자가 직접 메인 메뉴를 선택할 수 있음.
		// 종료 전까지 반복 실행. 각 메뉴 선택 시 해당 메소드 이동.(호출)
		
		System.out.println("== Welcome KH Library ==");
		System.out.println("\n");
		while(true) {
		System.out.println("******* 메인 메뉴 *******");
		System.out.println("1. 새 도서 추가");
		System.out.println("2. 도서 전체 조회");
		System.out.println("3. 도서 검색 조회");
		System.out.println("4. 도서 삭제");
		System.out.println("5. 도서 명 오름차순 정렬");
		System.out.println("9. 종료");
		System.out.print("메뉴 번호 선택 : ");
		int menu = sc.nextInt();
		sc.nextLine();		// nextLine 메소드 외에는 버퍼에 엔터키(\n) 값이 남아 있어 제거하기 위해 사용
		
			switch(menu) {
			case 1:
				insertBook();
				break;
			case 2:
				selectList();
				break;
			case 3:
				searchBook();
				break;
			case 4:
				deleteBook();
				break;
			case 5:
				ascBook();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다.");
				return;		// 메소드 종료 --> 현재 메소드를 호출한 위치로 돌아감!
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
				break;	// 프로그램이 보안 인증을 받고자 할 때는 break 써 주는 게 좋음
			}
		}
	}
	/**
	 * 1. 새 도서 추가용 메소드
	 */
	public void insertBook() {
		System.out.print("도서 명 : ");
		String title = sc.nextLine();
		
		System.out.print("저자 명 : ");
		String author = sc.nextLine();
		
		System.out.print("장르(1. 인문 / 2. 과학 / 3. 외국어 / 4. 기타) : ");
		int category = sc.nextInt();
		
		System.out.print("가격 : ");
		int price = sc.nextInt();
		
		// * 조건문(if/switch) 사용한 방식
		String genre = "";
		switch(category) {
		case 1:
			genre = "인문";
		case 2:
			genre = "과학";
		case 3:
			genre = "외국어";
		case 4:
			genre = "기타";
		}
		Book b = new Book(title, author, genre, price);

		// * 배열을 사용한다면...
		/*
		String[] categories = {"인문", "자연과학", "의료", "기타"};
		Book b2 = new Book(title, author, categories[category-1], price);
		*/
		// * 문자열 split() 사용한다면...
		/*
		String categories = "인문,자연과학,의료,기타";
		Book b3 = new Book(title, author, categories.split(",")[category-1], price);
		*/
		
		// 컨트롤러에게 생성된 Book 객체 전달(insertBook)
		bc.insertBook(b);
		
	}
	
	/**
	 * 2. 도서 전체용 view 메소드
	 */
	public void selectList() {
		// 컨트롤러의 selectList() 메소드 호출 후 결과 값을 변수에 저장
		ArrayList<Book> bookList = new ArrayList<>(bc.selectList());
		
		// 조건식을 이용하여,
		//              비어있을 경우, "존재하는 도서가 없습니다" 문구 출력
		//				비어있지 않은 경우 반복문을 통해 리스트의 Book 객체를 출력
		if (bookList.isEmpty()) {		// isEmpty() => 리스트가 비어있는지 여부를 확인하는 메소드
			// 리스트가 비어있는 경우 (bookList.isEmpty() --> true)
			System.out.println("존재하는 도서가 없습니다.");
		} else {
			// 리스트가 비어있지 않은 경우 (bookList.isEmpty() --> False)
			for(int i=0; i<bookList.size(); i++) {		// size() : 리스트의 저장된 데이터 길이
				System.out.println(bookList.get(i/*.toString()*/));	// get(index) : 해당 index 위치의 데이터 조회
			}
		}
	}
	/**
	 * 3. 도서 검색용 view 메소드
	 */
	public void searchBook() {
		// 키워드를 입력받아 
		System.out.print("검색 키워드 : ");
		String keyword = sc.nextLine();
		
		// 컨트롤러의 searchBook 메소드로 keyword 전달 후 결과를 변수에 저장
		ArrayList<Book> searchList = bc.searchBook(keyword);
		
		// 조건식을 이용하여,
		//              비어있는 경우 "검색 결과가 없습니다" 출력
		//              비어있지 않은 경우 반복문을 사용하여 리스트의 Book 객체 출력
		if(searchList.isEmpty()) {
			System.out.println("검색 결과가 없습니다.");
			return;
		} else {
			for (Book b : searchList) {
				System.out.println(b);
			}
		/*
		for(int i=0; i<searchList.size(); i++) {
			System.out.println(searchList.get(i));
		}
		*/
		}
	}
	
	public void deleteBook() {
		System.out.println("삭제할 도서 명 : ");
		String title = sc.nextLine();
		
		System.out.println("삭제할 저자 명 : ");
		String author = sc.nextLine();
		
		Book remove = bc.deleteBook(title, author);
		
		if (remove != null) {
			// remove 변수의 값이 null이 아닌 경우 --> 값이 있는 경우 (어딘가를 참조하고 있음!)
			System.out.println("성공적으로 삭제되었습니다.");
		} else {
			// remove 변수의 값이 null인 경우 --> 값이 없는 경우 (참조하는 곳이 없음!)
		}
			System.out.println("삭제할 도서를 찾지 못했습니다.");
		
	}
	
	/**
	 * 도서명 오름차순 정렬용 메소드
	 */
	public void ascBook() {
		//bc(BookController)의 ascBook() 메소드 값에 따라
		//                   성공 시 “정렬에 성공하였습니다.”
		//					 실패 시 “정렬에 실패하였습니다.” 출력
		int result = bc.ascBook();
		if (result == 1) {
			System.out.println("정렬에 성공하였습니다.");
		} else {
			System.out.println("정렬에 실패하였습니다.");
		}
		
	}

}
