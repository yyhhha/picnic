package kr.pe.playdata.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.playdata.model.domain.RentCategory;

public interface RentCategoryRepo extends CrudRepository<RentCategory, Integer> {
	
	//카테고리 이름으로 검색
	List<RentCategory> findRentCategoryByRentCateName(String rentCateName);
	

}
