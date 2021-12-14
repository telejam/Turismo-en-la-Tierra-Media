package model;

public class BasePromotion {
	private int id;
	private String name;
	private String type;
	private double value;
	private int disabled;

	public BasePromotion(int id, String name, String type, double value, int disabled) {
		this.id = id;
		this.name = name;
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


}
