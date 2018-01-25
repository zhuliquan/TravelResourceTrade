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
	 * ʹ����ͨ�ĵ��Ŵ��㷨ȥ�������
	 * */
	public static Genetic match(Environment env) throws CloneNotSupportedException {
		//�����Ŵ�����
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
	 * ʹ����ͨ���Ŵ��㷨
	 * */
	public static Genetic matchByGA(Environment env,double GGAP,double Pc,double Pm) throws CloneNotSupportedException {
		Genetic globalBestGenetic = null;
		double globalBestFitness = -10000.0;
		//��ʼ����Ⱥ
		ArrayList<Genetic> group = Generator.createPopulation(Setting.POPULATION_NUMBER,Setting.geneticType);
		
		//��������
		for (int gen=1; gen <= Setting.MAX_GENERATION; gen++) {
			
			//�����Ⱥ�Ļ����Ŀ��ֵ
//			double fitness[] = UtilityOfMarket.getAllObjValues(group, env);
			
			//��������Ӧ��
			double fitness[] = UtilityOfMarket.getAllFitness(group, env);
			
			//������
			Show.showEachGenResult(gen,fitness);
			
			//�õ���ǰ���ŵĻ�����
			int localBestIdx = Statistic.getValuesMaxIdx(fitness);
			
			if (fitness[localBestIdx] >= globalBestFitness) {
				globalBestGenetic = group.get(localBestIdx);
				globalBestFitness = fitness[localBestIdx];
			}
			
			//���Ƚ�����ȥ��
			fitness = UtilityOfMarket.reduceNegativeFitness(fitness);
			
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
	public static Genetic matchByBGA(Environment env,double GGAP,double Pc,double Pm) throws CloneNotSupportedException {
		Genetic globalBestGenetic = null;
		double globalBestFitness = -10000.0;
		//��ʼ����Ⱥ
		ArrayList<Genetic> group = Generator.createPopulation(Setting.POPULATION_NUMBER,"order");
		
		for (int gen=1; gen<=Setting.MAX_GENERATION; gen++) {
			//�����Ӧ��
			double fitness[] =  UtilityOfMarket.getAllFitness(group, env);
			//�õ����
			Show.showEachGenResult(gen, fitness);
			//������ŵĸ���
			int maxIdx = Statistic.getValuesMaxIdx(fitness);
			if (globalBestFitness <= fitness[maxIdx]) {
				globalBestFitness = fitness[maxIdx];
				globalBestGenetic = group.get(maxIdx);
			}
			//�����µ���Ⱥ
			ArrayList<Genetic> newGroup = new ArrayList<>(); 
			//����Ӧ�ȿ��Ը��õ����ڼ���
			fitness = UtilityOfMarket.reduceNegativeFitness(fitness);
			//�����Ӧ�ȵĺ�
			double totalFitness = Statistic.getValuesSum(fitness);
			
			for (int popIdx = 0; popIdx<Setting.POPULATION_NUMBER; popIdx ++) {				
				//���ѡ����������
				Genetic genetic1 = Select.selectByRouletteWheel(group, fitness, totalFitness).clone();
				Genetic genetic2 = Select.selectByRouletteWheel(group, fitness, totalFitness).clone();
				//�������ĵ���Ӧ��
				double f1 = UtilityOfMarket.getSingleFitness(genetic1, env);
				double f2 = UtilityOfMarket.getSingleFitness(genetic2, env);
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
				f1 = UtilityOfMarket.getSingleFitness(geneticWinner, env);
				f2 = UtilityOfMarket.getSingleFitness(geneticLoser, env);
				if (f1 > f2) {
					newGroup.add(geneticWinner);
				} else {
					newGroup.add(geneticLoser);
				}
			}
			//�ı����Pm��ֵ
			if (gen % 1000 == 0) {
				Pm = Adapter.modifyPm(Pm);
			}
			//������Ⱥ
			group = newGroup;
		}
		return globalBestGenetic;
		
	}
}
