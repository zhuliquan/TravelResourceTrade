package com.market.utility;

import java.util.ArrayList;
import java.util.TreeSet;

import com.genetic_algorithm.genetic.Genetic;
import com.market.environment.Environment;
import com.market.statstic.Statistic;

public class Show {

	public Show() {}
	
	public static void showEachGenResult(int gen,double objValues[]) {
		double maxObj = objValues[Statistic.getValuesMaxIdx(objValues)];
		double meanObj = Statistic.getValuesMean(objValues);
		System.out.printf("��%d���������ֵ%f ƽ��ֵ%f\n",gen,maxObj,meanObj);
	}
	
	public static void showEnvironment(Environment env) {
		int agentNumber = env.getAgentNumber();
		int commodityNumber = env.getCommodityNumber();
		System.out.println("��ǰ�Ļ������");
		for (int i=0; i<agentNumber; i++) {
			System.out.println(env.getAgent(i));
		}
//		for (int i=0; i<commodityNumber; i++) {
//			System.out.println(env.getCommodity(i));
//		}
	}
	public static void showMatch(Genetic genetic,Environment env) {
		int agentNumber = env.getAgentNumber();
		ArrayList<ArrayList<Integer> > match = new ArrayList<>() ;
		for (int i=0; i<agentNumber; i++) {
			match.add(new ArrayList<>());
		}
		int geneticLength = Genetic.geneticLength;
		int getTouristNumber[] = new int[agentNumber];
		for (int i=0; i<agentNumber; i++) {getTouristNumber[i] = 0;}
		for (int i=0; i<geneticLength; i++) {
			int agentIndex = genetic.getCode(i);
			getTouristNumber[agentIndex] += env.getCommodity(i).getTouristNumber();
			match.get(agentIndex).add(i);
		}
		int commodityNumber = env.getCommodityNumber();
		boolean isAllocated[] = new boolean[commodityNumber];
		for (int i=0; i<agentNumber; i++) {
			if (getTouristNumber[i] != 0) {
				if (getTouristNumber[i] <= env.getAgent(i).getTouristCapcity()) {					
					ArrayList<Integer> cs = match.get(i);
					for (int c:cs) {
						isAllocated[c] = true;
					}
				} else {
					ArrayList<Integer> cs = match.get(i);
					for (int c:cs) {
						isAllocated[c] = false;
					}
				}
			}
		}
		
		System.out.println("��Ʒ�����ϵ");
		for (int i=0; i<commodityNumber; i++) {
			if (isAllocated[i]) {
				System.out.printf("��%d����Ʒ�Ѿ�����, ��ԭ����������%d, �����������%d\n",
						i,env.getCommodity(i).getOnwer().getAgentID(),
						env.getAgent(genetic.getCode(i)).getAgentID());
			} else {
				System.out.printf("��%d����Ʒû�з���, ��ԭ����������%d\n",
						i,env.getCommodity(i).getOnwer().getAgentID());
			}
		}
//		System.out.println("��ǰ��agentӵ����Ʒ���");
//		for (int i=0; i<agentNumber; i++) {
//			if (getTouristNumber[i] != 0) {
//				if (getTouristNumber[i] <= env.getAgent(i).getTouristCapcity()) {					
//					System.out.printf("��%d��agent ��ǰӵ��%d, ���������%d\n",
//							i,getTouristNumber[i],env.getAgent(i).getTouristCapcity());
//				} else {
//					
//				}
//			} else {
//				
//			}
//		}
	}

	public static void showEnvironment(Genetic bestGenetic, Environment env) {
		int agentNumber = env.getAgentNumber();
		int commodityNumber = env.getCommodityNumber();
		System.out.println("��ǰ�Ļ������");
		for (int i=0; i<agentNumber; i++) {
			System.out.println(env.getAgent(i));
		}
//		for (int i=0; i<commodityNumber; i++) {
//			System.out.println(env.getCommodity(i));
//		}
	}
	public static void showSellerMatchBuyer(Genetic genetic,Environment env) {
		int agentNumber = env.getAgentNumber();
		ArrayList<ArrayList<Integer> > match = new ArrayList<>() ;
		for (int i=0; i<agentNumber; i++) {
			match.add(new ArrayList<>());
		}
		int geneticLength = Genetic.geneticLength;
		int getTouristNumber[] = new int[agentNumber];
		for (int i=0; i<agentNumber; i++) {getTouristNumber[i] = 0;}
		for (int i=0; i<geneticLength; i++) {
			int agentIndex = genetic.getCode(i);
			getTouristNumber[agentIndex] += env.getCommodity(i).getTouristNumber();
			match.get(agentIndex).add(i);
		}
		int commodityNumber = env.getCommodityNumber();
		boolean isAllocated[] = new boolean[commodityNumber];
		for (int i=0; i<agentNumber; i++) {
			if (getTouristNumber[i] != 0) {
				if (getTouristNumber[i] <= env.getAgent(i).getTouristCapcity()) {					
					ArrayList<Integer> cs = match.get(i);
					for (int c:cs) {
						isAllocated[c] = true;
					}
				} else {
					ArrayList<Integer> cs = match.get(i);
					for (int c:cs) {
						isAllocated[c] = false;
					}
				}
			}
		}
		
		System.out.println("��Ʒ�����ϵ");
		TreeSet<Integer> sellIdx = new TreeSet<>();
		TreeSet<Integer> buyerIdx = new TreeSet<>();
		for (int i=0; i<commodityNumber; i++) {
			if (isAllocated[i]) {
				System.out.printf("��%d��Agent�������˵�%d��Agentһ��%d���ο�\n",
						env.getCommodity(i).getOnwer().getAgentID(),
						env.getAgent(genetic.getCode(i)).getAgentID(),
						env.getCommodity(i).getOnwer().getTouristNumber());
				sellIdx.add(env.getCommodity(i).getOnwer().getAgentID());
				buyerIdx.add(env.getAgent(genetic.getCode(i)).getAgentID());
			} else {
				System.out.printf("��%d��û�������Լ�����Ʒ\n",
						env.getCommodity(i).getOnwer().getAgentID());
			}
		}
		
		System.out.println("���Һ����");
		System.out.println("����");
		System.out.println(sellIdx);
		System.out.println("���");
		System.out.println(buyerIdx);
		System.out.println("ͬʱ�����һ������");
		TreeSet<Integer> interset = new TreeSet<>();
		interset.addAll(sellIdx);
		interset.retainAll(buyerIdx);
		System.out.println(interset);
		
	}

}
