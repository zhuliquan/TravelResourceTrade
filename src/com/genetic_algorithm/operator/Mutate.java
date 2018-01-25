package com.genetic_algorithm.operator;

import java.util.ArrayList;

import com.genetic_algorithm.genetic.Genetic;
import com.market.utility.MersenneTwisterFast;
import com.setting.Setting;

public class Mutate {

	public Mutate() {}
	
	/**
	 * ʵ���˰���ָ���ķ�ʽȥѡ�������б���
	 * @param ArrayList<Genetic>group �����Ⱥ��
	 * @param String mutateType       ����ѡ��ķ�ʽ   ��changeOnePoint,swapTwoPoint���֣���������ǿմ���ô����ԭ����Ⱥ��
	 * @param double Pm               �������ĸ���
	 * @return ArrayList<Genetic>     ���ؽ��б������Ⱥ
	 * */
	public static ArrayList<Genetic> mutate(ArrayList<Genetic> group,String mutateType, double Pm){		
		if ((mutateType.equals(""))||
			(mutateType.equals("changeOnePoint") && (Genetic.upperValue - Genetic.lowerValue <= 1))||
			(mutateType.equals("swapTwoPoint") && (Genetic.geneticLength <= 1)) || group.size() == 0) {
			return group;
		}
		
		for (Genetic genetic:group) {
			if (Math.random() > Pm) {
				continue;
			}
			if (mutateType.equals("changeOnePoint")) {
				changeOnePoint(genetic);
			} else if (mutateType.equals("swapTwoPoint")) {
				swapTwoPoint(genetic);
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
		ArrayList<Genetic> group = Generator.createPopulation(size,"random");
		//û�н��б���ǰ
		System.out.println("û�з�������");
		for (int i=0; i<size; i++) {
			System.out.println(group.get(i));
		}
		group = mutate(group, "swapTwoPoint", Setting.MUTATION_PROBILITY);
		group = mutate(group, "changeOnePoint", Setting.MUTATION_PROBILITY);
		//���б����
		System.out.println("��������");
		for (int i=0; i<size; i++) {
			System.out.println(group.get(i));
		}
		
	}
}
