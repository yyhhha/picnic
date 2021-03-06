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

import io.swagger.annotations.ApiOperation;
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
public class MainController {

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



	@ApiOperation(value = "review ????????? ??????", notes = "review ????????? ??????, ???????????? ????????? ?????? ??????")
	@PostMapping("/add/review")
	@Transactional
	public String addBoardReviews(HttpServletRequest request, @RequestBody BoardReviewDTO reviewDto,
			HttpServletResponse response) {
		HttpSession session = request.getSession();

		BoardReview board = new BoardReview();

		board.setReviewTitle(reviewDto.getReviewTitle());
		board.setPuser(pur.findPuserByUserEmail(session.getAttribute("userEmail").toString()));
		board.setBoardPlace(bpr.findBoardPlaceByPlaceId(reviewDto.getPlaceId()));
		board.setReviewContent(reviewDto.getReviewContent());
		board.setReviewScore(reviewDto.getReviewScore());

		brer.save(board);

		return "";
	}

	
	@ApiOperation(value = "tip ????????? ??????", notes = "tip ????????? ??????, ???????????? ????????? ?????? ??????")
	@PostMapping("/add/tip")
	@Transactional
	public String addBoardTip(HttpServletRequest request, @RequestBody BoardTipDTO tipDto,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		BoardTip A = new BoardTip();
		A.setTipTitle(tipDto.getTipTitle());
		A.setTipContent(tipDto.getTipContent());
		A.setPuser(pur.findPuserByUserEmail(session.getAttribute("userEmail").toString()));
		A.setTipImg(tipDto.getTipImg());

		btr.save(A);

		return "";
	}

