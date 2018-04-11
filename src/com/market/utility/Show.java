package com.market.utility;

import java.util.ArrayList;
import java.util.TreeSet;

import com.manager.merger.genetic_algorithm.Genetic;
import com.market.environment.Environment;
import com.market.statstic.Statistic;

public class Show {

	public Show() {}
	
	public static void showEachGenResult(int gen,double objValues[]) {
		double maxObj = objValues[Statistic.getValuesMaxIdx(objValues)];
		double meanObj = Statistic.getValuesMean(objValues);
		System.out.printf("第%d迭代的最大值%f 平均值%f\n",gen,maxObj,meanObj);
	}

	public static void showEnvironment(Environment env) {
		int agentNumber = env.getAgentNumber();
		int itemsNumber = env.getItemsNumber();
		System.out.println("当前的环境情况");
		for (int i=0; i<agentNumber; i++) {
			System.out.println(env.getAgent(i));
		}
		for (int i=0; i<itemsNumber; i++) {
			System.out.println(env.getItem(i));
		}
	}
	public static void showSellerMatchBuyer(Genetic genetic,Environment env) {
		int agentNumber = env.getAgentNumber();
		ArrayList<ArrayList<Integer> > match = new ArrayList<>() ;
		for (int i=0; i<agentNumber; i++) {
			match.add(new ArrayList<>());
		}
		int geneticLength = Genetic.geneticLength;
		int getTouristNumber[] = new int[agentNumber];
		for (int buyerIndex=0; buyerIndex<agentNumber; buyerIndex++) {
			getTouristNumber[buyerIndex] = 0;
		}
		
		for (int sellerIndex=0; sellerIndex<geneticLength; sellerIndex++) {
			int buyerIndex = genetic.getCode(sellerIndex);
			getTouristNumber[buyerIndex] += env.getItem(sellerIndex).getTouristNumber();
			match.get(buyerIndex).add(sellerIndex);
		}
		int itemsNumber = env.getItemsNumber();
		boolean isAllocated[] = new boolean[itemsNumber];
		
		for (int buyerIndex=0; buyerIndex<agentNumber; buyerIndex++) {
			
			if (getTouristNumber[buyerIndex] != 0) {
				ArrayList<Integer> sellerIndexs = match.get(buyerIndex);
				if (getTouristNumber[buyerIndex] <= env.getAgent(buyerIndex).getTouristCapcity()) {					
					for (int sellerIndex:sellerIndexs) {
						isAllocated[sellerIndex] = true;
					}
				} else {
					for (int sellerIndex:sellerIndexs) {
						isAllocated[sellerIndex] = false;
					}
				}
			}
		}
		
		System.out.println("商品分配关系:");
		TreeSet<Integer> sellerIndexs = new TreeSet<>();
		TreeSet<Integer> buyerIndexs = new TreeSet<>();
		for (int itemIndex=0; itemIndex<itemsNumber; itemIndex++) {
			if (isAllocated[itemIndex]) {
				System.out.printf("第%d号Agent将卖给了第%d号Agent一共%d名游客\n",
						env.getItem(itemIndex).getOnwer().getAgentID(),
						env.getAgent(genetic.getCode(itemIndex)).getAgentID(),
						env.getItem(itemIndex).getOnwer().getTouristNumber());
				sellerIndexs.add(env.getItem(itemIndex).getOnwer().getAgentID());
				buyerIndexs.add(env.getAgent(genetic.getCode(itemIndex)).getAgentID());
			} else {
				System.out.printf("第%d号没有卖出自己的商品\n",
						env.getItem(itemIndex).getOnwer().getAgentID());
			}
		}
		
//		System.out.println("卖家和买家");
//		System.out.println("卖家");
//		System.out.println(sellerIndexs);
//		System.out.println("买家");
//		System.out.println(buyerIndexs);
//		System.out.println("同时是卖家还是买家");
//		TreeSet<Integer> interset = new TreeSet<>();
//		interset.addAll(sellerIndexs);
//		interset.retainAll(buyerIndexs);
//		System.out.println(interset);
		
	}

}
