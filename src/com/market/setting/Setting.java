package com.market.setting;

import com.manager.merger.Merger;
import com.manager.merger.genetic_algorithm.Crossover;
import com.manager.merger.genetic_algorithm.Genetic;
import com.manager.merger.genetic_algorithm.Mutate;
import com.manager.merger.genetic_algorithm.Select;
import com.market.environment.Environment;

public class Setting {
	
	/*
	 * �г���ز���
	 * */
	//envType�г�����agent����ʽ BySelect����ɢ�ݻ���ʽ�������ѡ��
	//�����ByRand�������ȫ�������TOURIST_NUMBER,REQUEST_TOURISTNUMBER�������
	public static int ENVIRONMENT_GENERATE_TYPE = Environment.typeBySelect;
	public static int AGENT_NUMBER = 50;          //agent����Ŀ
	public static int ITEMS_NUMBER = 50;          //��Ʒ��Ŀ
	public static int TOURIST_NUMBER = 30;        //�ο���Ŀ������
	public static int REQUEST_TOURISTNUMBER = 10; //�����ο���Ŀ������
	public static int[] TOURIST_CAPCITY = new  int[] {
			10,15,20,25,30,35,40,45,50,55,
			60,65,70,75,80,85,90,95,100
	};
	
	public static double UNIT_COST = 1.0;
	public static double STEAD_COST = 50.0;
	
	//���׼۸�
	public static double BASE_VALUATION = 1.0;
	public static double[] PAYMENT = new double[] {
			0.0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0,
			1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9,2.0};
	//�۸�ı仯����
	public static double RISE = 0.1;
	
	/*
	 * �Ŵ��㷨������
	 * */
	public static int POPULATION_NUMBER = 200;
	public static int MAX_GENERATION = 10000;
	public static double GGAP = 1.0;
	public static double CROSS_PROBILITY = 0.95;
	public static double MUTATION_PROBILITY = 0.9;
	/*
	 *�Ŵ��㷨����ѡ�� 
	 * */
	//��������ƥ����Ŵ��㷨���� GA����ͨ���Ŵ��㷨 MGA��΢�����Ŵ��㷨
	public static int GAType = Merger.typeByMGA;      
	//������������ random order �������ͣ���Ҫ����մ�
	public static int geneticType= Genetic.typeByOrder; 
	//����ѡ��ķ�ʽ   ��selectByRouletteWheel��һ�������������ǿմ���ô����ԭ����Ⱥ��
	public static int selectType= Select.typeByRouletteWheel;	   
	//����ѡ��ķ�ʽ   ��swapOnePoint,swapTwoPoint,winnerSave���֣���������ǿմ���ô����ԭ����Ⱥ�� ��winnersave��Ҫ����MGA
	public static int crossType = Crossover.typeBySwapOnePoint;    
	//��������������ж���������
	public static int crossType1 = Crossover.typeBySwapOnePoint;
	public static int crossType2 = Crossover.typeBySwapTwoPoint;
	public static int crossType3 = Crossover.typeByWinnerSave;
	//����ѡ��ķ�ʽ   ��changeOnePoint,swapTwoPoint���֣���������ǿմ���ô����ԭ����Ⱥ��
	public static int mutateType= Mutate.typeBySwapTwoPoint;
	//��������������ж���������
	public static int mutateType1 = Mutate.typeByChangeOnePoint;
	public static int mutateType2 = Mutate.typeBySwapTwoPoint;
}
