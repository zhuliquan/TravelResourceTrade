package com.market.environment;

import java.util.ArrayList;
import java.util.Random;

import com.market.setting.Setting;
import com.market.utility.MersenneTwisterFast;

import cern.jet.random.Normal;
/*
 * 表示市场的环境
 * agentNumber 表示agent的数目
 * agents 表示存储的agent的
 * */
public class Environment {
	int agentNumber;
	int itemsNumber;
	ArrayList<Agent> agents;
	ArrayList<Item> items;
	public final static int typeByRand = 1;
	public final static int typeBySelect = 2;
	public Environment(ArrayList<Agent> agents,ArrayList<Item> items) {
		this.agents = agents;
		this.items = items;
		this.agentNumber = agents.size();
		this.itemsNumber = items.size();
	}
	public Environment(int agentNumber,int itemsNumber,int envGenType) {
		this.agentNumber = agentNumber;
		this.itemsNumber = itemsNumber;
		agents = new ArrayList<>();
		items = new  ArrayList<>();
		switch (envGenType) {
			case typeByRand:
				generateAgentByRand();break;
			case typeBySelect:
				generateAgentBySelect();break;
		}
	}
	public void generateAgentByRand() {
		
		MersenneTwisterFast rand = new MersenneTwisterFast(System.currentTimeMillis());
		for (int i=0 ;i<agentNumber; i++) {
			int agentId = i;
			int itemId = i;		
			double valuation = Setting.BASE_VALUATION + Normal.staticNextDouble(1.0, 0.1);
			//没有需求不会进入市场的
			int requestTouristNumber = rand.nextInt(Setting.REQUEST_TOURISTNUMBER);
			while (requestTouristNumber == 0) {
				requestTouristNumber = rand.nextInt(Setting.REQUEST_TOURISTNUMBER);
			}
			//当前已经有的游客数目
			int touristNumber = rand.nextInt(Setting.TOURIST_NUMBER);
    		Agent agent = new Agent(agentId,valuation,touristNumber,requestTouristNumber);
    		Item item   = new SingleItem(itemId, touristNumber, agent);
			agents.add(agent);
			items.add(item);
		}
	}
	
	public void generateAgentBySelect() {
		MersenneTwisterFast rand = new MersenneTwisterFast(System.currentTimeMillis());
		for (int i=0; i<agentNumber; i++) {
			int agentId = i;
			int itemId = i;		
			double valuation = Setting.BASE_VALUATION + Normal.staticNextDouble(1.0, 0.1);
			int touristCapacity = Setting.TOURIST_CAPCITY[rand.nextInt(Setting.TOURIST_CAPCITY.length)];
			int touristNumber = rand.nextInt(touristCapacity);
			while (touristNumber == touristCapacity) {
				touristNumber = rand.nextInt(touristCapacity);
			}
			int requestTouristNumber = touristCapacity - touristNumber;
			Agent agent = new Agent(agentId,valuation,touristNumber,requestTouristNumber);
			Item item = new SingleItem(itemId,touristNumber,agent);
			agents.add(agent);
			items.add(item);
		}
	}
	public int getItemsNumber() {
		return itemsNumber;
	}
	public void setItemsNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
	}
	public Agent getAgent(int index) {
		return agents.get(index);
	}
	public void setAgent(int index, Agent agent) {
		agents.set(index, agent);
	}	
	public Item getItem(int index) {
		return items.get(index);
	}
	public void setItem(int index, Item item) {
		items.set(index, item);
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
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
		Environment env = new Environment(20,20,typeByRand);
		for (int i=0; i<20; i++) {
			System.out.println(env.getAgent(i));
		}
		for (int i=0; i<20; i++) {
			System.out.println(env.getItem(i));
		}
	}
}