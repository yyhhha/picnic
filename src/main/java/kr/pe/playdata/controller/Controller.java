package kr.pe.playdata.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.dao.BoardPlaceRepo;
import kr.pe.playdata.dao.BoardRentRepo;
import kr.pe.playdata.dao.BoardReviewRepo;
import kr.pe.playdata.dao.BoardTipRepo;
import kr.pe.playdata.dao.LocCategoryRepo;
import kr.pe.playdata.dao.PcommentRepo;
import kr.pe.playdata.dao.PuserRepo;
import kr.pe.playdata.model.domain.BoardPlace;
import kr.pe.playdata.model.domain.BoardRent;
import kr.pe.playdata.model.domain.BoardReview;
import kr.pe.playdata.model.domain.BoardTip;
import kr.pe.playdata.model.domain.LocCategory;
import kr.pe.playdata.model.domain.Pcomment;
import kr.pe.playdata.model.domain.Puser;
import kr.pe.playdata.model.dto.BoardRentDTO;
import kr.pe.playdata.model.dto.BoardTipDTO;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
//	@Autowired
//	private RentCategoryRepo pcr;

	
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
	 
	
	//피크닉 꿀팁 작성하는 메소드
	@PostMapping("/addBoardTip")
	@Transactional
	public String addBoardTip(@RequestBody BoardTipDTO tipDto, HttpServletResponse response) {
		System.out.println(111);
		
		BoardTip A = new BoardTip();
		A.setTipTitle(tipDto.getTipTitle());
		A.setTipContent(tipDto.getTipContent());
		A.setPuser(pur.findPuserByUserEmail("aa.gmail.com")); //저장된 로그인아이디로..?
		A.setTipImg(tipDto.getTipImg());
		
		btr.save(A);

		return null;
	}
	
	
	//피크닉 rent대여업체 작성하는 메소드 -> 미완성
	@PostMapping("/addBoardRent")
	@Transactional
	public String addBoardRent(@RequestBody BoardRentDTO rentDto, HttpServletResponse response) {
		System.out.println(222);
		
		BoardRent A = new BoardRent();
		A.setLocCate(lcr.findLocCategoryByLocId(1).get(0));
		A.setPuser(pur.findPuserByUserEmail("aa.gmail.com"));
		A.setRentCateName(rentDto.getRentCateName());
		A.setRentName(rentDto.getRentName());
		A.setRentContent(rentDto.getRentContent());
		A.setRentTime(rentDto.getRentTime());
		A.setRentPrice(rentDto.getRentPrice());
		A.setRentLink(rentDto.getRentLink());
		A.setRentImg(rentDto.getRentImg());
		A.setRentDel(0);
		brr.save(A);
		return null;
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
		pur.save(B);
		
		return "puser 저장 성공";
	}
	@GetMapping("delPUser")
	@Transactional
	public String delPUser(@RequestParam String userEmail) {
		Puser A = pur.findPuserByUserEmail(userEmail);
		A.setRoles("out"); // 로그인에서 확인
		A.setUserPassword("");
		A.setUserOut(1);		
		
		return "계정탈퇴되었습니다";
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
	@GetMapping("addLoc2")
	@Transactional
	public String addLocCate2( HttpServletResponse response, @RequestParam String locName, @RequestParam String sido, @RequestParam String sigungu, @RequestParam String address, @RequestParam String placeCategory) {
		LocCategory A = new LocCategory();
		A.setLocName(locName);
		A.setLocSido(sido);
		A.setLocSigungu(sigungu);
		A.setLocAddress(address);
		A.setPlaceCategory(placeCategory);
		lcr.save(A);
		try {
			response.sendRedirect("http://localhost/locCate.html");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "성공";
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
	
	@GetMapping("addplace2")
	@Transactional
	public String addBoardPlace2(HttpServletResponse response, @RequestParam String placeName, @RequestParam String placeLoc,@RequestParam String placeContent) {
		BoardPlace A = new BoardPlace();
		A.setPlaceName(placeName);
		System.out.println(lcr.findLocCategoryByLocName(placeLoc).get(0));
		if (lcr.findLocCategoryByLocName(placeLoc).get(0) != null){
			A.setLocCate(lcr.findLocCategoryByLocName(placeLoc).get(0));			
			A.setPlaceContent(placeContent);
			bpr.save(A);
		}else {
			try {
				response.sendRedirect("http://localhost/locCate.html");
				return "위치 데이터를 입력해주세요";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			response.sendRedirect("http://localhost/boardPlace.html");
			return "성공";
		} catch (IOException e) {
			e.printStackTrace();
		};
		return "???";
		
	}
	
	
//	@GetMapping("addBoardTip")
//	@Transactional
//	public String addBoardTip() {
//		BoardTip A = new BoardTip();
//		A.setTipId(1);
//		A.setTipTitle("tip title");
//		A.setTipContent("tip content");
//		A.setPuser(pur.findPuserByUserEmail("aa.gmail.com"));
//		A.setTipImg("img");
//		A.setTipLike(0);
//		A.setTipDel(0);
//				
//		btr.save(A);
//		
//		return "tip 저장 성공";
//	}
	
	@GetMapping("delBoardTip")
	@Transactional
	public String delBoardTip(@RequestParam String tipTitle) {
		BoardTip A = btr.findBoardTipByTipTitle(tipTitle).get(0);
		return tipTitle;
	}
	
	
	@GetMapping("addPComment")
	public String addPComment() {
		Pcomment A = new Pcomment();
		A.setCommentId(2);
		A.setPuser(pur.findPuserByUserEmail("aa.gmail.com"));
		A.setCommentContent("comment content");
		A.setCommentDel(0);
//		A.setBoardRent(brr.findBoardRentByRentId(1));
		A.setBoardTip(btr.findBoardTipByTipId(1).get(0));
		
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
		
		brer.save(A);
		
		return "리뷰 저장 성공";
	}

}
