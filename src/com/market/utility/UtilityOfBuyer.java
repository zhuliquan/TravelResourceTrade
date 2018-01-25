package com.market.utility;


import com.market.environment.Agent;
import com.market.utility.DoubleAndInteger;

public class UtilityOfBuyer {
	public static double getuBuyerUtility(Agent agent,int EarnedTouristNumber,double payment) {
		double utility = DoubleAndInteger.mul(DoubleUnit.sub(agent.getValuation(), payment), EarnedTouristNumber);
		return utility;	
	}
}
