package kr.pe.playdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.playdata.model.domain.BoardPlace;
import kr.pe.playdata.model.domain.Puser;

public interface BoardPlaceRepo extends JpaRepository<BoardPlace, Integer>{

	
	
	
	BoardPlace findBoardPlaceByPlaceId(int placeId);
	
	List<BoardPlace> findBoardPlaceByPlaceName(String placeName);
	
	List<BoardPlace> findBoardPlaceByPlaceContent(String PlaceContent);
	
	List<BoardPlace> findBoardPlaceByPuser(Puser puser);

	List<BoardPlace> findBoardPlaceByPlaceNameContaining(String placeName);

}
