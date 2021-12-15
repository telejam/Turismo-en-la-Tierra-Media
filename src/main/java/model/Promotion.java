package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Promotion implements Offer {

	private Integer id;
	private String name;
	private Double cost;
	protected List<Attraction> attractions;

	private Map<String, String> errors;
	
	public Promotion(Integer id, String name, List<Attraction> attractions) {
		this.id = id;
		this.name = name;
		this.attractions = attractions;
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Double getDuration() {
		double time = 0;
		for (Attraction attraction : this.attractions) {
			time += attraction.getDuration();
		}
		return time;
	}

	@Override
	public Integer getCapacity() {
		int capacity = 999;
		for (Attraction attraction : this.attractions) {
			if (attraction.getCapacity() < capacity)
				capacity = attraction.getCapacity();
		}
		return capacity;
	}

	@Override
	public boolean canHost(int i) {
		boolean status = true;
		for (Attraction attraction : this.attractions) {
			if (!attraction.canHost(i))
				status = false;
		}
		return status;
	}

	@Override
	public void host(int i) {
		for (Attraction attraction : this.attractions) {
			attraction.host(i);
		}
	}


	@Override
	public List<Attraction> getContent() {
		return attractions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promotion other = (Promotion) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (cost < 0) {
			errors.put("cost", "Debe ser mayor o igual a cero");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}


}
