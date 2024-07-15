package com.kh.chap01_beforeVSafter.after.model.vo;
// *.after.* 경로의 Desktop
public class Desktop extends Product {
	// 필드부
	private boolean allInOne;
	
	// 생성자부
	public Desktop() {}
	
	public Desktop(String brand
					, String code
					, String name
					, int price
					, boolean allInOne) {
		super(brand, code, name, price);
		
		this.allInOne = allInOne;
	}
	
	// 메소드부
	public String information() {
		return super.information() + ", allInOne=" + allInOne;
	}
	// boolean 자료형 필드는 getter 이름 isXXX로 지어진다.
	public boolean isAllInOne() {
		return allInOne;
	}
	
	public void setAllInOne(boolean allInOne) {
		this.allInOne = allInOne;
	}
	
}
