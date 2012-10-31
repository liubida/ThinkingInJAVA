/**
 * 
 */
package com.liubida.sohu.android.wuhan;

/**
 * @author Leon
 *
 */
public enum FolderType {
	Read		("_read"),
	Unread		("_unread"),
	Starred		("_starred"),
	RecentRead	("_recent_read");
	
	FolderType(String __s) {
		asYouWish = __s;
	}
	
	public String toString() {
		return asYouWish;
	}
	
	private String asYouWish = "_read";

}


