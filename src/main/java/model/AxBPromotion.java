package model;

import java.util.List;

public class AxBPromotion extends Promotion {
	
	private  List<Attraction> attractionsToPay = null;
	
	public AxBPromotion(int id, String nombre, List<Attraction> attractions, List<Attraction> attractionsToPay) {
		super(id, nombre, attractions);
		this.attractionsToPay = attractionsToPay;
	}

	@Override
	public Double getCost() {
		Double cost = 0.0;
		for (Attraction attraction : attractionsToPay) {
			cost += attraction.getCost();
		}

		return cost;
	}

}
