package com.market.utility;


import com.market.utility.DoubleAndInteger;
import com.setting.Setting;

public class UtilityofCost {
			//��չ�������λ���ο���Ŀ
	public static double getTotalCost(int TouristNumber) {
		double TotalCost;		//��չһ�����λ���ܳɱ�
		
		TotalCost = DoubleUnit.add(DoubleAndInteger.mul(Setting.UNIT_COST, TouristNumber), Setting.STEAD_COST);
//		TotalCost = Constances.UNITCOST*TouristNumber+Constances.STEADCOST;
		return TotalCost;
	}
}
