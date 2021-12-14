package model;


import java.sql.*;
import java.text.*;
import java.util.*;


public class Itinerary {

public class Itinerario {
	private ArrayList<Attraction> attractiones = new ArrayList<Attraction>();
	private User user;
	private double time = 0;
	private double coins = 0;

	public Itinerario(User user) throws SQLException {
		this.user = user;
		attractiones = DAOFactory.getItinerarioDAO().findByIdUser(user.getId());
	}




	public boolean isLoaded (Offer offer) {
		boolean esta = false;
		for (Attraction attraction : offer.getContent()) {
			if (attractiones.contains(attraction)) {
				esta = true;
			}
		}
		return esta;
	}

 /*
	@Override
	public String toString() {
		DecimalFormat f = new DecimalFormat("#.##");
		String listaAttractiones = "";
		String mensaje = "Este es su Itinerario:  " + user.getNombre() + "\n\n";
		for (Attraction attraction : attractiones) {
			listaAttractiones += " " + attraction.getNombre() + " " + f.format(attraction.getDuration()) + " horas\n";
		}
		if (listaAttractiones.equals("")) {
			mensaje += "Usted no ha elegido ninguna oferta. ";
		} else {
			mensaje += listaAttractiones + "\n\n Su Costo total: " + f.format(this.costoDeVisita ) + " monedas de oro "
					+ "\n\n Su Tiempo total: " + f.format(this.tiempoDeVisita ) + " horas.";
		}
		return mensaje + "\n\nQue disfrute su visita a la Tierra Media."
				+ "\n\n-----------------------------------------\n";
	}
*/
	

	
/*
	public void add(Offer offer) throws SQLException {
		String tipo;

		if (offer.getContent().size()>1) {
			tipo = "P";
		}  else { 
			tipo = "A";
		}
		DAOFactory.getItinerarioDAO().insert(offer.getId(), tipo, user.getId());

		for (Attraction attraction : offer.getContent()) {
			attractiones.add(attraction);
	    }

	    this.time += offer.getDuration();
	    this.coins +=offer.getCost();

		
	}
   */
}
}
