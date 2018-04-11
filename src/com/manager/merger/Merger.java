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
	//���ٿ��ƵĲ���
	public final static int typeByNULL = 0;
	public final static int typeByGA = 1;
	public final static int typeByMGA = 2;

	public Merger() {}
	
	public static ArrayList<Item> merge(Environment env,int GAType) throws CloneNotSupportedException {
		//�����Ŵ�����
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
		
		//��ʾ�Ŵ��㷨���Ľ��
		double globalBestFitness = Fitness.getSingleFitness(globalBestGenetic, env);
		System.out.printf("��õĻ������Ӧ��%f\n",globalBestFitness);
		Show.showSellerMatchBuyer(globalBestGenetic, env);
		Show.showEnvironment(env);
		
		//�����µĻ���õ��ϳɵ��µ���Ʒ
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
	 * ʹ����ͨ���Ŵ��㷨
	 * */
	public static Genetic mergeByGA(Environment env,double GGAP,double Pc,double Pm) throws CloneNotSupportedException {
		Genetic globalBestGenetic = null;
		double globalBestFitness = -10000.0;
		//��ʼ����Ⱥ
		ArrayList<Genetic> group = Generator.createPopulation(Setting.POPULATION_NUMBER,Setting.geneticType);
		
		//��������
		for (int gen=1; gen <= Setting.MAX_GENERATION; gen++) {
			
			//��������Ӧ��
			double fitness[] = Fitness.getAllFitness(group, env);
			
			//�õ���ǰ���ŵĻ�����
			int localBestIdx = Statistic.getValuesMaxIdx(fitness);
			
			if (fitness[localBestIdx] >= globalBestFitness) {
				globalBestGenetic = group.get(localBestIdx);
				globalBestFitness = fitness[localBestIdx];
			}
			
			//������
			if (gen % 100 == 0) {
				Show.showEachGenResult(gen,fitness);
			}
			
			//���ƽ��ֵ�����ֵһ��û���Ż��ı�Ҫ
			if(fitness[Statistic.getValuesMaxIdx(fitness)] == Statistic.getValuesMean(fitness)) {
				break;
			}
						
			//������Ӧ������ĸ���
			fitness = Fitness.reduceNegativeFitness(fitness);
			
			//��ѡ���ŵĻ���
			ArrayList<Genetic> selectedGroup = Select.select(group, fitness, Setting.selectType, GGAP);
			
			//���ŵĻ��򽻲�
			selectedGroup = Crossover.cross(selectedGroup, Setting.crossType, Pc);
			
			//���ŵĻ������
			selectedGroup = Mutate.mutate(selectedGroup, Setting.mutateType1, Pm);
			
			selectedGroup = Mutate.mutate(selectedGroup, Setting.mutateType2, Pm);	
			
			//�ı����Pm��ֵ
			if (gen % 1000 == 0) {
				Pm = Adapter.modifyPm(Pm);
			}
			
			//�µ���Ⱥ��̭�ϵ�Ⱥ��
			group = selectedGroup;
		}
		return globalBestGenetic;
	}

	/*
	 * ʹ��Microbial Genetic Algorithm�������
	 * */
	public static Genetic mergeByMGA(Environment env,double GGAP,double Pc,double Pm) throws CloneNotSupportedException {
		Genetic globalBestGenetic = null;
		double globalBestFitness = -10000.0;
		//��ʼ����Ⱥ
		ArrayList<Genetic> group = Generator.createPopulation(Setting.POPULATION_NUMBER,Setting.geneticType);
		
		for (int gen=1; gen<=Setting.MAX_GENERATION; gen++) {
			//�����Ӧ��
			double fitness[] =  Fitness.getAllFitness(group, env);
			
			//������ŵĸ���
			int maxIdx = Statistic.getValuesMaxIdx(fitness);
			if (globalBestFitness <= fitness[maxIdx]) {
				globalBestFitness = fitness[maxIdx];
				globalBestGenetic = group.get(maxIdx);
			}
			
			//������
			if (gen % 100 == 0) {
				Show.showEachGenResult(gen,fitness);
			}
			
			//���ƽ��ֵ�����ֵһ��û���Ż��ı�Ҫ
//			if(fitness[maxIdx] == Statistic.getValuesMean(fitness)) {
//				break;
//			}

			//������Ӧ������ĸ���
			fitness = Fitness.reduceNegativeFitness(fitness);
			
			//�����µ���Ⱥ
			ArrayList<Genetic> newGroup = new ArrayList<>(); 
			
			//�����Ӧ�ȵĺ�
			double totalFitness = Statistic.getValuesSum(fitness);
			
			for (int popIdx = 0; popIdx<Setting.POPULATION_NUMBER; popIdx ++) {				
				//���ѡ����������
				Genetic genetic1 = Select.selectByRouletteWheel(group, fitness, totalFitness);
				Genetic genetic2 = Select.selectByRouletteWheel(group, fitness, totalFitness);
				//�������ĵ���Ӧ��
				double f1 = Fitness.getSingleFitness(genetic1, env);
				double f2 = Fitness.getSingleFitness(genetic2, env);
				Genetic geneticWinner = genetic1,geneticLoser = genetic2;
				if (f1 < f2) {
					geneticWinner = genetic2;
					geneticLoser  = genetic1;
				}
				//����
				if (Math.random() < Pc) {
					Crossover.winnerSave(geneticWinner, geneticLoser);
				}
				//����
				if (Math.random() < Pm) {
					Mutate.changeOnePoint(geneticLoser);
				}
				//���뵽�µ�Ⱥ��
				f1 = Fitness.getSingleFitness(geneticWinner, env);
				f2 = Fitness.getSingleFitness(geneticLoser, env);
				if (f1 > f2) {
					newGroup.add(geneticWinner);
				} else {
					newGroup.add(geneticLoser);
				}
			}
			//������Ⱥ
			group = newGroup;
		}
		return globalBestGenetic;
	}
}
