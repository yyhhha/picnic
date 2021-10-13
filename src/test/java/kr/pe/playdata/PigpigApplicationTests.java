package kr.pe.playdata;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import kr.pe.playdata.dao.BoardPlaceRepo;
import kr.pe.playdata.dao.LocCategoryRepo;
import kr.pe.playdata.dao.PuserRepo;
import kr.pe.playdata.model.domain.BoardPlace;
import kr.pe.playdata.model.domain.LocCategory;
//
//import kr.pe.playdata.controller.Controller;
//
@SpringBootTest
class PigpigApplicationTests {
//	
//	@Autowired
//	private Controller controller;
	@Autowired
	private PuserRepo pur;
	@Autowired
	private BoardPlaceRepo bpr;
	@Autowired
	private LocCategoryRepo lcr;
	
	private MockMvc mock;
	
	@BeforeEach
	public void init() {
		System.out.println("init()");
		mock = MockMvcBuilders.standaloneSetup(pur).build();
	}

	@Test
	@Transactional
	void contextLoads() {
		
//		System.out.println(pur.findPuserByUserEmail("aa.gmail.com"));
//		System.out.println(pur.findAll());
//		BoardPlace A = new BoardPlace();
//		A.setPlaceName("test place");
//		A.setPlaceImg("img");
//		A.setPlaceContent("content");
//		A.setPuser(pur.findPuserByUserEmail("aa.gmail.com"));
//		A.setLocCate(lcr.findLocCategoryByLocId(21).get(0));
//		System.out.println(A);

		System.out.println(lcr.findLocCategoryByLocId(1));
	}

}
