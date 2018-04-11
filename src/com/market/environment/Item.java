package com.market.environment;

public class Item {
	int itemId;
	int touristNumber;
	Agent onwer;
	public Item(int itemId,int touristNumber,Agent onwer) {
		this.itemId = itemId;
		this.touristNumber = touristNumber;
		this.onwer = onwer;
	}
	@Override
	public String toString() {
		return String.format("Item [itemId=%2d, touristNumber=%2d]", itemId,touristNumber);
	}
	
	@Override
	public Item clone() throws CloneNotSupportedException {
		Item newItem = new Item(itemId,touristNumber,onwer);
		return newItem;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getTouristNumber() {
		return touristNumber;
	}
	public void setTouristNumber(int touristNumber) {
		this.touristNumber = touristNumber;
	}
	public Agent getOnwer() {
		return onwer;
	}
	public void setOnwer(Agent onwer) {
		this.onwer = onwer;
	}
}
