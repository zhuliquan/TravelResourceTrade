package com.market.utility;

import java.util.Random;

public class RandonGenerator {

	public RandonGenerator() {
	}
	public static double[] generator() {
		double result[] = new double[23];
		MersenneTwisterFast rand = new MersenneTwisterFast(System.currentTimeMillis());
		double rest = 1.0;
		double k = 0.0;
		for (int i=0; i<22; i++) {
			double newGen = rand.nextDouble()*rest;
			result[i] = newGen;
			rest = rest - newGen;
		}
		result[22] = rest;
		for (int t=0; t<2000; t++) {
			int i = rand.nextInt(23);
			int j = rand.nextInt(23);
			while (i == j) {
				j = rand.nextInt(23);
			}
			double tmpr = result[i];
			result[i] = result[j];
			result[j] = tmpr;
		}
		return result;
	}

	public static void main(String[] args) {
		double r[] = generator();
		for (int i=0; i<23; i++) {
			System.out.println(r[i]);
		}

	}

}
