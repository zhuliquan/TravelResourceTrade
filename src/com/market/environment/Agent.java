package com.market.environment;

public class Agent {
	int agentID;                    //表示标号
	double priceForTourist;			//单位游客的要价 是agent 对于 tourist的要价 应该是一个 正太分布的情况
	int touristNumber;              //现阶段拥有的商品指的是游客的数目
	int touristCapcity;			    //可以容纳的游客数目
	double earnMoney;               //挣到的资金
	/*
	 * 将agent的Id,估值,现有的游客数目,需要的游客数目输入
	 * */
	public Agent(int ID,double priceForTourist,int touristNumber,int requestTouristNumber){
		this.agentID = ID;
		this.priceForTourist = priceForTourist;
		this.touristNumber = touristNumber;
		this.touristCapcity = touristNumber + requestTouristNumber;
	}
	public double getEarnMoney() {
		return earnMoney;
	}
	public void setEarnMoney(double earnMoney) {
		this.earnMoney = earnMoney;
	}
	public int getAgentID() {
		return agentID;
	}
	public void setAgentID(int agentID) {
		this.agentID = agentID;
	}
	public double getValuation() {
		return priceForTourist;
	}
	public void setValuation(double valuation) {
		this.priceForTourist = valuation;
	}
	public int getTouristNumber() {
		return touristNumber;
	}
	public void setTouristNumber(int touristNumber) {
		this.touristNumber = touristNumber;
	}
	public int getTouristCapcity() {
		return touristCapcity;
	}
	public void setTouristCapcity(int touristCapcity) {
		this.touristCapcity = touristCapcity;
	}
	public int getRequestTouristNumber() {
		return touristCapcity - getTouristNumber();
	}
	@Override
	public String toString() {
		return String.format("Agent [agentID=%2d, priceForTourist=%.2f, touristNumber=%2d, touristCapcity=%2d]", 
		agentID,priceForTourist,touristNumber,touristCapcity);
	}	
}
