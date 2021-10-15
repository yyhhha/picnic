package kr.pe.playdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.playdata.model.domain.BoardPlace;
import kr.pe.playdata.model.domain.BoardTip;

public interface BoardTipRepo extends JpaRepository<BoardTip, Integer>{

	//select * from book where title=?
	List<BoardTip> findBoardTipByTipId(int tipId);
	List<BoardTip> findBoardTipByTipTitle(String tipTitle);
	
	//select * from book where title like '%?%'
	List<BoardTip> findBoardTipByTipIdContaining(int tipId);
	List<BoardTip> findBoardTipByTipTitleContaining(String tipTitle);
	
//	Iterator<BoardTip> findAllById(int tipId);
	
//	List<BoardTip> findBoardTipByRentCateName(String rengCateName);
	
//	//select * from book where seq=? and writer=?
////	Book findBookBySeqAndWriter(Long s, String w);
	
	
	
//	//select bt from BOARD_TIP bt  order by bt.TIP_ID DESC;
//	@Query("select b from BOARD_TIP b  order by b.TIP_ID DESC")
//	List<BoardTip> findAllDesc();
	
}
