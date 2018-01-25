package com.market.matcher;

import java.util.ArrayList;

import com.genetic_algorithm.genetic.Genetic;
import com.genetic_algorithm.operator.Adapter;
import com.genetic_algorithm.operator.Crossover;
import com.genetic_algorithm.operator.Generator;
import com.genetic_algorithm.operator.Mutate;
import com.genetic_algorithm.operator.Select;
import com.market.environment.Agent;
import com.market.environment.Environment;
import com.market.statstic.Statistic;
import com.market.utility.Show;
import com.market.utility.UtilityOfMarket;
import com.setting.Setting;

public class Matcher {
	public Matcher() {}
	
	/*
	 * 使用普通的的遗传算法去求解问题
	 * */
	public static Genetic match(Environment env) throws CloneNotSupportedException {
		//设置遗传参数
		Genetic.geneticLength = env.getCommodityNumber();
		Genetic.upperValue = env.getAgentNumber();
		Genetic.lowerValue = 0;
		double GGAP = Setting.GGAP;
		double Pc = Setting.CROSS_PROBILITY;
		double Pm = Setting.MUTATION_PROBILITY;
		String GAType = Setting.GAType;
		Genetic globalBestGenetic = null;
		if (GAType.equals("GA")) {
			globalBestGenetic = matchByGA(env, GGAP, Pc, Pm);
		} else if (GAType.equals("MGA")) {
			globalBestGenetic = matchByBGA(env,GGAP,Pc, Pm);
		} 
		return globalBestGenetic;

	}
	 
	/*
	 * 使用普通的遗传算法
	 * */
	public static Genetic matchByGA(Environment env,double GGAP,double Pc,double Pm) throws CloneNotSupportedException {
		Genetic globalBestGenetic = null;
		double globalBestFitness = -10000.0;
		//初始化种群
		ArrayList<Genetic> group = Generator.createPopulation(Setting.POPULATION_NUMBER,Setting.geneticType);
		
		//迭代更新
		for (int gen=1; gen <= Setting.MAX_GENERATION; gen++) {
			
			//获得种群的基因的目标值
//			double fitness[] = UtilityOfMarket.getAllObjValues(group, env);
			
			//计算是适应度
			double fitness[] = UtilityOfMarket.getAllFitness(group, env);
			
			//输出结果
			Show.showEachGenResult(gen,fitness);
			
			//得到当前最优的基因标号
			int localBestIdx = Statistic.getValuesMaxIdx(fitness);
			
			if (fitness[localBestIdx] >= globalBestFitness) {
				globalBestGenetic = group.get(localBestIdx);
				globalBestFitness = fitness[localBestIdx];
			}
			
			//首先将负数去除
			fitness = UtilityOfMarket.reduceNegativeFitness(fitness);
			
			//挑选最优的基因
			ArrayList<Genetic> selectedGroup = Select.select(group, fitness, Setting.selectType, GGAP);
			
			//最优的基因交叉
			selectedGroup = Crossover.cross(selectedGroup, Setting.crossType, Pc);
			
			//最优的基因变异
			selectedGroup = Mutate.mutate(selectedGroup, Setting.mutateType1, Pm);
			
			selectedGroup = Mutate.mutate(selectedGroup, Setting.mutateType2, Pm);	
			
			//改变基因Pm的值
			if (gen % 1000 == 0) {
				Pm = Adapter.modifyPm(Pm);
			}
			
			//新的总群淘汰老的群体
			group = selectedGroup;
		}
		return globalBestGenetic;
	}

	/*
	 * 使用Microbial Genetic Algorithm求解问题
	 * */
	public static Genetic matchByBGA(Environment env,double GGAP,double Pc,double Pm) throws CloneNotSupportedException {
		Genetic globalBestGenetic = null;
		double globalBestFitness = -10000.0;
		//初始化种群
		ArrayList<Genetic> group = Generator.createPopulation(Setting.POPULATION_NUMBER,"order");
		
		for (int gen=1; gen<=Setting.MAX_GENERATION; gen++) {
			//求解适应度
			double fitness[] =  UtilityOfMarket.getAllFitness(group, env);
			//得到结果
			Show.showEachGenResult(gen, fitness);
			//求解最优的个体
			int maxIdx = Statistic.getValuesMaxIdx(fitness);
			if (globalBestFitness <= fitness[maxIdx]) {
				globalBestFitness = fitness[maxIdx];
				globalBestGenetic = group.get(maxIdx);
			}
			//设立新的种群
			ArrayList<Genetic> newGroup = new ArrayList<>(); 
			//让适应度可以更好的用于计算
			fitness = UtilityOfMarket.reduceNegativeFitness(fitness);
			//求解适应度的和
			double totalFitness = Statistic.getValuesSum(fitness);
			
			for (int popIdx = 0; popIdx<Setting.POPULATION_NUMBER; popIdx ++) {				
				//随机选择两个基因
				Genetic genetic1 = Select.selectByRouletteWheel(group, fitness, totalFitness).clone();
				Genetic genetic2 = Select.selectByRouletteWheel(group, fitness, totalFitness).clone();
				//计算基因的的适应度
				double f1 = UtilityOfMarket.getSingleFitness(genetic1, env);
				double f2 = UtilityOfMarket.getSingleFitness(genetic2, env);
				Genetic geneticWinner = genetic1,geneticLoser = genetic2;
				if (f1 < f2) {
					geneticWinner = genetic2;
					geneticLoser  = genetic1;
				}
				//交叉
				if (Math.random() < Pc) {
					Crossover.winnerSave(geneticWinner, geneticLoser);
				}
				//变异
				if (Math.random() < Pm) {
					Mutate.changeOnePoint(geneticLoser);
				}
				//插入到新的群体
				f1 = UtilityOfMarket.getSingleFitness(geneticWinner, env);
				f2 = UtilityOfMarket.getSingleFitness(geneticLoser, env);
				if (f1 > f2) {
					newGroup.add(geneticWinner);
				} else {
					newGroup.add(geneticLoser);
				}
			}
			//改变基因Pm的值
			if (gen % 1000 == 0) {
				Pm = Adapter.modifyPm(Pm);
			}
			//更新种群
			group = newGroup;
		}
		return globalBestGenetic;
		
	}
}
