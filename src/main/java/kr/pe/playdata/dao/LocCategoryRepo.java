package kr.pe.playdata.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.playdata.model.domain.LocCategory;

public interface LocCategoryRepo extends CrudRepository<LocCategory, Integer>{

	List<LocCategory> findLocCategoryByLocId(int id);
	List<LocCategory> findLocCategoryByLocName(String name);
	
}
