package com.manager.merger;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import com.manager.merger.genetic_algorithm.Adapter;
import com.manager.merger.genetic_algorithm.Crossover;
import com.manager.merger.genetic_algorithm.Fitness;
import com.manager.merger.genetic_algorithm.Generator;
import com.manager.merger.genetic_algorithm.Genetic;
import com.manager.merger.genetic_algorithm.Mutate;
import com.manager.merger.genetic_algorithm.Select;
import com.market.environment.Agent;
import com.market.environment.Item;
import com.market.environment.MergeItem;
import com.market.environment.Environment;
import com.market.setting.Setting;
import com.market.statstic.Statistic;
import com.market.utility.DoubleUnit;
import com.market.utility.Show;

public class Merger {
	//开辟控制的参数
	public final static int typeByNULL = 0;
	public final static int typeByGA = 1;
	public final static int typeByMGA = 2;

	public Merger() {}
	
	public static ArrayList<Item> merge(Environment env,int GAType) throws CloneNotSupportedException {
		//设置遗传参数
		Genetic.geneticLength = env.getItemsNumber();
		Genetic.upperValue = env.getAgentNumber();
		Genetic.lowerValue = 0;
		double GGAP = Setting.GGAP;
		double Pc = Setting.CROSS_PROBILITY;
		double Pm = Setting.MUTATION_PROBILITY;
		Genetic globalBestGenetic = null;
		switch (GAType) {
			case typeByGA:
				globalBestGenetic = mergeByGA(env, GGAP, Pc, Pm);break;
			case typeByMGA:
				globalBestGenetic = mergeByMGA(env, GGAP, Pc, Pm);break;
			case typeByNULL:
				globalBestGenetic = new Genetic(Genetic.typeByNull);break;
		}
		
		//显示遗传算法求解的结果
		double globalBestFitness = Fitness.getSingleFitness(globalBestGenetic, env);
		System.out.printf("最好的基因的适应度%f\n",globalBestFitness);
		Show.showSellerMatchBuyer(globalBestGenetic, env);
		Show.showEnvironment(env);
		
		//利用新的基因得到合成的新的商品
		int agentNumber = env.getAgentNumber();
		int geneticLength = Genetic.geneticLength;
		TreeMap<Integer,ArrayList<Integer> > match = new TreeMap<>();
		
		int getTouristNumber[] = new int[agentNumber];
		
		for (int buyerIndex=0; buyerIndex<agentNumber; buyerIndex++) {
			getTouristNumber[buyerIndex] = 0;
		}
		
		for (int sellerIndex=0; sellerIndex<geneticLength; sellerIndex++) {
			int buyerIndex = globalBestGenetic.getCode(sellerIndex);
			getTouristNumber[buyerIndex] += env.getItem(sellerIndex).getTouristNumber();
			if (!match.containsKey(buyerIndex)) {
				match.put(buyerIndex, new ArrayList<Integer>());
			} 
			match.get(buyerIndex).add(sellerIndex);
		}
		
		ArrayList<Item> mergeItems = new ArrayList<>();
		
		int mergeItemIndex = 0;
		for (int buyerIndex=0; buyerIndex<agentNumber; buyerIndex++) {
			
			if (getTouristNumber[buyerIndex] != 0) {
				
				ArrayList<Integer> sellerIndexs = match.get(buyerIndex);
				if (getTouristNumber[buyerIndex] <= env.getAgent(buyerIndex).getTouristCapcity()) {
					MergeItem mergeItem = new MergeItem(mergeItemIndex++, 
							getTouristNumber[buyerIndex], null);
					
					for (int sellerIndex:sellerIndexs) {
						mergeItem.addSingleItem(env.getItem(sellerIndex).clone());
					}
					mergeItems.add(mergeItem);
				} else {
					for (int sellerIndex:sellerIndexs) {
						MergeItem mergeItem = new MergeItem(mergeItemIndex++, 
								env.getItem(sellerIndex).getTouristNumber(), null);
						mergeItem.addSingleItem(env.getItem(sellerIndex).clone());
						mergeItems.add(mergeItem);
					}
				}
			}
		}
		return mergeItems;
	}
	 
