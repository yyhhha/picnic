package kr.pe.playdata.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.playdata.model.domain.LocCategory;
import kr.pe.playdata.model.domain.Pcomment;

public interface PcommentRepo  extends CrudRepository<Pcomment, Integer>{

	List<Pcomment> findPcommentByCommentId(int id);
	List<Pcomment> findPcommentByCommentContentContaining(String comment);
	


}
