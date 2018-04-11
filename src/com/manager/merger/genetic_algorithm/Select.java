package com.manager.merger.genetic_algorithm;

import java.util.ArrayList;

import com.market.environment.Environment;
import com.market.statstic.Statistic;
import com.market.utility.DoubleAndInteger;
import com.market.utility.DoubleUnit;
import com.market.utility.MersenneTwisterFast;

public class Select {
	public final static int typeByNull = 0;
	public final static int typeByRouletteWheel = 1;
	public Select() {}

	/**
	 * ʵ���˰���ָ���ķ�ʽȥѡ����岢����ѡ��֮��ĸ���
	 * @param ArrayList<Genetic>group �����Ⱥ��
	 * @param double[] fitness        ����Ⱥ�����Ӧ��
	 * @param String selectType       ����ѡ��ķ�ʽ   ��selectByRouletteWheel��һ�������������ǿմ���ô����ԭ����Ⱥ��
	 * @param double GGAP             �����Ŵ���һ������Ŀ����
	 * @return ArrayList<Genetic>     ����ѡ��֮���Ⱥ�� 
	 * */
	public static ArrayList<Genetic> select(ArrayList<Genetic> group, double fitness[],int selectType,double GGAP) throws CloneNotSupportedException  {
		//�ڽ���ѡ��
		ArrayList<Genetic> selectGroup = new ArrayList<>();
		int populationNumber = group.size();
		int selectNumber = (int)(DoubleAndInteger.mul(GGAP, populationNumber));
		if (selectType == typeByRouletteWheel) {
			double totalFitness = Statistic.getValuesSum(fitness);
			double accumlateFitness[] = Statistic.accumlate(fitness);
			for (int k=0; k<selectNumber; k++) {
				selectGroup.add(selectByRouletteWheel(group, accumlateFitness,totalFitness));
			}
		} else if (selectType == typeByNull) {
			selectGroup = group;
		}
		return selectGroup;
	}
	/**
	 * ���յ�ǰ����Ⱥ����Ӧ���Լ���Ӧ�ȵ��ܺ������㷨ѡ��һ���������
	 * @param ArrayList<Genetic> group �������Ⱥ
	 * @param double[] accumlateFitness������ۼ���Ӧ��
	 * @param double[] totalFitness    ������Ӧ�ȵĺ�
	 * @return Genetic ����һ��ѡ��Ļ���
	 * */
	public static Genetic selectByRouletteWheel(ArrayList<Genetic> group, double accumlateFitness[],double totalFitness)
			throws CloneNotSupportedException{
		
		double pickFitness = DoubleUnit.mul(Math.random(), totalFitness);
		//���ö��ַ�����
		int populationNumber = group.size();
		int selectIdx = 0;
		int lIdx = 0, rIdx = populationNumber-1;
		while (lIdx < rIdx) {
			int mIdx = (lIdx+rIdx)/2;
			if (accumlateFitness[mIdx] > pickFitness) {
				rIdx = mIdx;
			} else {
				lIdx = mIdx+1;
			}
		}
		selectIdx = lIdx;
		return group.get(selectIdx).clone();
	}

	public static void main(String[] args) {
		//���ڲ��Ե�
		Environment env = new Environment(20, 20, Environment.typeByRand);
		Genetic.geneticLength = 20;
		Genetic.upperValue = 20;
		ArrayList<Genetic> group = Generator.createPopulation(100,Genetic.typeByRandom);
		group = Generator.shuffleGroup(group);
		double fitness[] = Fitness.getAllFitness(group, env);
		
		//ѡ��֮ǰ
		System.out.println("ѡ��֮ǰ");
		for (int i=0; i<group.size(); i++) {
			System.out.println(fitness[i]);
		}
		fitness = Fitness.reduceNegativeFitness(fitness);
		
		System.out.println("��������֮��");
		for (int i=0; i<group.size(); i++) {
			System.out.println(fitness[i]);
		}
		try {
			group = select(group, fitness, typeByRouletteWheel, 1.0);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		//ѡ��֮��
		System.out.println("ѡ��֮��");
		fitness = Fitness.getAllFitness(group, env);
		for (int i=0; i<group.size(); i++) {
			System.out.println(fitness[i]);
		}
	}
}
