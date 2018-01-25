package com.market.utility;

import java.util.ArrayList;
import java.util.TreeSet;

import com.genetic_algorithm.genetic.Genetic;
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
		int commodityNumber = env.getCommodityNumber();
		System.out.println("当前的环境情况");
		for (int i=0; i<agentNumber; i++) {
			System.out.println(env.getAgent(i));
		}
//		for (int i=0; i<commodityNumber; i++) {
//			System.out.println(env.getCommodity(i));
//		}
	}
	public static void showMatch(Genetic genetic,Environment env) {
		int agentNumber = env.getAgentNumber();
		ArrayList<ArrayList<Integer> > match = new ArrayList<>() ;
		for (int i=0; i<agentNumber; i++) {
			match.add(new ArrayList<>());
		}
		int geneticLength = Genetic.geneticLength;
		int getTouristNumber[] = new int[agentNumber];
		for (int i=0; i<agentNumber; i++) {getTouristNumber[i] = 0;}
		for (int i=0; i<geneticLength; i++) {
			int agentIndex = genetic.getCode(i);
			getTouristNumber[agentIndex] += env.getCommodity(i).getTouristNumber();
			match.get(agentIndex).add(i);
		}
		int commodityNumber = env.getCommodityNumber();
		boolean isAllocated[] = new boolean[commodityNumber];
		for (int i=0; i<agentNumber; i++) {
			if (getTouristNumber[i] != 0) {
				if (getTouristNumber[i] <= env.getAgent(i).getTouristCapcity()) {					
					ArrayList<Integer> cs = match.get(i);
					for (int c:cs) {
						isAllocated[c] = true;
					}
				} else {
					ArrayList<Integer> cs = match.get(i);
					for (int c:cs) {
						isAllocated[c] = false;
					}
				}
			}
		}
		
		System.out.println("商品分配关系");
		for (int i=0; i<commodityNumber; i++) {
			if (isAllocated[i]) {
				System.out.printf("第%d号商品已经分配, 它原来的主人是%d, 它被分配给了%d\n",
						i,env.getCommodity(i).getOnwer().getAgentID(),
						env.getAgent(genetic.getCode(i)).getAgentID());
			} else {
				System.out.printf("第%d号商品没有分配, 它原来的主人是%d\n",
						i,env.getCommodity(i).getOnwer().getAgentID());
			}
		}
//		System.out.println("当前的agent拥有物品情况");
//		for (int i=0; i<agentNumber; i++) {
//			if (getTouristNumber[i] != 0) {
//				if (getTouristNumber[i] <= env.getAgent(i).getTouristCapcity()) {					
//					System.out.printf("第%d个agent 当前拥有%d, 本身的容量%d\n",
//							i,getTouristNumber[i],env.getAgent(i).getTouristCapcity());
//				} else {
//					
//				}
//			} else {
//				
//			}
//		}
	}

	public static void showEnvironment(Genetic bestGenetic, Environment env) {
		int agentNumber = env.getAgentNumber();
		int commodityNumber = env.getCommodityNumber();
		System.out.println("当前的环境情况");
		for (int i=0; i<agentNumber; i++) {
			System.out.println(env.getAgent(i));
		}
//		for (int i=0; i<commodityNumber; i++) {
//			System.out.println(env.getCommodity(i));
//		}
	}
	public static void showSellerMatchBuyer(Genetic genetic,Environment env) {
		int agentNumber = env.getAgentNumber();
		ArrayList<ArrayList<Integer> > match = new ArrayList<>() ;
		for (int i=0; i<agentNumber; i++) {
			match.add(new ArrayList<>());
		}
		int geneticLength = Genetic.geneticLength;
		int getTouristNumber[] = new int[agentNumber];
		for (int i=0; i<agentNumber; i++) {getTouristNumber[i] = 0;}
		for (int i=0; i<geneticLength; i++) {
			int agentIndex = genetic.getCode(i);
			getTouristNumber[agentIndex] += env.getCommodity(i).getTouristNumber();
			match.get(agentIndex).add(i);
		}
		int commodityNumber = env.getCommodityNumber();
		boolean isAllocated[] = new boolean[commodityNumber];
		for (int i=0; i<agentNumber; i++) {
			if (getTouristNumber[i] != 0) {
				if (getTouristNumber[i] <= env.getAgent(i).getTouristCapcity()) {					
					ArrayList<Integer> cs = match.get(i);
					for (int c:cs) {
						isAllocated[c] = true;
					}
				} else {
					ArrayList<Integer> cs = match.get(i);
					for (int c:cs) {
						isAllocated[c] = false;
					}
				}
			}
		}
		
		System.out.println("商品分配关系");
		TreeSet<Integer> sellIdx = new TreeSet<>();
		TreeSet<Integer> buyerIdx = new TreeSet<>();
		for (int i=0; i<commodityNumber; i++) {
			if (isAllocated[i]) {
				System.out.printf("第%d号Agent将卖给了第%d号Agent一共%d名游客\n",
						env.getCommodity(i).getOnwer().getAgentID(),
						env.getAgent(genetic.getCode(i)).getAgentID(),
						env.getCommodity(i).getOnwer().getTouristNumber());
				sellIdx.add(env.getCommodity(i).getOnwer().getAgentID());
				buyerIdx.add(env.getAgent(genetic.getCode(i)).getAgentID());
			} else {
				System.out.printf("第%d号没有卖出自己的商品\n",
						env.getCommodity(i).getOnwer().getAgentID());
			}
		}
		
		System.out.println("卖家和买家");
		System.out.println("卖家");
		System.out.println(sellIdx);
		System.out.println("买家");
		System.out.println(buyerIdx);
		System.out.println("同时是卖家还是买家");
		TreeSet<Integer> interset = new TreeSet<>();
		interset.addAll(sellIdx);
		interset.retainAll(buyerIdx);
		System.out.println(interset);
		
	}

}
