/**
 * 
 */
package com.liubida.sohu.android.wuhan;

/**
 * @author Leon
 *
 */
public enum OrderType {
	CreateTimeUp	("create_time"),
	CreateTimeDown	("-create_time"),
	
	ReadTimeUp		("read_time"),
	ReadTimeDown	("-read_time"),
	
	UrlUp			("url"),
	UrlDown			("-url"),
	
	TitleUp			("title"),
	TitleDown		("-title");
	
	OrderType(String __s) {
		asYouWish = __s;
	}
	
	public String toString() {
		return asYouWish;
	}
	
	private String asYouWish = "_read";

}
