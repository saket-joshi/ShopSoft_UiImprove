/*
 * Class for the model of the cards for the shops list
 * Contains the getter and setter methods for the cards
 * 
 * Author: SJo	Created Date: 29-07-14	Version: 2.0
 * 
 * Version History: 1.0 - Created the basic implementation
 * 					1.3 - Removed webview as the card did not look good
 * 					2.0 - Completely new UI model 
 * 					2.1 - Again a new UI (probably the final)
 */


package com.syntaxsofts.uiimprove.model;

public class Shop_Card_Item {

	private String title;
	private String iconUrl;
	private String description;
	
	public Shop_Card_Item() { }
	
	public Shop_Card_Item(String title, String description, String iconUrl) {
		this.title = title;
		this.iconUrl = iconUrl;
		this.description = description;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getIconUrl() {
		return this.iconUrl;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setIcon(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
