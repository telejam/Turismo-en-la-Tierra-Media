package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Attraction implements Offer {

	private Integer id;
	private String name;
	private Double cost;
	private Double duration;
	private Integer capacity;
	
	private Map<String, String> errors;
	
	public Attraction(String name, Double cost, Double duration, Integer capacity) {
		super();
		this.name = name;
		this.cost = cost;
		this.duration = duration;
		this.capacity = capacity;
	}
	
	public Attraction(Integer id, String name, Double cost, Double duration, Integer capacity) {
		this(name, cost, duration, capacity);
		this.id = id;
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (cost <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (duration <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
		if (duration > 60) {
			errors.put("duration", "Excede el tiempo máximo");
		}
		if (capacity <= 0) {
			errors.put("capacity", "Debe ser positivo");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
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
	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	@Override
	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Attraction [id=" + id + ", name=" + name + ", cost=" + cost + ", duration=" + duration + ", capacity="
				+ capacity + "]";
	}

	@Override
	public boolean canHost(int i) {
		return capacity >= i;
	}

	@Override
	public void host(int i) {
		this.capacity -= i;
	}


	@Override
	public List<Attraction> getContent() {
        List<Attraction> content = new ArrayList<Attraction>();
        content.add(this);
        return content;
	}
}
