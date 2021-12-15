package persistence;

import java.util.List;

import model.Attraction;
import persistence.commons.GenericDAO;

public interface AttractionDAO extends GenericDAO<Attraction> {
	
public abstract Attraction find(Integer id);


public List<Attraction> findAll();

public int countAll();

public int insert(Attraction attraction);

public int update(Attraction attraction);

public int delete(Attraction attraction);

	

}
