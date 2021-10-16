package kr.pe.playdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	List<BoardRent> findBoardRentByPuserUserEmail(String useremail);
	
//	  @Query("SELECT r FROM BOARD_RENT r WHERE r.USER_EMAIL = :useremail")
//	  List<BoardRent> findBoardRentByPuserBoardRents(@Param("useremail") String useremail);
	 
}
