package com.setting;

public class Setting {
	
	/*
	 * 市场相关参数
	 * */
	public static int AGENT_NUMBER = 20;	
	public static int TOURIST_NUMBER = 30;
	public static int REQUEST_TOURISTNUMBER = 10;
	
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
	public static int POPULATION_NUMBER = 120;
	public static int MAX_GENERATION = 10000;
	public static double GGAP = 1.0;
	public static double CROSS_PROBILITY = 0.95;
	public static double MUTATION_PROBILITY = 0.6;
	/*
	 *遗传算法操作选项 
	 * */
	//输入用于匹配的遗传算法种类
	public static String GAType = "MGA";      
	//输入基因的类型 random order 两种类型，不要输入空串
	public static String geneticType= "order"; 
	//输入选择的方式   有selectByRouletteWheel这一个类别，如果输入是空串那么返回原来的群体
	public static String selectType="selectByRouletteWheel";	   
	//输入选择的方式   有swapOnePoint,swapTwoPoint,winnerSave三种，如果输入是空串那么返回原来的群体
	public static String crossType ="swapOnePoint";    
	//如果进化过程中有多个交叉过程
	public static String crossType1 = "swapOnePoint";
	public static String crossType2 = "swapTwoPoint";
	public static String crossType3 = "winnerSave";
	//输入选择的方式   有changeOnePoint,swapTwoPoint两种，如果输入是空串那么返回原来的群体
	public static String mutateType="swapTwoPoint";    
	//如果进化过程中有多个变异过程
	public static String mutateType1 = "changeOnePoint"; 
	public static String mutateType2 = "swapTwoPoint";
}
