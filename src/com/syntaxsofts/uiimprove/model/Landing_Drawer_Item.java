/*
 * Class for defining the model of the menu drawer
 * Contains basic getter and setter functions for the object
 * Author: SJo	Created Date: 27-07-14	Version: 1.0
 */

package com.syntaxsofts.uiimprove.model;

public class Landing_Drawer_Item {
	
	private String title;
	private int icon;
	
	public Landing_Drawer_Item() {}
	
	public Landing_Drawer_Item(String itemTitle, int itemIcon) {
		this.title = itemTitle;
		this.icon = itemIcon;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public int getIcon(){
		return this.icon;
	}
	
	public void setIcon(int icon){
		this.icon = icon;
	}
	
	public void setTitle(String title){
		this.title = title;
	}

}
