package com.genetic_algorithm.operator;

import java.util.ArrayList;

import com.genetic_algorithm.genetic.Genetic;
import com.market.utility.MersenneTwisterFast;

public class Generator {
	public Generator() {}
	public static ArrayList<Genetic> createPopulation(int populationNumber,String geneticType){
		ArrayList<Genetic> group = new ArrayList<>();
		for (int i=0; i<populationNumber; i++) {
			group.add(new Genetic(geneticType));
		}
		return group;
	}
	public static ArrayList<Genetic> shuffleGroup(ArrayList<Genetic> group){
		int populationNumber = group.size();
		for (int i=0; i<2000; i++) {
			int idx1 = (int)Math.floor(Math.random() * populationNumber);
			int idx2 = (int)Math.floor(Math.random() * populationNumber);
			Genetic g1 = group.get(idx1);
			Genetic g2 = group.get(idx2);
			group.set(idx1, g2);
			group.set(idx2, g1);
		}
		return group;
	}
	public static void main(String[] args) {
		Genetic.geneticLength = 20;
		Genetic.upperValue = 20;
		ArrayList<Genetic> group = createPopulation(100,"random");
//		group = shuffleGroup(group);
		
		for (int i=0; i<100; i++) {
			System.out.println(group.get(i));
		}
	}
}
