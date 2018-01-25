package com.market.utility;


import com.market.utility.DoubleAndInteger;
import com.setting.Setting;

public class UtilityofCost {
			//开展本次旅游活动的游客数目
	public static double getTotalCost(int TouristNumber) {
		double TotalCost;		//开展一次旅游活动的总成本
		
		TotalCost = DoubleUnit.add(DoubleAndInteger.mul(Setting.UNIT_COST, TouristNumber), Setting.STEAD_COST);
//		TotalCost = Constances.UNITCOST*TouristNumber+Constances.STEADCOST;
		return TotalCost;
	}
}
