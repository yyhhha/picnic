package kr.pe.playdata.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.playdata.model.domain.BoardRent;
import kr.pe.playdata.model.domain.Puser;
import kr.pe.playdata.model.domain.RentCategory;

public interface BoardRentRepo extends CrudRepository<BoardRent, Integer>{

	BoardRent findBoardRentByRentId(int rentId);
	
	BoardRent findBoardRentByRentName(String RentName);
	
	List<BoardRent> findBoardRentByRentCategory(RentCategory rentCategory);
	
	List<BoardRent> findBoardRentByPuser(Puser puser);
	
}
