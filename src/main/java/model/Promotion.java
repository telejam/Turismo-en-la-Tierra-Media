package model;

import java.util.List;

public abstract class Promotion implements Offer {

	private Integer id;
	private String name;
	protected List<Attraction> attractions;

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

}
