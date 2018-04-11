package com.market.environment;

import java.util.ArrayList;

public class MergeItem extends Item{
	/**
	 * ���������Ʒ�ϵ�
	 * */
	ArrayList<Item> items;  //������SingleItem���
	public MergeItem(int itemId,int touristNumber, Agent onwer) {
		super(itemId, touristNumber, onwer);
		items = new ArrayList<>();
	}
	public void addSingleItem(Item item) {
		items.add(item);
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(super.toString());
		sb.append("get from:[");
		for (Item item:items) {
			sb.append(item.getItemId()+",");
		}
		sb.append("]");
		sb.append("]");
		return sb.toString();
	}
}
