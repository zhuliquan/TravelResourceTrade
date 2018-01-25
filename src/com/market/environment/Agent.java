package com.market.environment;

public class Agent {
	int agentID;                    //��ʾ���
	double valuation;			    //��λ������Դ�ı�����
	int touristNumber;              //�ֽ׶�ӵ�е���Ʒָ�����ο͵���Ŀ
	int touristCapcity;			    //�������ɵ��ο���Ŀ
	
	/*
	 * ��agent��Id,��ֵ,���е��ο���Ŀ,��Ҫ���ο���Ŀ����
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
