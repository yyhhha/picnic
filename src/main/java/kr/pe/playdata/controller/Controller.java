package kr.pe.playdata.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import kr.pe.playdata.model.dto.BoardPlaceDTO;
import kr.pe.playdata.model.dto.BoardRentDTO;
import kr.pe.playdata.model.dto.BoardReviewDTO;
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

	@GetMapping("/signup")
	public void signin(HttpServletResponse response) {
		String redirect_uri = "http://localhost/signup.html";
		try {
			response.sendRedirect(redirect_uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/login")
	public void login(HttpServletResponse response) {
		String redirect_uri = "http://localhost/login.html";
		try {
			response.sendRedirect(redirect_uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**/

	// 피크닉 꿀팁 작성하는 메소드
	@PostMapping("/add/tip")
	@Transactional
	public String addBoardTip(HttpServletRequest request, @RequestBody BoardTipDTO tipDto, HttpServletResponse response) {
		System.out.println(111);
		HttpSession session = request.getSession();
		BoardTip A = new BoardTip();
		A.setTipTitle(tipDto.getTipTitle());
		A.setTipContent(tipDto.getTipContent());
		A.setPuser(pur.findPuserByUserEmail(session.getAttribute("userEmail").toString())); //Ycontroller에서 attribute값 지정
//		A.setPuser(pur.findPuserByUserEmail("ssss")); //Ycontroller에서 attribute값 지정
		A.setTipImg(tipDto.getTipImg());

		btr.save(A);

		return "";
	}
	


	//1) 수정을 하기 위해서 일단 id를 찾고, 그 아이디의 del_tip 값을 변경한다. , 나머지값은 그대로
	@PutMapping("/del/tips/{tipId}")
	@Transactional
	public boolean delBoardTip1(@PathVariable("tipId") int tipId) {
		System.out.println("수정");

		try {
			
			BoardTip tip = btr.findBoardTipByTipId(tipId);
			tip.setTipDel("1");
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	
	@PutMapping("/del/rents/{rentId}")
	@Transactional
	public boolean delBoardRent1(@PathVariable("rentId") int rentId) {
		System.out.println("렌트수정");

		try {	
			BoardRent rent = brr.findBoardRentByRentId(rentId);
			rent.setRentDel("1");
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@PutMapping("/del/places/{placeId}")
	@Transactional
	public boolean delBoardPlace1(@PathVariable("placeId") int placeId) {
		System.out.println("렌트수정");

		try {	
			BoardPlace place = bpr.findBoardPlaceByPlaceId(placeId);
			place.setPlaceDel("1");
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@PutMapping("/del/reviews/{reviewId}")
	@Transactional
	public boolean delBoardReview1(@PathVariable("reviewId") int reviewId) {
		System.out.println("리뷰 수정");

		try {
			BoardReview review = brer.findBoardReviewByReviewId(reviewId);
			review.setReviewDel("1");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@GetMapping("/del/tip")
	@Transactional
	public String delBoardTip(@RequestParam int tipId) {
		btr.findBoardTipByTipId(tipId).setTipDel("1");
		return "업체 게시글 삭제되었습니다.";
	}
	
	//피크닉 rent대여업체 작성하는 메소드
	@PostMapping("/add/rent")
	@Transactional
	public String addBoardRent(HttpServletRequest request,  @RequestBody BoardRentDTO rentDto, HttpServletResponse response) {
		System.out.println(222);
		HttpSession session = request.getSession();
		
		BoardRent A = new BoardRent();
		A.setLocCate(lcr.findLocCategoryByLocId(1).get(0));
		A.setPuser(pur.findPuserByUserEmail(session.getAttribute("userEmail").toString()));
		System.out.println("안녕");
		System.out.println(session.getAttribute("userEmail").toString());
//		A.setPuser(pur.findPuserByUserEmail("sss"));
		A.setRentCateName(rentDto.getRentCateName());
		A.setRentName(rentDto.getRentName());
		A.setRentContent(rentDto.getRentContent());
		A.setRentTime(rentDto.getRentTime());
		A.setRentPrice(rentDto.getRentPrice());
		A.setRentLink(rentDto.getRentLink());
		A.setRentImg(rentDto.getRentImg());
		A.setRentDel("0");
		brr.save(A);
		return null;
	}
	
	@GetMapping("/del/rent")
	@Transactional
	public String delBoardRent(@RequestParam int rentId) {
		brr.findBoardRentByRentId(rentId).setRentDel("1");
		return "업체 게시글 삭제되었습니다.";
	}

	// get으로 json 가져오는 방식
	@GetMapping("/board/tips")
	public List<BoardTipDTO> boardTipList() {
		Iterator<BoardTip> all = btr.findAll().iterator();
		System.out.println(btr.findAll().iterator());
		BoardTip tip;
		List<BoardTipDTO> test = new ArrayList<>();
		while (all.hasNext()) {
			tip = all.next();
			test.add(new BoardTipDTO(tip.getTipId(), tip.getPuser().getUserEmail(), tip.getTipTitle(),
					tip.getTipContent(), tip.getTipImg(), tip.getTipDate(), tip.getTipDel(), tip.getTipLike()));
		}

		return test;

	}
	
	


	//tip_id로 tip게시글 상세 get 가능
	@GetMapping("/board/tips/{tipId}")
		public BoardTipDTO boardTipDetail(@PathVariable("tipId") int tipId) {
			BoardTip all = btr.findBoardTipByTipId(tipId);
			
			
			BoardTipDTO board = new BoardTipDTO(all.getTipId(),all.getPuser().getUserEmail() ,all.getTipTitle(), 
					all.getTipContent(),all.getTipImg(),all.getTipDate(),all.getTipDel(),all.getTipLike());
			
			
			return board;
//			return null;
	}
	
	
	//rent_id로 rent게시글 상세 get 가능
	@GetMapping("/board/rents/{rentId}")
		public BoardRentDTO boardRentDetail(@PathVariable("rentId") int rentId) {
			BoardRent rent = brr.findBoardRentByRentId(rentId);
			
			BoardRentDTO board = new BoardRentDTO(rent.getRentId(),rent.getRentCateName(),rent.getLocCate().getLocName(),rent.getPuser().getUserEmail(),rent.getRentName(),
					rent.getRentLink(),rent.getRentPrice(),rent.getRentTime(),rent.getRentContent(),
					rent.getRentImg(),rent.getRentDel());
			
			return board;
	}
	
	//place_id로 place게시글 상세 get가능
	@GetMapping("/board/places/{placeId}")
		public BoardPlaceDTO boardPlaceDetail(@PathVariable("placeId") int placeId) {
		BoardPlace place = bpr.findBoardPlaceByPlaceId(placeId);
		
		BoardPlaceDTO board = new BoardPlaceDTO(place.getPlaceId(),place.getLocCate().getLocName(),place.getPuser().getUserEmail(),
				place.getPlaceName(),place.getPlaceContent(),place.getPlaceImg(),place.getPlaceDel());
		return board;
}

	
	//review_id로 review게시글 상세 get가능
	@GetMapping("/board/reviews/{reviewId}")
		public BoardReviewDTO boardReviewDetail(@PathVariable("reviewId") int reviewId) {
		BoardReview review = brer.findBoardReviewByReviewId(reviewId);
		
		BoardReviewDTO board = new BoardReviewDTO(review.getReviewId(),review.getBoardPlace().getPlaceName(),review.getPuser().getUserEmail(),
				review.getReviewTitle(),review.getReviewContent(),review.getReviewDate(),review.getReviewDel(),review.getReviewScore());
		
		return board;
}
	

	@GetMapping("add/PUser") //test용
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

	@GetMapping("/del/PUser")
	@Transactional
	public String delPUser(@RequestParam String userEmail) {
		System.out.println(userEmail);
		Date to = null;
		try {
			to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.nnn").parse(LocalDate.now(ZoneId.of("Asia/Seoul"))+LocalTime.now(ZoneId.of("Asia/Seoul")).toString());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Puser A = null;
		A = pur.findPuserByUserEmail(userEmail);
		A.setRoles("out"); // 로그인에서 확인
		A.setOutDate(to);
		A.setUserOut(1);

		pur.save(A);
//		pur.delete(A);
		System.out.println(A);

		return null;
	}

	@GetMapping("/add/loc2") // test용
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

	@GetMapping("/add/loc")
	@Transactional
	public String addLocCate2(HttpServletResponse response, @RequestParam String locName, @RequestParam String sido,
			@RequestParam String sigungu, @RequestParam String address, @RequestParam String placeCategory) {
		LocCategory A = new LocCategory();
		A.setLocName(locName);
		A.setLocSido(sido);
		A.setLocSigungu(sigungu);
		A.setLocAddress(address);
		A.setPlaceCategory(placeCategory);
		lcr.save(A);
		try {
			response.sendRedirect("http://localhost/writepage/locCate.html");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "성공";
	}

	@GetMapping("/del/loc")
	@Transactional
	public String delLocCate(@RequestParam int locId) {
		lcr.deleteById(locId); // 필요없는 카테고리 삭제용
		return "장소 카테고리 삭제되었습니다.";
	}

	@GetMapping("/add/place") // test용
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

	@GetMapping("/add/place2")
	@Transactional
	public String addBoardPlace2(HttpServletRequest request, HttpServletResponse response, @RequestParam String placeName,
			@RequestParam String placeLoc, @RequestParam String placeContent,@RequestParam String placeImg) {
		
		HttpSession session = request.getSession();
		BoardPlace A = new BoardPlace();
		
		if (lcr.findLocCategoryByLocName(placeLoc) != null) {
			A.setPlaceName(placeName);
			A.setLocCate(lcr.findLocCategoryByLocName(placeLoc).get(0));
			A.setPlaceContent(placeContent);
			A.setPuser(pur.findPuserByUserEmail(session.getAttribute("userEmail").toString()));
			System.out.println(session.getAttribute("userEmail").toString());
//			A.setPuser(pur.findPuserByUserEmail("ssss"));
			A.setPlaceImg(placeImg);
			bpr.save(A);
			System.out.println(lcr.findLocCategoryByLocName(placeLoc));
		} else {
			try {
				response.sendRedirect("http://localhost/writepage/locCate.html");
				return "위치 데이터를 입력해주세요";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			response.sendRedirect("http://localhost/writepage/boardPlace.html");
			return "성공";
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
		return "???";

	}

	@GetMapping("/del/place")
	@Transactional
	public String delBoardPlace(@RequestParam int placeId) {
		bpr.findBoardPlaceByPlaceId(placeId).setPlaceDel("1");
		return "장소 게시글 삭제되었습니다.";
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


	

//	@GetMapping("delBoardTip")
//	@Transactional
//	public String delBoardTip(@RequestParam String tipTitle) {
//		BoardTip A = btr.findBoardTipByTipTitle(tipTitle).get(0);
//		return tipTitle;
//	}
	
	
	@GetMapping("/add/PComment")
	public String addPComment() {
		Pcomment A = new Pcomment();
		A.setCommentId(2);
		A.setPuser(pur.findPuserByUserEmail("aa.gmail.com"));
		A.setCommentContent("comment content");
		A.setCommentDel(0);
//		A.setBoardRent(brr.findBoardRentByRentId(1));
		A.setBoardTip(btr.findBoardTipByTipId(1));

		pcor.save(A);

		return "comment 저장 성공";
	}

	@GetMapping("/del/Pcomment")
	@Transactional
	public String delPcomment(@RequestParam int commentid) {
		Pcomment A = pcor.findPcommentByCommentId(commentid).get(0);
		A.setCommentDel(1);
		return "댓글 삭제되었습니다.";
	}


// review

	@GetMapping("/add/review")
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

	@GetMapping("/del/review")
	@Transactional
	public String delBoardReview(@RequestParam int reviewId) {
		BoardReview A = brer.findBoardReviewByReviewId(reviewId);
		A.setReviewDel("1");
		return "리뷰 삭제되었습니다.";
	}
	
	
	//작성자 확인 메소드
	//vue로 작성자만 넘기고, vue에서는 storageseesion값을 비교해서 맞으면 v-show 
	@GetMapping("/check/tipwriter/{tipId}")
	public String checkTWriter(@PathVariable("tipId") int tipId) {
		BoardTip tip = btr.findBoardTipByTipId(tipId);
				
		return tip.getPuser().getUserEmail();
		
	}
	
	//작성자 확인 메소드
	@GetMapping("/check/rentwriter/{rentId}")
	public String checkRWriter(@PathVariable("rentId") int rentId) {
		BoardRent rent = brr.findBoardRentByRentId(rentId);
				
		return rent.getPuser().getUserEmail();
		
	}
	
	//작성자 확인 메소드
	@GetMapping("/check/reviewwriter/{reviewId}")
	public String checkRVWriter(@PathVariable("reviewId") int reviewId) {
		BoardReview review = brer.findBoardReviewByReviewId(reviewId);
				
		return review.getPuser().getUserEmail();
		
	}
	
	//작성자 확인 메소드
	@GetMapping("/check/placewriter/{placeId}")
	public String checkPWriter(@PathVariable("placeId") int placeId) {
		BoardPlace place = bpr.findBoardPlaceByPlaceId(placeId);
				
		return place.getPuser().getUserEmail();
		
	}
	
	
	
}
