package com.genetic_algorithm.operator;
import java.util.ArrayList;

import com.genetic_algorithm.genetic.Genetic;
import com.market.utility.MersenneTwisterFast;
import com.setting.Setting;
public class Crossover {

	public Crossover() {}

	/**
	 * 实现了按照指定的方式去选择个体进行交叉
	 * @param ArrayList<Genetic>group 输入的群体
	 * @param String crossType        输入选择的方式   有swapOnePoint,swapTwoPoint两种，如果输入是空串那么返回原来的群体
	 * @param double Pc               输入交叉的概率
	 * @return ArrayList<Genetic>     返回进行交叉的种群
	 * */
	public static ArrayList<Genetic> cross(ArrayList<Genetic> group,String crossType,double Pc){
		if (crossType.equals("") || Genetic.geneticLength <= 2 || group.size() == 0 ) {
			return group;
		}
		
		int populationNumber = group.size();
		if (populationNumber % 2 == 1) {
			populationNumber = populationNumber - 1;
		}
		for (int i=0; i<populationNumber; i+=2) {
			if (Math.random() > Pc) {
				continue;
			}
			Genetic genetic1 = group.get(i);
			Genetic genetic2 = group.get(i+1);
			if (crossType.equals("swapOnePoint")) {
				swapOnePoint(genetic1, genetic2);
			} else if (crossType.equals("swapTwoPoint")) {
				swapTwoPoint(genetic1, genetic2);
			}
		}
		return group;
	}
	
	/**
	 * 两个基因按照单点交叉方式交叉
	 * @param Genetic genetic1  第一个基因
	 * @param Genetic genetic2 第二个基因
	 * */
	public static void swapOnePoint(Genetic genetic1,Genetic genetic2) {
		int p1 = (int)Math.floor(Math.random() * Genetic.geneticLength);
		for (int i=0; i<=p1; i++) {
			int t = genetic1.getCode(p1);
			genetic1.setCode(p1, genetic2.getCode(p1));
			genetic2.setCode(p1,t);
		}
	}
	
	/**
	 * 两个基因按照双点交叉方式交叉
	 * @param Genetic genetic1  第一个基因
	 * @param Genetic genetic2 第二个基因
	 * */
	public static void swapTwoPoint(Genetic genetic1,Genetic genetic2) {
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
		for (int p=p1; p<=p2; p++) {
			int t = genetic1.getCode(p);
			genetic1.setCode(p, genetic2.getCode(p));
			genetic2.setCode(p,t);
		}
	}
	
	/**
	 * 保护优秀基因的提取部分胜利者基因到差一点的基因
	 * @param Genetic geneticWinner 好一点的基因
	 * @param Genetic geneticLoser  差一点的基因
	 * */
	public static void winnerSave(Genetic geneticWinner,Genetic geneticLoser) {
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
		for (int p=p1; p<=p2; p++) {
			geneticLoser.setCode(p, geneticWinner.getCode(p));
		}
	}
	
	public static void main(String[] args) {
		Genetic.geneticLength = 20;
		Genetic.upperValue = 20;
		int size = 20;
		ArrayList<Genetic> group = Generator.createPopulation(size,"order");
		group = Generator.shuffleGroup(group);
		//没有进行变异前
		System.out.println("没有发生交叉");
		for (int i=0; i<size; i++) {
			System.out.println(group.get(i));
		}
		group = cross(group, "swapOnePoint", Setting.CROSS_PROBILITY);
		//进行变异后
		System.out.println("发生交叉");
		for (int i=0; i<size; i++) {
			System.out.println(group.get(i));
		}
	}
}
