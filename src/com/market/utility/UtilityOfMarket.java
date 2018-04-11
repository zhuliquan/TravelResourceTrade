package com.market.utility;

import com.market.environment.Agent;

public class UtilityOfMarket {

	public UtilityOfMarket() {}
	public static double getAgentFormerUtility(Agent agent) {
		double earnFromTourist = DoubleAndInteger.mul(agent.getValuation(), agent.getTouristNumber());
		double totalCost = UtilityofCost.getTotalCost(agent.getTouristNumber());
		return DoubleUnit.sub(earnFromTourist, totalCost); 
	}

}
