package model;

import java.util.List;

public class AbsolutePromotion extends Promotion {
	
	private double cost;
	
	public AbsolutePromotion(int id, String name, String description, List<Attraction> attractions, double cost) {
		super(id, name, description, attractions);
		this.cost = cost;
	}

	@Override
	public Double getCost() {
		return this.cost;
	}
	
}
