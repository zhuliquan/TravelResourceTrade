package com.market.environment;

import java.util.ArrayList;

import com.market.utility.MersenneTwisterFast;
import com.setting.Setting;

import cern.jet.random.Normal;
/*
 * 表示市场的环境
 * agentNumber 表示agent的数目
 * agents 表示存储的agent的
 * */
public class Environment {
	int agentNumber;
	int commodityNumber;
	ArrayList<Agent> agents;
	ArrayList<Commodity> commodities;
	public Environment(int agentNumber,int commodityNumber) {
		this.agentNumber = agentNumber;
		this.commodityNumber = commodityNumber;
		agents = new ArrayList<>();
		commodities = new  ArrayList<>();
		MersenneTwisterFast rand = new MersenneTwisterFast(System.currentTimeMillis());
		//TODO 需要考虑
		for (int i=0 ;i<agentNumber; i++) {
			
			int agentId = i;
			int commodityId = i;		
			double valuation = Setting.BASE_VALUATION + Normal.staticNextDouble(1.0, 0.1);
			//没有需求不会进入市场的
			int requestTouristNumber = rand.nextInt(Setting.REQUEST_TOURISTNUMBER+1);
			if (requestTouristNumber == 0) {
				requestTouristNumber = rand.nextInt(Setting.REQUEST_TOURISTNUMBER+1);
			}
			//当前已经有的游客数目
			int touristNumber = rand.nextInt(Setting.TOURIST_NUMBER+1);
    		Agent agent = new Agent(agentId,valuation,touristNumber,requestTouristNumber);
    		Commodity commodity = new Commodity(commodityId, touristNumber, agent);
			agents.add(agent);
			commodities.add(commodity);
		}
	}
	public int getCommodityNumber() {
		return commodityNumber;
	}
	public void setCommodityNumber(int commodityNumber) {
		this.commodityNumber = commodityNumber;
	}
	public Agent getAgent(int index) {
		return agents.get(index);
	}
	public void setAgent(int index, Agent agent) {
		agents.set(index, agent);
	}	
	public Commodity getCommodity(int index) {
		return commodities.get(index);
	}
	public void setCommodity(int index, Commodity commodity) {
		commodities.set(index, commodity);
	}
	public ArrayList<Commodity> getCommodities() {
		return commodities;
	}
	public void setCommodities(ArrayList<Commodity> commodities) {
		this.commodities = commodities;
	}
	public int getAgentNumber() {
		return agentNumber;
	}
	public void setAgentNumber(int agentNumber) {
		this.agentNumber = agentNumber;
	}
	public ArrayList<Agent> getAgents() {
		return agents;
	}
	public void setAgents(ArrayList<Agent> agents) {
		this.agents = agents;
	}
	public static void main(String[] args) {
		Environment env = new Environment(20,20);
		for (int i=0; i<20; i++) {
			System.out.println(env.getAgent(i));
		}
		for (int i=0; i<20; i++) {
			System.out.println(env.getCommodity(i));
		}
	}
}