package model;

import java.util.List;

public class PorcentualPromotion extends Promotion {
	
	private double discount;
	
	public PorcentualPromotion(int id, String name, List<Attraction> attractionsPromo, double value) {
		super(id, name, attractionsPromo);
	    this. discount = value;
	}
	
	@Override
	public Double getCost() {
		double cost = 0;
		for (Attraction attraction : attractions) {
			cost += attraction.getCost();
		}

		return cost-(cost*(discount/100));
	}
}