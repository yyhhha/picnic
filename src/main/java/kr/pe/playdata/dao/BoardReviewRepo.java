package kr.pe.playdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.playdata.model.domain.BoardReview;

public interface BoardReviewRepo extends JpaRepository<BoardReview, Integer>{

	//select * from board_review where title=?
	List<BoardReview> findBoardReviewByReviewId(int reviewId);
	
	//select * from board_review where title like '%?%'
	List<BoardReview> findBoardReviewByReviewIdContaining(int reviewId);
	
	
	
	//select * from board_review where seq=? and writer=?
//	BoardReviewRepo findBookBySeqAndWriter(Long s, String w);
	
}
