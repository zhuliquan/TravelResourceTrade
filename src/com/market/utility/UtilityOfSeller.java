package com.market.utility;

import com.market.utility.DoubleAndInteger;

public class UtilityOfSeller {
	
//	int EarnedTouristNumber		//�ڲ����г��۵�������Դ��Ŀ
//	double gather;				//�ڱ��ν����л�õļ۸�	
	public double getutility(double gather,int EarnedTouristNumber) {
		double utility = DoubleAndInteger.mul(gather, EarnedTouristNumber);
//		double utility = gather*EarnedTouristNumber;
		return utility;
	}
}
