package kr.pe.playdata.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.playdata.model.domain.BoardPlace;
import kr.pe.playdata.model.domain.Puser;

public interface BoardPlaceRepo extends CrudRepository<BoardPlace, Integer>{

	

	
	BoardPlace findBoardPlaceByPlaceId(int PlaceId);
	
	BoardPlace findBoardPlaceByPlaceName(String placeName);
	
	List<BoardPlace> findBoardPlaceByPlaceContent(String PlaceContent);
	
	List<BoardPlace> findBoardPlaceByPuser(Puser puser);
}
