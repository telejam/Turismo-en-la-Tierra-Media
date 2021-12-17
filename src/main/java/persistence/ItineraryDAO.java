package persistence;

import java.util.List;
import model.Offer;

public interface ItineraryDAO  {

	public List<Offer> findByIdUser(int id_user);
	public List<Offer> findAll();
	public int insert(int id_offer, String type_offer, int id_user);
}
