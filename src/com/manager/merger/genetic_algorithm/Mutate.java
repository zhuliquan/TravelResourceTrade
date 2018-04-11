package com.manager.merger.genetic_algorithm;

import java.util.ArrayList;

import com.market.setting.Setting;
import com.market.utility.MersenneTwisterFast;

public class Mutate {
	public final static int typeByNull = 0;
	public final static int typeByChangeOnePoint = 1;
	public final static int typeBySwapTwoPoint = 2;
	public Mutate() {}
	
	/**
	 * 实现了按照指定的方式去选择个体进行变异
	 * @param ArrayList<Genetic>group 输入的群体
	 * @param String mutateType       输入选择的方式   有changeOnePoint,swapTwoPoint两种，如果输入是空串那么返回原来的群体
	 * @param double Pm               输入变异的概率
	 * @return ArrayList<Genetic>     返回进行变异的种群
	 * */
	public static ArrayList<Genetic> mutate(ArrayList<Genetic> group,int mutateType, double Pm){		
		if ((mutateType == typeByNull)||
			(mutateType == typeByChangeOnePoint && (Genetic.upperValue - Genetic.lowerValue <= 1))||
			(mutateType == typeBySwapTwoPoint && (Genetic.geneticLength <= 1)) || group.size() == 0) {
			return group;
		}
		
		for (Genetic genetic:group) {
			if (Math.random() > Pm) {
				continue;
			}
			switch (mutateType) {
				case typeByChangeOnePoint:
					changeOnePoint(genetic);break;
				case typeBySwapTwoPoint:
					swapTwoPoint(genetic);break;
			}
		}		
		return group;
	}
	
	/**
	 * 单点改变新基因
	 * @param Genetic genetic 需要变异的基因
	 * */
	public static void changeOnePoint(Genetic genetic) {
		int p = (int)Math.floor(Math.random() * Genetic.geneticLength);
		int oldValue = genetic.getCode(p);
		int newValue = (int)Math.floor(Math.random() * Genetic.upperValue) - Genetic.lowerValue;
		while (oldValue == newValue) {
			newValue = (int)Math.floor(Math.random() * Genetic.upperValue) - Genetic.lowerValue;
		}
		genetic.setCode(p, newValue);
	}
	
	/**
	 * 交换两个点的基因
	 * @param Genetic genetic 需要变异的基因
	 * */
	public static void swapTwoPoint(Genetic genetic) {
		int p1 = (int)Math.floor(Math.random() * Genetic.geneticLength);
		int p2 = (int)Math.floor(Math.random() * Genetic.geneticLength);
		while (p1 == p2) {
			p2 = (int)Math.floor(Math.random() * Genetic.geneticLength);
		}
		if (p1 > p2) {
			int t = p1;
			p1 = p2;
			p2 = t;
		}
		int t = genetic.getCode(p1);
		genetic.setCode(p1, genetic.getCode(p2));
		genetic.setCode(p2, t);
	}
	public static void main(String[] args) {
		Genetic.geneticLength = 20;
		Genetic.upperValue = 20;
		int size = 20;
		ArrayList<Genetic> group = Generator.createPopulation(size,Genetic.typeByRandom);
		//没有进行变异前
		System.out.println("没有发生变异");
		for (int i=0; i<size; i++) {
			System.out.println(group.get(i));
		}
		group = mutate(group, typeBySwapTwoPoint, Setting.MUTATION_PROBILITY);
		group = mutate(group, typeByChangeOnePoint, Setting.MUTATION_PROBILITY);
		//进行变异后
		System.out.println("发生变异");
		for (int i=0; i<size; i++) {
			System.out.println(group.get(i));
		}
		
	}
}
