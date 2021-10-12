package kr.pe.playdata.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.playdata.model.domain.Puser;

public interface PuserRepo extends CrudRepository<Puser, String>{
	
	//가입일로 검색
	List<Puser> findPuserByAssignDate(String assignDate);
		
	//탈퇴여부로 검색
	List<Puser> findPuserByUserOut(int userOut);
	
	//아이디(메일)로 검색
	Puser findPuserByUserEmail(String userEmail);
	
	//특정 단어가 포함된 메일로 검색
	List<Puser> findPuserByUserEmailContaining(String userEmail);

	//닉네임으로 검색
	Puser findPuserByUserNickname(String userNickname);
	
	//특정 단어가 포함된 닉네임으로 검색
	List<Puser> findPuserByUserNicknameContaining(String userNickname);
}
