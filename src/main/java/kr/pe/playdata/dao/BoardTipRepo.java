package kr.pe.playdata.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.playdata.model.domain.BoardTip;

public interface BoardTipRepo extends CrudRepository<BoardTip, Integer>{

	//select * from book where title=?
	List<BoardTip> findBoardTipByTipId(int tipId);
	
	//select * from book where title like '%?%'
	List<BoardTip> findBoardTipByTipIdContaining(int tipId);
	
	
	
//	//select * from book where seq=? and writer=?
////	Book findBookBySeqAndWriter(Long s, String w);
	
}
