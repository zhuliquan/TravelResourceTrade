package com.genetic_algorithm.operator;
import java.util.ArrayList;

import com.genetic_algorithm.genetic.Genetic;
import com.market.utility.MersenneTwisterFast;
import com.setting.Setting;
public class Crossover {

	public Crossover() {}

	/**
	 * ʵ���˰���ָ���ķ�ʽȥѡ�������н���
	 * @param ArrayList<Genetic>group �����Ⱥ��
	 * @param String crossType        ����ѡ��ķ�ʽ   ��swapOnePoint,swapTwoPoint���֣���������ǿմ���ô����ԭ����Ⱥ��
	 * @param double Pc               ���뽻��ĸ���
	 * @return ArrayList<Genetic>     ���ؽ��н������Ⱥ
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
	 * ���������յ��㽻�淽ʽ����
	 * @param Genetic genetic1  ��һ������
	 * @param Genetic genetic2 �ڶ�������
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
	 * ����������˫�㽻�淽ʽ����
	 * @param Genetic genetic1  ��һ������
	 * @param Genetic genetic2 �ڶ�������
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
	 * ��������������ȡ����ʤ���߻��򵽲�һ��Ļ���
	 * @param Genetic geneticWinner ��һ��Ļ���
	 * @param Genetic geneticLoser  ��һ��Ļ���
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
		//û�н��б���ǰ
		System.out.println("û�з�������");
		for (int i=0; i<size; i++) {
			System.out.println(group.get(i));
		}
		group = cross(group, "swapOnePoint", Setting.CROSS_PROBILITY);
		//���б����
		System.out.println("��������");
		for (int i=0; i<size; i++) {
			System.out.println(group.get(i));
		}
	}
}
