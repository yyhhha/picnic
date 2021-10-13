package kr.pe.playdata.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
import kr.pe.playdata.model.domain.BoardReview;
import kr.pe.playdata.model.domain.BoardTip;
import kr.pe.playdata.model.domain.LocCategory;
import kr.pe.playdata.model.domain.Pcomment;
import kr.pe.playdata.model.domain.Puser;
import kr.pe.playdata.model.domain.RentCategory;

@RestController
@RequestMapping("/con")
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
	
	
	/***
	 * 
	 * @return
	 * add yyh
	 */
	
	@GetMapping("signup")
	public void signin(HttpServletResponse response) {
		String redirect_uri="http://localhost/signup.html";
    	try {
			response.sendRedirect(redirect_uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@GetMapping("login")
	public void login(HttpServletResponse response) {
		String redirect_uri="http://localhost/login.html";
    	try {
			response.sendRedirect(redirect_uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**/
	 
	@GetMapping("addRentCate")
	@Transactional
	public String addRentCate() {
		RentCategory A = new RentCategory(1,null,"test rent category");
		pcr.save(A);
		
		return "Rent cate 저장 성공";
	}
	
	@GetMapping("addBoardRent")
	@Transactional
	public String addBoardRent() {
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
		return "Rent 저장 성공";
	}
	
	@GetMapping("addPUser")
	@Transactional
	public String addPUser() {
		Puser B = new Puser();
		B.setUserEmail("aa.gmail.com");
		B.setUserPassword("aaab");
		B.setUserNickname("jokea");
		B.setRoles("admin");
		B.setUserOut(0);
		System.out.println(B.toString());
		pur.save(B);
		
		return "puser 저장 성공";
	}
	
	@GetMapping("addLocCate")
	@Transactional
	public String addLocCate() {
		LocCategory A = new LocCategory();
		A.setLocId(1);
		A.setLocName("test loc");
		A.setLocSido("서울");
		A.setLocSigungu("용산구");
		A.setLocAddress("서울시 용산구");
		A.setPlaceCategory("강/공원/피크닉");
		lcr.save(A);
		
		return "loc 저장 성공";
	}
	
	@GetMapping("addBoardPlace")
	@Transactional
	public String addBoardPlace() {
		BoardPlace A = new BoardPlace();
		A.setPlaceId(1);
		A.setLocCate(lcr.findLocCategoryByLocId(1).get(0));
		A.setPuser(pur.findPuserByUserEmail("aa.gmail.com"));
		A.setPlaceName("test place name");
		A.setPlaceContent("test place content");
		A.setPlaceImg("img");
		
		bpr.save(A);
		
		return "place 저장 성공";
	}
	
	@GetMapping("addBoardTip")
	@Transactional
	public String addBoardTip() {
		BoardTip A = new BoardTip();
		A.setTipId(1);
		A.setTipTitle("tip title");
		A.setTipContent("tip content");
		A.setPuser(pur.findPuserByUserEmail("aa.gmail.com"));
		A.setTipImg("img");
		A.setTipLike(0);
		A.setTipDel(0);
				
		System.out.println(A.toString());
		btr.save(A);
		
		return "tip 저장 성공";
	}
	@GetMapping("addPComment")
	public String addPComment() {
		Pcomment A = new Pcomment();
		A.setCommentId(1);
		A.setPuser(pur.findPuserByUserEmail("aa.gmail.com"));
		A.setCommentContent("comment content");
		A.setCommentDel(0);
		A.setBoardRent(brr.findBoardRentByRentId(1));
		A.setBoardTip(btr.findTipTitleByTipId(1).get(0));
		
		System.out.println(A.toString());
		pcor.save(A);
		
		return "comment 저장 성공";
	}
	
	
	@GetMapping("addBoardReview")
	public String addBoardReview() {
		BoardReview A = new BoardReview();
		
		A.setReviewId(1);
		A.setPuser(pur.findPuserByUserEmail("aa.gmail.com"));
		A.setReviewTitle("review title");
		A.setReviewContent("review content");
		
		A.setReviewScore(0);
		
		System.out.println(A.toString());
		brer.save(A);
		
		return "리뷰 저장 성공";
	}
}
