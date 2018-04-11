package com.manager.merger.genetic_algorithm;

import java.util.ArrayList;

import com.market.environment.Environment;
import com.market.statstic.Statistic;
import com.market.utility.DoubleUnit;

public class Fitness {	
	/*
	 * 用于计算种群的适应度
	 * */
	public static double[] getAllFitness(ArrayList<Genetic> group,Environment env) {
		int populationNumber = group.size();
		double fitness[] = new double[populationNumber];
		for (int i=0; i<populationNumber; i++) {
			fitness[i] = getSingleFitness(group.get(i), env);
		}
		return fitness;
	}
	
	/*
	 * 用于计算单个个体的适应度
	 * */
	public static double getSingleFitness(Genetic genetic, Environment env) {
		
		int agentNumber = env.getAgentNumber();
		int getTouristNumber[] = new int[agentNumber];
		
		for (int buyerIndex=0; buyerIndex<agentNumber; buyerIndex++) {
			getTouristNumber[buyerIndex] = 0;
		}
		
		int geneticLength = Genetic.geneticLength;
		for (int i=0; i<geneticLength; i++) {
			int agentIndex = genetic.getCode(i);
			getTouristNumber[agentIndex] += env.getItem(i).getTouristNumber();
		}
		double minRate = 2.0;
 
//		for (int buyerIndex=0; buyerIndex<agentNumber; buyerIndex++) {
//			double tmpRate = 0.0;
//			if (getTouristNumber[buyerIndex] != 0) {
//				if (getTouristNumber[buyerIndex] <= env.getAgent(buyerIndex).getTouristCapcity()) {					
//					tmpRate = DoubleUnit.div(getTouristNumber[buyerIndex],
//							env.getAgent(buyerIndex).getTouristCapcity());
//					if (tmpRate <= minRate) {
//						minRate = tmpRate;
//					}
//				} 
//			}
//		}
		for (int buyerIndex=0; buyerIndex<agentNumber; buyerIndex++) {
			double tmpRate = 0.0;
			if (getTouristNumber[buyerIndex] != 0) {
				if (getTouristNumber[buyerIndex] <= env.getAgent(buyerIndex).getTouristCapcity()) {					
					tmpRate = DoubleUnit.div(getTouristNumber[buyerIndex],
							env.getAgent(buyerIndex).getTouristCapcity());
				} else {
					int sub = env.getAgent(buyerIndex).getTouristCapcity() - getTouristNumber[buyerIndex];
					tmpRate = DoubleUnit.div(sub, env.getAgent(buyerIndex).getTouristCapcity());
				}
				if (tmpRate <= minRate) {
					minRate = tmpRate;
				}
			}
			
		}
		if (minRate > 1.0) {
			minRate = 0.0;
		}
		return minRate;
	}
	
	/*
	 * 用于消除适应度中的负数
	 * */
	public static double[] reduceNegativeFitness(double fitness[]){
		int populationNumber = fitness.length;
		double minFitness = fitness[Statistic.getValuesMinIdx(fitness)];
		for (int i=0; i<populationNumber; i++) {
			fitness[i] = DoubleUnit.sub(fitness[i],minFitness);
		}
		return fitness;
	}

}
