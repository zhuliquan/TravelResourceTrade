package com.manager.merger.genetic_algorithm;

import java.nio.channels.ShutdownChannelGroupException;
import java.util.ArrayList;
import java.util.Arrays;

import com.market.environment.Agent;

import cern.jet.random.engine.MersenneTwister;


public class Genetic{
	public static int geneticLength;
	public static int lowerValue;
	public static int upperValue;
	int code[];
	public final static int typeByNull = 0;
	public final static int typeByRandom = 1;
	public final static int typeByOrder = 2;
	
	/**
	 * 用于生成基因的构造函数
	 * @param String geneticType 输入基因的类型 ByRandom ByOrder两种类型，不要输入空串
	 * */
	public Genetic(int geneticType) {
		switch (geneticType) {
			case typeByRandom:
				code = generateByRandom();break;
			case typeByOrder:
				code = generateByOrder();break;
			case typeByNull:
				code = generateByOrder();break;
		}
		//如果是随机生成的
		if (typeByNull != geneticType) {
			code = suffle(code);
		}
	}
	/**
	 * 用于完全随机的生成基因的函数
	 * */
	public static int[] generateByRandom() {
		int [] seq = new int[geneticLength];
		for (int i=0; i<geneticLength; i++) {
			seq[i] = (int)Math.floor(Math.random() * upperValue) - lowerValue;
		}
		return seq;
	}
	/**
	 * 用于按照全排列生成基因的函数
	 * */
	public static int [] generateByOrder() {
		int [] seq = new int[geneticLength];
		for (int i=0; i<geneticLength; i++) {
			seq[i] = i;
		}
		return seq;
	}
	/**
	 * 用于将基因进行洗牌的基因函数
	 * */
	public static int[] suffle(int seq[]) {
		int len = seq.length;
		for (int i=0; i<20; i++) {
			int p1 = (int)Math.floor(Math.random() * len);
			int p2 = (int)Math.floor(Math.random() * len);
			int t = seq[p1];
			seq[p1] = seq[p2];
			seq[p2] = t;
		}
		return seq;
	}
	public int getCode(int idx) {
		return code[idx];
	}
	public void setCode(int idx,int value) {
		this.code[idx] = value;
	}
	@Override
	public String toString() {
		return "IntegerGenetic [code=" + Arrays.toString(code) + "]";
	}
	@Override
	public Genetic clone() throws CloneNotSupportedException {
		Genetic newGenetic = new Genetic(typeByNull);
		for (int i=0; i<geneticLength; i++) {
			newGenetic.setCode(i, code[i]);
		}
		return newGenetic;
	}
}
