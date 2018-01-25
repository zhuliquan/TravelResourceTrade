package com.market.utility;

import com.market.utility.DoubleAndInteger;

public class UtilityOfSeller {
	
//	int EarnedTouristNumber		//在波提中出售的旅游资源数目
//	double gather;				//在本次交易中获得的价格	
	public double getutility(double gather,int EarnedTouristNumber) {
		double utility = DoubleAndInteger.mul(gather, EarnedTouristNumber);
//		double utility = gather*EarnedTouristNumber;
		return utility;
	}
}
