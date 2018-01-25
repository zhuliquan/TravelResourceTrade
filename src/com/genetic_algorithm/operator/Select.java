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
	 * 实现了按照指定的方式去选择个体并返回选择之后的个体
	 * @param ArrayList<Genetic>group 输入的群体
	 * @param double[] fitness        输入群体的适应度
	 * @param String selectType       输入选择的方式   有selectByRouletteWheel这一个类别如果输入是空串那么返回原来的群体
	 * @param double GGAP             输入遗传下一代的数目比例
	 * @return ArrayList<Genetic>     返回选择之后的群体 
	 * */
	public static ArrayList<Genetic> select(ArrayList<Genetic> group, double fitness[],String selectType,double GGAP) throws CloneNotSupportedException  {
		//在进行选择
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
	 * 按照当前的种群和适应度以及适应度的总和轮盘算法选出一个个体出来
	 * @param ArrayList<Genetic> group 输入的种群
	 * @param double[] fitness         输入的适应度
	 * @param double[] totalFitness    输入适应度的和
	 * @return Genetic 返回一个选择的基因
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
		
		//选择之前
		System.out.println("选择之前");
		for (int i=0; i<group.size(); i++) {
			System.out.println(fitness[i]);
		}
		fitness = UtilityOfMarket.reduceNegativeFitness(fitness);
		try {
			group = select(group, fitness, "selectByRouletteWheel", 1.0);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		//选择之后
		System.out.println("选择之后");
		fitness = UtilityOfMarket.getAllFitness(group, env);
		for (int i=0; i<group.size(); i++) {
			System.out.println(fitness[i]);
		}
	}
}
