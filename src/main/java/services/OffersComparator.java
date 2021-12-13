package services;

import java.util.Comparator;

import model.Offer;

public class OffersComparator implements Comparator<Offer>{
	
	@Override
	public int compare(Offer offer1, Offer offer2) {
		int status;
		if (offer1.getContent().size() > 1 && offer2.getContent().size() == 1) 
			status = -1;

		else if (offer1.getContent().size() == 1 && offer2.getContent().size() > 1) 
			status = 1;
		
		else {
			status = -Double.compare(offer1.getCost(), offer2.getCost());
			if (status == 0) {
				status = -Double.compare(offer1.getDuration(), offer2.getDuration());
			}
		}
		return status;
	}

}
