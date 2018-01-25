package com.genetic_algorithm.operator;

import java.util.ArrayList;

import com.genetic_algorithm.genetic.Genetic;
import com.market.environment.Environment;
import com.market.statstic.Statistic;
import com.market.utility.DoubleAndInteger;
import com.market.utility.DoubleUnit;
import com.market.utility.MersenneTwisterFast;
import com.market.utility.UtilityOfMarket;

public class Select {

	public Select() {}

	/**
	 * ʵ���˰���ָ���ķ�ʽȥѡ����岢����ѡ��֮��ĸ���
	 * @param ArrayList<Genetic>group �����Ⱥ��
	 * @param double[] fitness        ����Ⱥ�����Ӧ��
	 * @param String selectType       ����ѡ��ķ�ʽ   ��selectByRouletteWheel��һ�������������ǿմ���ô����ԭ����Ⱥ��
	 * @param double GGAP             �����Ŵ���һ������Ŀ����
	 * @return ArrayList<Genetic>     ����ѡ��֮���Ⱥ�� 
	 * */
	public static ArrayList<Genetic> select(ArrayList<Genetic> group, double fitness[],String selectType,double GGAP) throws CloneNotSupportedException  {
		//�ڽ���ѡ��
		ArrayList<Genetic> selectGroup = new ArrayList<>();
		int populationNumber = group.size();
		int selectNumber = (int)(DoubleAndInteger.mul(GGAP, populationNumber));
		if (selectType.equals("selectByRouletteWheel")) {
			double totalFitness = Statistic.getValuesSum(fitness);
//			double averageFitness = Statistic.getValuesMean(fitness);
			for (int k=0; k<selectNumber; k++) {
				selectGroup.add(selectByRouletteWheel(group, fitness,totalFitness));
			}
		} else if (selectType.equals("")) {
			selectGroup = group;
		}
		return selectGroup;
	}
	/**
	 * ���յ�ǰ����Ⱥ����Ӧ���Լ���Ӧ�ȵ��ܺ������㷨ѡ��һ���������
	 * @param ArrayList<Genetic> group �������Ⱥ
	 * @param double[] fitness         �������Ӧ��
	 * @param double[] totalFitness    ������Ӧ�ȵĺ�
	 * @return Genetic ����һ��ѡ��Ļ���
	 * */
	public static Genetic selectByRouletteWheel(ArrayList<Genetic> group, double fitness[],double totalFitness)
			throws CloneNotSupportedException{
		
		double pickFitness = DoubleUnit.mul(Math.random(), totalFitness);
		double particalFitness = 0.0;
		int populationNumber = group.size();
		int selectIdx = 0;
		for (int i=0; i<populationNumber; i++) {
			particalFitness = DoubleUnit.add(particalFitness, fitness[i]);
			if (particalFitness >= pickFitness) {
				selectIdx = i;
				break;
			}
		}
		return group.get(selectIdx).clone();
	}

	public static void main(String[] args) {
		Environment env = new Environment(20, 20);
		Genetic.geneticLength = 20;
		Genetic.upperValue = 20;
		ArrayList<Genetic> group = Generator.createPopulation(100,"order");
		group = Generator.shuffleGroup(group);
		double fitness[] = UtilityOfMarket.getAllFitness(group, env);
		
		//ѡ��֮ǰ
		System.out.println("ѡ��֮ǰ");
		for (int i=0; i<group.size(); i++) {
			System.out.println(fitness[i]);
		}
		fitness = UtilityOfMarket.reduceNegativeFitness(fitness);
		try {
			group = select(group, fitness, "selectByRouletteWheel", 1.0);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		//ѡ��֮��
		System.out.println("ѡ��֮��");
		fitness = UtilityOfMarket.getAllFitness(group, env);
		for (int i=0; i<group.size(); i++) {
			System.out.println(fitness[i]);
		}
	}
}
