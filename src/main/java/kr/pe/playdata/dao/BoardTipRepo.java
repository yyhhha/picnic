package kr.pe.playdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.playdata.model.domain.BoardTip;

public interface BoardTipRepo extends JpaRepository<BoardTip, Integer>{

	//select * from book where title=?
	List<BoardTip> findBoardTipByTipId(int tipId);
	List<BoardTip> findBoardTipByTipTitle(String tipTitle);
	
	//select * from book where title like '%?%'
	List<BoardTip> findBoardTipByTipIdContaining(int tipId);
	
	
	
//	//select * from book where seq=? and writer=?
////	Book findBookBySeqAndWriter(Long s, String w);
	
}
