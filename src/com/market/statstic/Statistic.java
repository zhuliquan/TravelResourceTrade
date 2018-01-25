package com.market.statstic;

import com.market.utility.DoubleAndInteger;
import com.market.utility.DoubleUnit;

public class Statistic {

	public Statistic() {}
	
	public static double getValuesMean(double values[]) {
		double mean = 0.0;
		int length = values.length;
		for (int i=0; i<length; i++) {
			mean = DoubleUnit.add(mean, values[i]);
		}
		return DoubleAndInteger.DoubledivInteger(mean, length);
	}

	public static int getValuesMaxIdx(double values[]) {
		double max = values[0];
		int maxIdx = 0;
		int length = values.length;
		for (int i=1; i<length; i++) {
			if (max <= values[i]) {
				max = values[i];
				maxIdx = i;
			}
		}
		return maxIdx;
		
	}

	public static int getValuesMinIdx(double values[]) {
		double min = values[0];
		int minIdx = 0;
		int length = values.length;
		for (int i=1; i<length; i++) {
			if (min >= values[i]) {
				min = values[i];
				minIdx = i;
			}
		}
		return minIdx;
		
	}

	public static double getValuesSum(double values[]) {
		double totalValues = 0.0;
		int length = values.length;
		for (int i=0; i<length; i++) {
			totalValues = DoubleUnit.add(totalValues, values[i]);
		}
		return totalValues;
	}
}
