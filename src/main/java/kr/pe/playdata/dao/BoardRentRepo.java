package kr.pe.playdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.playdata.model.domain.BoardRent;
import kr.pe.playdata.model.domain.Puser;

public interface BoardRentRepo extends JpaRepository<BoardRent, Integer>{

//	BoardRent findBoardRentByRentId(int rentId);
	
	BoardRent findBoardRentByRentName(String RentName);
	
//	List<BoardRent> findBoardRentByRentCateName(String RentCateName);
	
	List<BoardRent> findBoardRentByPuser(Puser puser);
	
	List<BoardRent> findBoardRentByRentId(int rentId);

	List<BoardRent> findBoardRentByRentNameContaining(String rentName);
	
	List<BoardRent> findBoardRentByRentCateName(String rentCateName);
	
}
