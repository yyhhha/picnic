package kr.pe.playdata.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.dao.BoardPlaceRepo;
import kr.pe.playdata.dao.BoardRentRepo;
import kr.pe.playdata.dao.BoardReviewRepo;
import kr.pe.playdata.dao.BoardTipRepo;
import kr.pe.playdata.dao.LocCategoryRepo;
import kr.pe.playdata.dao.PcommentRepo;
import kr.pe.playdata.dao.PuserRepo;
import kr.pe.playdata.dao.RentCategoryRepo;
import kr.pe.playdata.model.domain.BoardPlace;
import kr.pe.playdata.model.domain.BoardRent;
import kr.pe.playdata.model.domain.LocCategory;
import kr.pe.playdata.model.domain.Puser;
import kr.pe.playdata.model.domain.RentCategory;

@RestController
public class Controller {

	@Autowired
	private BoardPlaceRepo bpr;
	@Autowired
	private BoardRentRepo brr;
	@Autowired
	private BoardReviewRepo brer;
	@Autowired
	private BoardTipRepo btr;
	@Autowired
	private LocCategoryRepo lcr;
	@Autowired
	private PcommentRepo pcor;
	@Autowired
	private PuserRepo pur;
	@Autowired
	private RentCategoryRepo pcr;
	
	@GetMapping("addRentCate")
	@Transactional
	public RentCategory addRentCate() {
		RentCategory A= new RentCategory(1,null,"test rent category");
		System.out.println(A.toString());
		pcr.save(A);
		
		return A;
	}
	
	@GetMapping("addBoardRent")
	@Transactional
	public BoardRent addBoardRent() {
		BoardRent A = new BoardRent();
		A.setRentCategory(pcr.findRentCategoryByRentCateId(1).get(0));
		A.setLocCate(lcr.findLocCategoryByLocId(1).get(0));
		A.setPuser(pur.findPuserByUserEmail("aa.gmail.com"));
		
		A.setRentName("test rent");
		A.setRentContent("test rent content");
		A.setRentTime("2시간");
		A.setRentPrice(10);
		A.setRentLink("link");
		A.setRentImg("img");
		A.setRentDel(0);
		System.out.println(A.toString());
		brr.save(A);
		return null;
	}
	
	@GetMapping("addPUser")
	@Transactional
	public Puser addPUser() {
		Puser B = new Puser();
		B.setUserEmail("aa.gmail.com");
		B.setUserPassword("aaab");
		B.setUserNickname("jokea");
		B.setRoles("admin");
		B.setUserOut(0);
		System.out.println(B.toString());
		pur.save(B);
		
		return B;
	}
	
	@GetMapping("addLocCate")
	@Transactional
	public LocCategory addLocCate() {
		LocCategory A = new LocCategory();
		A.setLocId(1);
		A.setLocName("test loc");
		A.setLocSido("서울");
		A.setLocSigungu("용산구");
		A.setLocAddress("서울시 용산구");
		A.setPlaceCategory("강/공원/피크닉");
		lcr.save(A);
		
		return A;
	}
	
	@GetMapping("addBoardPlace")
	@Transactional
	public BoardPlace addBoardPlace() {
		BoardPlace A = new BoardPlace(1,lcr.findLocCategoryByLocId(1).get(0),pur.findPuserByUserEmail("aa.gmail.com"),null,"test place name", "test place content", "img",0);
		A.setPlaceId(1);
		
		bpr.save(A);
		
		return A;
	}
	
	@GetMapping("addBoardTip")
	@Transactional
	public String addBoardTip() {
		
		System.out.println(A.toString());
		btr.save(A);
		
		return A;
	}
	@GetMapping("addPComment")
	public String addPComment() {
		
		
		System.out.println(A.toString());
		pcor.save(A);
		
		return A;
	}
	
	
	@GetMapping("addBoardReview")
	public String addBoardReview() {
		
		System.out.println(A.toString());
		brer.save(A);
		
		return A;
	}
}
