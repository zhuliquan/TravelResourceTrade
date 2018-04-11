package com.market.environment;

public class Agent {
	int agentID;                    //��ʾ���
	double priceForTourist;			//��λ�ο͵�Ҫ�� ��agent ���� tourist��Ҫ�� Ӧ����һ�� ��̫�ֲ������
	int touristNumber;              //�ֽ׶�ӵ�е���Ʒָ�����ο͵���Ŀ
	int touristCapcity;			    //�������ɵ��ο���Ŀ
	double earnMoney;               //�������ʽ�
	/*
	 * ��agent��Id,��ֵ,���е��ο���Ŀ,��Ҫ���ο���Ŀ����
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
