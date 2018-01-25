package com.market.utility;

import java.util.ArrayList;

import com.genetic_algorithm.genetic.Genetic;
import com.market.environment.Agent;
import com.market.environment.Commodity;
import com.market.environment.Environment;
import com.market.statstic.Statistic;

public class UtilityOfMarket {
	public UtilityOfMarket() {}
	
	public static double getAgentFormerUtility(Agent agent) {
		double earnFromTourist = DoubleAndInteger.mul(agent.getValuation(), agent.getTouristNumber());
		double totalCost = UtilityofCost.getTotalCost(agent.getTouristNumber());
		return DoubleUnit.sub(earnFromTourist, totalCost); 
	}
	
	/*
	 * 用于计算种群的的目标值
	 * */
	public static double[] getAllObjValues(ArrayList<Genetic> group,Environment env) {
		int populationNumber = group.size();
		double objValues[] = new double[populationNumber];
		for (int i=0; i<populationNumber; i++) {
			objValues[i] = getSingleObjValue(group.get(i), env);
		}
		return objValues;
	}
	
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
	 * 用于计算单个个体的目标值
	 * */
	public static double getSingleObjValue(Genetic genetic, Environment env) {
		int agentNumber = env.getAgentNumber();
		int getTouristNumber[] = new int[agentNumber];
		for (int i=0; i<agentNumber; i++) {getTouristNumber[i] = 0;}
		int geneticLength = Genetic.geneticLength;
		for (int i=0; i<geneticLength; i++) {
			int agentIndex = genetic.getCode(i);
			getTouristNumber[agentIndex] += env.getCommodity(i).getTouristNumber();
		}
		double fullRates[] = new double[agentNumber];
		double minRate = 2.0;
		for (int i=0; i<agentNumber; i++) {
			if (getTouristNumber[i] == 0) {
				fullRates[i] = 0.0;
			} else {
				if (getTouristNumber[i] <= env.getAgent(i).getTouristCapcity()) {					
					fullRates[i] = DoubleUnit.div(getTouristNumber[i],env.getAgent(i).getTouristCapcity());
					if (fullRates[i] <= minRate) {
						minRate = fullRates[i];
					}
				} else {
					fullRates[i] = 0.0;
				}
			}
		}
		if (minRate > 1.0) {
			minRate = 0.0;
		}
		return minRate;
	}
	
	/*
	 * 用于计算单个个体的适应度
	 * */
	public static double getSingleFitness(Genetic genetic, Environment env) {
		int agentNumber = env.getAgentNumber();
		int getTouristNumber[] = new int[agentNumber];
		for (int i=0; i<agentNumber; i++) {getTouristNumber[i] = 0;}
		int geneticLength = Genetic.geneticLength;
		for (int i=0; i<geneticLength; i++) {
			int agentIndex = genetic.getCode(i);
			getTouristNumber[agentIndex] += env.getCommodity(i).getTouristNumber();
		}
		double fullRates[] = new double[agentNumber];
		double minRate = 2.0;
		for (int i=0; i<agentNumber; i++) {
			if (getTouristNumber[i] == 0) {
				fullRates[i] = 0.0;
			} else {
				if (getTouristNumber[i] <= env.getAgent(i).getTouristCapcity()) {					
					fullRates[i] = DoubleUnit.div(getTouristNumber[i],env.getAgent(i).getTouristCapcity());
				} else {
					int sub = env.getAgent(i).getTouristCapcity() - getTouristNumber[i];
					fullRates[i] = DoubleUnit.div(sub, env.getAgent(i).getTouristCapcity());
				}
				if (fullRates[i] <= minRate) {
					minRate = fullRates[i];
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
