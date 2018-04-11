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
	 * ʵ���˰���ָ���ķ�ʽȥѡ�������б���
	 * @param ArrayList<Genetic>group �����Ⱥ��
	 * @param String mutateType       ����ѡ��ķ�ʽ   ��changeOnePoint,swapTwoPoint���֣���������ǿմ���ô����ԭ����Ⱥ��
	 * @param double Pm               �������ĸ���
	 * @return ArrayList<Genetic>     ���ؽ��б������Ⱥ
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
	 * ����ı��»���
	 * @param Genetic genetic ��Ҫ����Ļ���
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
	 * ����������Ļ���
	 * @param Genetic genetic ��Ҫ����Ļ���
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
		//û�н��б���ǰ
		System.out.println("û�з�������");
		for (int i=0; i<size; i++) {
			System.out.println(group.get(i));
		}
		group = mutate(group, typeBySwapTwoPoint, Setting.MUTATION_PROBILITY);
		group = mutate(group, typeByChangeOnePoint, Setting.MUTATION_PROBILITY);
		//���б����
		System.out.println("��������");
		for (int i=0; i<size; i++) {
			System.out.println(group.get(i));
		}
		
	}
}
