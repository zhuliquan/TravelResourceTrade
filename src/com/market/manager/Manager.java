package com.market.manager;

import java.util.ArrayList;

import com.genetic_algorithm.genetic.Genetic;
import com.genetic_algorithm.operator.Crossover;
import com.genetic_algorithm.operator.Generator;
import com.genetic_algorithm.operator.Mutate;
import com.genetic_algorithm.operator.Select;
import com.market.environment.Environment;
import com.market.matcher.Matcher;
import com.market.utility.DoubleUnit;
import com.market.utility.Show;
import com.market.utility.UtilityOfMarket;
import com.setting.Setting;

public class Manager {
	public Manager() {}
	public static void main(String[] args) throws CloneNotSupportedException {
		//设置市场的初始化参数
		int agentNumber = Setting.AGENT_NUMBER;
		int commodityNumber = Setting.AGENT_NUMBER;
		Environment env = new Environment(agentNumber, commodityNumber);
		Genetic bestGenetic = Matcher.match(env);
		Show.showEnvironment(env);
//		for (int i=0; i<Setting.AGENT_NUMBER; i++) {
//			System.out.println(DoubleUnit.div(env.getAgent(i).getTouristNumber(), 
//											env.getAgent(i).getTouristCapcity()));
//		}
		Show.showSellerMatchBuyer(bestGenetic, env);
	}
	
//  //更具当前的统一价格决定买卖家
//	public static ArrayList<Agent> determinedType(ArrayList<Agent> agents,double payment){
//	int sellerIdx = 0;
//	int buyerIdx = 0;
//	for (Agent agent:agents) {
//		if (payment > agent.valuation) {
//			agent.isBuyer = false;
//			agent.agentID = sellerIdx;
//			sellerIdx++;
//		} else {
//			agent.isBuyer = true;
//			agent.agentID = buyerIdx;
//			buyerIdx++;
//		}
//	}
//	return agents;
//}
}