	// 1) ????????? ?????? ????????? ?????? id??? ??????, ??? ???????????? del_tip ?????? ????????????. , ??????????????? ?????????
	@ApiOperation(value = "tip ????????? ??????", notes = "Tip_del??? 0?????? 1??? ??????")
	@PutMapping("/del/tips/{tipId}")
	@Transactional
	public void delBoardTip1(@PathVariable("tipId") int tipId, HttpServletResponse response) throws IOException {
		System.out.println("??????");

		try {
			BoardTip tip = btr.findBoardTipByTipId(tipId);
			tip.setTipDel("1");
			response.sendRedirect("http://localhost/listpage/boardTipPage.html");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@ApiOperation(value = "rent ????????? ??????", notes = "rent_del??? 0?????? 1??? ??????")
	@PutMapping("/del/rents/{rentId}")
	@Transactional
	public boolean delBoardRent1(@PathVariable("rentId") int rentId) {
		System.out.println("????????????");

		try {
			BoardRent rent = brr.findBoardRentByRentId(rentId);
			rent.setRentDel("1");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@ApiOperation(value = "place ????????? ??????", notes = "place_del??? 0?????? 1??? ??????")
	@PutMapping("/del/places/{placeId}")
	@Transactional
	public boolean delBoardPlace1(@PathVariable("placeId") int placeId) {
		System.out.println("????????????");

		try {
			BoardPlace place = bpr.findBoardPlaceByPlaceId(placeId);
			place.setPlaceDel("1");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@ApiOperation(value = "review ????????? ??????", notes = "review_del??? 0?????? 1??? ??????")
	@PutMapping("/del/reviews/{reviewId}")
	@Transactional
	public boolean delBoardReview1(@PathVariable("reviewId") int reviewId) {
		System.out.println("?????? ??????");

		try {
			BoardReview review = brer.findBoardReviewByReviewId(reviewId);
			review.setReviewDel("1");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@ApiOperation(value = "????????? tip ????????? ??????", notes = "tip_del??? 0?????? 1??? ??????")
	@GetMapping("/del/tip")
	@Transactional
	public String delBoardTip(@RequestParam int tipId) {
		btr.findBoardTipByTipId(tipId).setTipDel("1");
		return "?????? ????????? ?????????????????????.";
	}

	// ????????? rent???????????? ???????????? ?????????
	@ApiOperation(value = "rent ????????? ??????", notes = "rent ????????? ??????, ???????????? ????????? ?????? ??????")
	@PostMapping("/add/rent")
	@Transactional
	public String addBoardRent(HttpServletRequest request, @RequestBody BoardRentDTO rentDto,
			HttpServletResponse response) {
		System.out.println(222);
		HttpSession session = request.getSession();

		BoardRent A = new BoardRent();
		A.setLocCate(lcr.findLocCategoryByLocId(1).get(0));
		A.setPuser(pur.findPuserByUserEmail(session.getAttribute("userEmail").toString()));
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

	@ApiOperation(value = "????????? rent ????????? ??????", notes = "rent_del??? 0?????? 1??? ??????")
	@GetMapping("/del/rent")
	@Transactional
	public String delBoardRent(@RequestParam int rentId) {
		brr.findBoardRentByRentId(rentId).setRentDel("1");
		return "?????? ????????? ?????????????????????.";
	}

	@ApiOperation(value = "tip ????????? ??????", notes = "tip ????????? ??????, ???????????? ????????? ?????? ??????")
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

	@ApiOperation(value = "????????? ??? ????????? +1", notes = "API ?????? ?????? : ????????? ??? ????????? ????????? counting + 1")
	@PutMapping("/add/like")
	public String plusLike(@RequestParam int tipId) {
		BoardTip A = btr.findBoardTipByTipId(tipId);
		A.setTipLike(A.getTipLike() + 1);
		btr.save(A);
		return null;
	}

	@ApiOperation(value = "????????? ??? ????????? -1", notes = "API ?????? ?????? : ????????? ??? ????????? ????????? counting - 1")
	@PutMapping("/del/like")
	public String minusLike(@RequestParam int tipId) {
		BoardTip A = btr.findBoardTipByTipId(tipId);
		A.setTipLike(A.getTipLike() - 1);
		btr.save(A);
		return null;
	}

	// tip_id??? tip????????? ?????? get ??????
	@ApiOperation(value = "????????? ?????? ??????????????? ??????", notes = "API ?????? ?????? : tip_id??? ????????? ?????? ??????????????? ??????")
	@GetMapping("/board/tips/{tipId}")
	public BoardTipDTO boardTipDetail(@PathVariable("tipId") int tipId) {
		BoardTip all = btr.findBoardTipByTipId(tipId);

		BoardTipDTO board = new BoardTipDTO(all.getTipId(), all.getPuser().getUserEmail(), all.getTipTitle(),
				all.getTipContent(), all.getTipImg(), all.getTipDate(), all.getTipDel(), all.getTipLike());

		return board;
//			return null;
	}

	// rent_id??? rent????????? ?????? get ??????
	@ApiOperation(value = "????????? ???????????? ??????????????? ??????", notes = "API ?????? ?????? : rent_id??? ????????? ???????????? ??????????????? ??????")
	@GetMapping("/board/rents/{rentId}")
	public BoardRentDTO boardRentDetail(@PathVariable("rentId") int rentId) {
		BoardRent rent = brr.findBoardRentByRentId(rentId);

		BoardRentDTO board = new BoardRentDTO(rent.getRentId(), rent.getRentCateName(), rent.getLocCate().getLocName(),
				rent.getPuser().getUserEmail(), rent.getRentName(), rent.getRentLink(), rent.getRentPrice(),
				rent.getRentTime(), rent.getRentContent(), rent.getRentImg(), rent.getRentDel());

		return board;
	}

	// place_id??? place????????? ?????? get??????
	@ApiOperation(value = "????????? ???????????? ??????????????? ??????", notes = "API ?????? ?????? : place_id??? ????????? ???????????? ??????????????? ??????")
	@GetMapping("/board/places/{placeId}")
	public BoardPlaceDTO boardPlaceDetail(@PathVariable("placeId") int placeId) {
		BoardPlace place = bpr.findBoardPlaceByPlaceId(placeId);

		BoardPlaceDTO board = new BoardPlaceDTO(place.getPlaceId(), place.getLocCate().getLocName(),
				place.getPuser().getUserEmail(), place.getPlaceName(), place.getPlaceContent(), place.getPlaceImg(),
				place.getPlaceDel());
		return board;
	}

	// review_id??? review????????? ?????? get??????
	@ApiOperation(value = "????????? ???????????? ??????????????? ??????", notes = "API ?????? ?????? : review_id??? ????????? ???????????? ??????????????? ??????")
	@GetMapping("/board/reviews/{reviewId}")
	public BoardReviewDTO boardReviewDetail(@PathVariable("reviewId") int reviewId) {
		BoardReview review = brer.findBoardReviewByReviewId(reviewId);

		BoardReviewDTO board = new BoardReviewDTO(review.getReviewId(), review.getBoardPlace().getPlaceId(),
				review.getPuser().getUserEmail(), review.getReviewTitle(), review.getReviewContent(),
				review.getReviewDate(), review.getReviewDel(), review.getReviewScore());

		return board;
	}

	@ApiOperation(value = "?????? ??????", notes = "API ?????? ?????? : ????????? ????????? ???????????? ?????????????????? ???????????? ????????? ????????? ??????")
	@GetMapping("/del/PUser")
	@Transactional
	public String delPUser(@RequestParam String userEmail) {
		System.out.println(userEmail);
		String to = null;
		to = LocalDate.now(ZoneId.of("Asia/Seoul")) + " " + LocalTime.now(ZoneId.of("Asia/Seoul")).toString();

		Puser A = null;
		A = pur.findPuserByUserEmail(userEmail);
		A.setRoles("out"); // ??????????????? ??????
		A.setOutDate(to);
		A.setUserOut(1);

		pur.save(A);
//		pur.delete(A);
		System.out.println(A);

		return null;
	}
	
	@ApiOperation(value = "?????? ??????", notes = "API ?????? ?????? : ?????? ????????? ?????? ?????? ")
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

		return "??????";
	}

	@ApiOperation(value = "?????? ??????", notes = "API ?????? ?????? : ???????????? ?????? ??????")
	@GetMapping("/del/loc")
	@Transactional
	public String delLocCate(@RequestParam int locId) {
		lcr.deleteById(locId); // ???????????? ???????????? ?????????
		return "?????? ???????????? ?????????????????????.";
	}

	@ApiOperation(value = "?????? ??????", notes = "API ?????? ?????? : ???????????? ???????????? ?????? ?????? ?????? ")
	@GetMapping("/add/place2")
	@Transactional
	public String addBoardPlace2(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String placeName, @RequestParam String placeLoc, @RequestParam String placeContent,
			@RequestParam String placeImg) {

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
				return "?????? ???????????? ??????????????????";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			response.sendRedirect("http://localhost/listpage/boardPlacePage.html");
			return "??????";
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
		return "???";

	}

	@ApiOperation(value = "?????? ??????", notes = "API ?????? ?????? : ????????? ????????? ???????????? ?????????????????? ?????? ")
	@GetMapping("/del/place")
	@Transactional
	public String delBoardPlace(@RequestParam int placeId) {
		bpr.findBoardPlaceByPlaceId(placeId).setPlaceDel("1");
		return "?????? ????????? ?????????????????????.";
	}

	@ApiOperation(value = "?????? ??????", notes = "API ?????? ?????? : ????????? ????????? ???????????? ?????????????????? ?????? ")
	@GetMapping("/del/Pcomment")
	@Transactional
	public String delPcomment(@RequestParam int commentid) {
		Pcomment A = pcor.findPcommentByCommentId(commentid).get(0);
		A.setCommentDel(1);
		return "?????? ?????????????????????.";
	}

	@ApiOperation(value = "?????? ??????", notes = "API ?????? ?????? : ?????? ?????? ???????????? ?????? ?????? ")
	@GetMapping("/add/review2")
	public String addBoardReview() {
		BoardReview A = new BoardReview();

		A.setReviewId(1);
		A.setPuser(pur.findPuserByUserEmail("aa.gmail.com"));
		A.setReviewTitle("review title");
		A.setReviewContent("review content");

		A.setReviewScore(0);

		brer.save(A);

		return "?????? ?????? ??????";
	}

	@ApiOperation(value = "?????? ??????", notes = "API ?????? ?????? : ????????? ????????? ???????????? ???????????? ?????? ????????? ?????? ")
	@GetMapping("/del/review")
	@Transactional
	public String delBoardReview(@RequestParam int reviewId) {
		BoardReview A = brer.findBoardReviewByReviewId(reviewId);
		A.setReviewDel("1");
		return "?????? ?????????????????????.";
	}

	// ????????? ?????? ?????????
	// vue??? ???????????? ?????????, vue????????? storageseesion?????? ???????????? ????????? v-show
	@ApiOperation(value = "????????? ??????", notes = "API ?????? ?????? : ???????????? ???????????? ??????????????? ??????. ")
	@GetMapping("/check/tipwriter/{tipId}")
	public String checkTWriter(@PathVariable("tipId") int tipId) {
		BoardTip tip = btr.findBoardTipByTipId(tipId);

		return tip.getPuser().getUserEmail();

	}

	// ????????? ?????? ?????????
	@ApiOperation(value = "????????? ??????", notes = "API ?????? ?????? : ???????????? ???????????? ??????????????? ??????. ")
	@GetMapping("/check/rentwriter/{rentId}")
	public String checkRWriter(@PathVariable("rentId") int rentId) {
		BoardRent rent = brr.findBoardRentByRentId(rentId);

		return rent.getPuser().getUserEmail();

	}

	// ????????? ?????? ?????????
	@ApiOperation(value = "????????? ??????", notes = "API ?????? ?????? : ???????????? ???????????? ??????????????? ??????. ")
	@GetMapping("/check/reviewwriter/{reviewId}")
	public String checkRVWriter(@PathVariable("reviewId") int reviewId) {
		BoardReview review = brer.findBoardReviewByReviewId(reviewId);

		return review.getPuser().getUserEmail();

	}

	// ????????? ?????? ?????????
	@ApiOperation(value = "????????? ??????", notes = "API ?????? ?????? : ???????????? ???????????? ??????????????? ??????. ")
	@GetMapping("/check/placewriter/{placeId}")
	public String checkPWriter(@PathVariable("placeId") int placeId) {
		BoardPlace place = bpr.findBoardPlaceByPlaceId(placeId);

		return place.getPuser().getUserEmail();

	}

}
