package com.market.setting;

import com.manager.merger.Merger;
import com.manager.merger.genetic_algorithm.Crossover;
import com.manager.merger.genetic_algorithm.Genetic;
import com.manager.merger.genetic_algorithm.Mutate;
import com.manager.merger.genetic_algorithm.Select;
import com.market.environment.Environment;

public class Setting {
	
	/*
	 * 市场相关参数
	 * */
	//envType市场生成agent的形式 BySelect是离散容积形式进行随机选择
	//如果是ByRand是真的完全随机根据TOURIST_NUMBER,REQUEST_TOURISTNUMBER随机生成
	public static int ENVIRONMENT_GENERATE_TYPE = Environment.typeBySelect;
	public static int AGENT_NUMBER = 50;          //agent的数目
	public static int ITEMS_NUMBER = 50;          //商品数目
	public static int TOURIST_NUMBER = 30;        //游客数目的上限
	public static int REQUEST_TOURISTNUMBER = 10; //需求游客数目的上限
	public static int[] TOURIST_CAPCITY = new  int[] {
			10,15,20,25,30,35,40,45,50,55,
			60,65,70,75,80,85,90,95,100
	};
	
	public static double UNIT_COST = 1.0;
	public static double STEAD_COST = 50.0;
	
	//交易价格
	public static double BASE_VALUATION = 1.0;
	public static double[] PAYMENT = new double[] {
			0.0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0,
			1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9,2.0};
	//价格的变化区间
	public static double RISE = 0.1;
	
	/*
	 * 遗传算法超参数
	 * */
	public static int POPULATION_NUMBER = 200;
	public static int MAX_GENERATION = 10000;
	public static double GGAP = 1.0;
	public static double CROSS_PROBILITY = 0.95;
	public static double MUTATION_PROBILITY = 0.9;
	/*
	 *遗传算法操作选项 
	 * */
	//输入用于匹配的遗传算法种类 GA是普通的遗传算法 MGA是微生物遗传算法
	public static int GAType = Merger.typeByMGA;      
	//输入基因的类型 random order 两种类型，不要输入空串
	public static int geneticType= Genetic.typeByOrder; 
	//输入选择的方式   有selectByRouletteWheel这一个类别，如果输入是空串那么返回原来的群体
	public static int selectType= Select.typeByRouletteWheel;	   
	//输入选择的方式   有swapOnePoint,swapTwoPoint,winnerSave三种，如果输入是空串那么返回原来的群体 。winnersave主要用于MGA
	public static int crossType = Crossover.typeBySwapOnePoint;    
	//如果进化过程中有多个交叉过程
	public static int crossType1 = Crossover.typeBySwapOnePoint;
	public static int crossType2 = Crossover.typeBySwapTwoPoint;
	public static int crossType3 = Crossover.typeByWinnerSave;
	//输入选择的方式   有changeOnePoint,swapTwoPoint两种，如果输入是空串那么返回原来的群体
	public static int mutateType= Mutate.typeBySwapTwoPoint;
	//如果进化过程中有多个变异过程
	public static int mutateType1 = Mutate.typeByChangeOnePoint;
	public static int mutateType2 = Mutate.typeBySwapTwoPoint;
}
