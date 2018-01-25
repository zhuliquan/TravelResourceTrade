package com.setting;

public class Setting {
	
	/*
	 * �г���ز���
	 * */
	public static int AGENT_NUMBER = 20;	
	public static int TOURIST_NUMBER = 30;
	public static int REQUEST_TOURISTNUMBER = 10;
	
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
	public static int POPULATION_NUMBER = 120;
	public static int MAX_GENERATION = 10000;
	public static double GGAP = 1.0;
	public static double CROSS_PROBILITY = 0.95;
	public static double MUTATION_PROBILITY = 0.6;
	/*
	 *�Ŵ��㷨����ѡ�� 
	 * */
	//��������ƥ����Ŵ��㷨����
	public static String GAType = "MGA";      
	//������������ random order �������ͣ���Ҫ����մ�
	public static String geneticType= "order"; 
	//����ѡ��ķ�ʽ   ��selectByRouletteWheel��һ�������������ǿմ���ô����ԭ����Ⱥ��
	public static String selectType="selectByRouletteWheel";	   
	//����ѡ��ķ�ʽ   ��swapOnePoint,swapTwoPoint,winnerSave���֣���������ǿմ���ô����ԭ����Ⱥ��
	public static String crossType ="swapOnePoint";    
	//��������������ж���������
	public static String crossType1 = "swapOnePoint";
	public static String crossType2 = "swapTwoPoint";
	public static String crossType3 = "winnerSave";
	//����ѡ��ķ�ʽ   ��changeOnePoint,swapTwoPoint���֣���������ǿմ���ô����ԭ����Ⱥ��
	public static String mutateType="swapTwoPoint";    
	//��������������ж���������
	public static String mutateType1 = "changeOnePoint"; 
	public static String mutateType2 = "swapTwoPoint";
}
