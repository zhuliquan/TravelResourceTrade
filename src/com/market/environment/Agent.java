package com.market.environment;

public class Agent {
	int agentID;                    //表示标号
	double valuation;			    //单位旅游资源的保估价
	int touristNumber;              //现阶段拥有的商品指的是游客的数目
	int touristCapcity;			    //可以容纳的游客数目
	
	/*
	 * 将agent的Id,估值,现有的游客数目,需要的游客数目输入
	 * */
	public Agent(int ID,double valuation,int touristNumber,int requestTouristNumber){
		this.agentID = ID;
		this.valuation = valuation;
		this.touristNumber = touristNumber;
		this.touristCapcity = touristNumber + requestTouristNumber;
	}
	public int getAgentID() {
		return agentID;
	}
	public void setAgentID(int agentID) {
		this.agentID = agentID;
	}
	public double getValuation() {
		return valuation;
	}
	public void setValuation(double valuation) {
		this.valuation = valuation;
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
		return String.format("Agent [agentID=%2d, valuation=%.2f, touristNumber=%2d, touristCapcity=%2d]", 
		agentID,valuation,touristNumber,touristCapcity);
	}	
}
