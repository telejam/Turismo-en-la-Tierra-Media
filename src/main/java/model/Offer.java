package model;

import java.util.List;

public interface Offer {
	public Integer getId();
	public void setId(Integer id);
	public String getName();
	public void setName(String name);
	public Double getCost();
	public Double getDuration();
	public Integer getCapacity();
	public boolean canHost(int i);
	public void host(int i);
	public List<Attraction> getContent();
}
