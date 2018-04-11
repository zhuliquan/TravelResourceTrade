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
		//设置市场的初始化参数
		Environment env = new Environment(
				Setting.AGENT_NUMBER, /*agent数目*/
				Setting.ITEMS_NUMBER, /*商品的数目*/
				Setting.ENVIRONMENT_GENERATE_TYPE);/*用于生成环境的参数*/
		
		//将资源按照容积进行
		ArrayList<Item> mergeItems = Merger.merge(env,Setting.GAType);
		System.out.println("新生成的合并商品");
		for (int i=0; i<mergeItems.size(); i++) {
			System.out.println(mergeItems.get(i));
		}		
		//利用已经有的agent和新生成的商品生成新的环境
		Environment newEnv = new Environment(env.getAgents(), mergeItems);
		
		//然后将资源进行次价拍卖
		
		
		
		
		
	}
	
	
}
