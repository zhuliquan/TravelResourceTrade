package com.manager;

import java.util.ArrayList;

import com.manager.merger.Merger;
import com.manager.merger.genetic_algorithm.Adapter;
import com.manager.merger.genetic_algorithm.Crossover;
import com.manager.merger.genetic_algorithm.Generator;
import com.manager.merger.genetic_algorithm.Genetic;
import com.manager.merger.genetic_algorithm.Mutate;
import com.manager.merger.genetic_algorithm.Select;
import com.market.environment.Item;
import com.market.environment.Environment;
import com.market.setting.Setting;
import com.market.statstic.Statistic;
import com.market.utility.DoubleUnit;
import com.market.utility.Show;

public class Manager {
	public Manager() {}
	public static void main(String[] args) throws CloneNotSupportedException {
		//�����г��ĳ�ʼ������
		Environment env = new Environment(
				Setting.AGENT_NUMBER, /*agent��Ŀ*/
				Setting.ITEMS_NUMBER, /*��Ʒ����Ŀ*/
				Setting.ENVIRONMENT_GENERATE_TYPE);/*�������ɻ����Ĳ���*/
		
		//����Դ�����ݻ�����
		ArrayList<Item> mergeItems = Merger.merge(env,Setting.GAType);
		System.out.println("�����ɵĺϲ���Ʒ");
		for (int i=0; i<mergeItems.size(); i++) {
			System.out.println(mergeItems.get(i));
		}		
		//�����Ѿ��е�agent�������ɵ���Ʒ�����µĻ���
		Environment newEnv = new Environment(env.getAgents(), mergeItems);
		
		//Ȼ����Դ���дμ�����
		
		
		
		
		
	}
	
	
}
