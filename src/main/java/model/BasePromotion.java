package model;

import java.util.HashMap;
import java.util.Map;

public class BasePromotion {
	private int id;
	private String name;
	private String description;
	private String type;
	private double value;
	private int disabled;
	private Map<String, String> errors;
	

	public BasePromotion(int id, String name, String description, String type, double value, int disabled) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.value = value;
		this.disabled = disabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getDisabled() {
		return disabled;
	}

	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (value < 0) {
			errors.put("cost", "Debe ser mayor o igual a cero");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
}
