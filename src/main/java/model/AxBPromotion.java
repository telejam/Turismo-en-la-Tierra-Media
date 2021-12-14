package model;

import java.util.List;

public class AxBPromotion extends Promotion {
	
	private  List<Attraction> attractionsToPay;
	
	public AxBPromotion(int id, String nombre, List<Attraction> attractions, List<Attraction> attractionsToPay) {
		super(id, nombre, attractions);
		this.attractionsToPay = attractionsToPay;
	}

	@Override
	public Double getCost() {
		double cost = 0;
		for (Attraction atraccion : attractionsToPay) {
			cost += atraccion.getCost();
		}

		return cost;
	}

}
