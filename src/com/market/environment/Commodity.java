package com.market.environment;

public class Commodity {
	int commodityId;
	int touristNumber;
	Agent onwer;
	public Commodity(int commodityId,int touristNumber,Agent onwer) {
		this.commodityId = commodityId;
		this.touristNumber = touristNumber;
		this.onwer = onwer;
	}
	
	@Override
	public String toString() {
		return String.format("Commodity [commodityId=%2d, touristNumber=%2d, onwerId=%2d]", commodityId,touristNumber,onwer.getAgentID());
	}
	public int getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
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