	/*
	 * 使用普通的遗传算法
	 * */
	public static Genetic mergeByGA(Environment env,double GGAP,double Pc,double Pm) throws CloneNotSupportedException {
		Genetic globalBestGenetic = null;
		double globalBestFitness = -10000.0;
		//初始化种群
		ArrayList<Genetic> group = Generator.createPopulation(Setting.POPULATION_NUMBER,Setting.geneticType);
		
		//迭代更新
		for (int gen=1; gen <= Setting.MAX_GENERATION; gen++) {
			
			//计算是适应度
			double fitness[] = Fitness.getAllFitness(group, env);
			
			//得到当前最优的基因标号
			int localBestIdx = Statistic.getValuesMaxIdx(fitness);
			
			if (fitness[localBestIdx] >= globalBestFitness) {
				globalBestGenetic = group.get(localBestIdx);
				globalBestFitness = fitness[localBestIdx];
			}
			
			//输出结果
			if (gen % 100 == 0) {
				Show.showEachGenResult(gen,fitness);
			}
			
			//如果平均值和最大值一样没有优化的必要
			if(fitness[Statistic.getValuesMaxIdx(fitness)] == Statistic.getValuesMean(fitness)) {
				break;
			}
						
			//消除适应度里面的负数
			fitness = Fitness.reduceNegativeFitness(fitness);
			
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
	public static Genetic mergeByMGA(Environment env,double GGAP,double Pc,double Pm) throws CloneNotSupportedException {
		Genetic globalBestGenetic = null;
		double globalBestFitness = -10000.0;
		//初始化种群
		ArrayList<Genetic> group = Generator.createPopulation(Setting.POPULATION_NUMBER,Setting.geneticType);
		
		for (int gen=1; gen<=Setting.MAX_GENERATION; gen++) {
			//求解适应度
			double fitness[] =  Fitness.getAllFitness(group, env);
			
			//求解最优的个体
			int maxIdx = Statistic.getValuesMaxIdx(fitness);
			if (globalBestFitness <= fitness[maxIdx]) {
				globalBestFitness = fitness[maxIdx];
				globalBestGenetic = group.get(maxIdx);
			}
			
			//输出结果
			if (gen % 100 == 0) {
				Show.showEachGenResult(gen,fitness);
			}
			
			//如果平均值和最大值一样没有优化的必要
//			if(fitness[maxIdx] == Statistic.getValuesMean(fitness)) {
//				break;
//			}

			//消除适应度里面的负数
			fitness = Fitness.reduceNegativeFitness(fitness);
			
			//设立新的种群
			ArrayList<Genetic> newGroup = new ArrayList<>(); 
			
			//求解适应度的和
			double totalFitness = Statistic.getValuesSum(fitness);
			
			for (int popIdx = 0; popIdx<Setting.POPULATION_NUMBER; popIdx ++) {				
				//随机选择两个基因
				Genetic genetic1 = Select.selectByRouletteWheel(group, fitness, totalFitness);
				Genetic genetic2 = Select.selectByRouletteWheel(group, fitness, totalFitness);
				//计算基因的的适应度
				double f1 = Fitness.getSingleFitness(genetic1, env);
				double f2 = Fitness.getSingleFitness(genetic2, env);
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
				f1 = Fitness.getSingleFitness(geneticWinner, env);
				f2 = Fitness.getSingleFitness(geneticLoser, env);
				if (f1 > f2) {
					newGroup.add(geneticWinner);
				} else {
					newGroup.add(geneticLoser);
				}
			}
			//更新种群
			group = newGroup;
		}
		return globalBestGenetic;
	}
}
