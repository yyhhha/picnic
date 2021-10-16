package kr.pe.playdata.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
import kr.pe.playdata.model.dto.BoardPlaceDTO;
import kr.pe.playdata.model.dto.BoardRentDTO;
import kr.pe.playdata.model.dto.BoardReviewDTO;
import kr.pe.playdata.model.dto.BoardTipDTO;

//@CrossOrigin(origins = "http://localhost:80")
@RestController
@RequestMapping("/bcon")
public class BController {

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
	
// ============================ 아래는 row-main
	
	
//	@GetMapping("/boardrents")
	public List<BoardRentDTO> findBoardRentList() {
		Iterator<BoardRent> all = brr.findAll().iterator();
	
		BoardRent rent;
		List<BoardRentDTO> test = new ArrayList<>();
		while (all.hasNext()) {
			rent = all.next();
			test.add(new BoardRentDTO(rent.getRentId(),rent.getRentCateName(),rent.getLocCate().getLocName(),rent.getPuser().getUserEmail(),rent.getRentName(),
					rent.getRentLink(),rent.getRentPrice(),rent.getRentTime(),rent.getRentContent(),
					rent.getRentImg(),rent.getRentDel()));
		}
		return test;
	}
	
	
	@GetMapping("/boardplaces")
	public List<BoardPlaceDTO> findBoardPlaceList() {
		Iterator<BoardPlace> all = bpr.findAll().iterator();
	
		BoardPlace place;
		List<BoardPlaceDTO> test = new ArrayList<>();
		while (all.hasNext()) {
			place = all.next();
			test.add(new BoardPlaceDTO(place.getPlaceId(),place.getLocCate().getLocName(),place.getPuser().getUserEmail(),place.getPlaceName(), 
					place.getPlaceContent(),place.getPlaceImg(),place.getPlaceDel()));
		}
		return test;
	}
	
	
	@GetMapping("/boardreviews")
	public List<BoardReviewDTO> findBoardReviewList() {
		Iterator<BoardReview> all = brer.findAll().iterator();
	
		BoardReview review;
		List<BoardReviewDTO> test = new ArrayList<>();
		while (all.hasNext()) {
			review = all.next();
			test.add(new BoardReviewDTO(review.getReviewId(),review.getBoardPlace().getPlaceName(),review.getPuser().getUserEmail(),review.getReviewTitle(), 
					review.getReviewContent(),review.getReviewDate(),review.getReviewDel(),review.getReviewScore()));
		}
		return test;
	}
		
	
	//get으로 json 가져오는 방식
	@GetMapping("/boardtips")
	public List<BoardTipDTO> findBoardTipList() {
		Iterator<BoardTip> all = btr.findAll().iterator();
	
		BoardTip tip;
		List<BoardTipDTO> test = new ArrayList<>();
		while (all.hasNext()) {
			tip = all.next();
			test.add(new BoardTipDTO(tip.getTipId(),tip.getPuser().getUserEmail() ,tip.getTipTitle(), 
					tip.getTipContent(),tip.getTipImg(),tip.getTipDate(),tip.getTipDel(),tip.getTipLike()));
		}
		return test;
	}

	
// ============================ 아래는 sidebar
	
	
//	@GetMapping("/rent")
	public List<BoardRentDTO> findBoardRentListByRentCateName(String rentCateName) {
		Iterator<BoardRent> han = brr.findBoardRentByRentCateName(rentCateName).iterator();
	
		BoardRent rent;
		List<BoardRentDTO> test = new ArrayList<>();
		while (han.hasNext()) {
			rent = han.next();
			test.add(new BoardRentDTO(rent.getRentId(),rent.getRentCateName(),rent.getLocCate().getLocName(),rent.getPuser().getUserEmail(),rent.getRentName(),
					rent.getRentLink(),rent.getRentPrice(),rent.getRentTime(),rent.getRentContent(),
					rent.getRentImg(),rent.getRentDel()));
		}
		return test;
	}
	
////	@GetMapping("/place")
//	public List<BoardPlaceDTO> findBoardPlaceListByRentCateName(String rentCateName) {
//		Iterator<BoardPlace> han = bpr.findBoardPlaceByRentCateName(rentCateName).iterator();
//	
//		BoardPlace place;
//		List<BoardPlaceDTO> test = new ArrayList<>();
//		while (han.hasNext()) {
//			place = han.next();
//			test.add(new BoardPlaceDTO(place.getPlaceId(),place.getLocCate().getLocName(),place.getPuser().getUserEmail(),place.getPlaceName(), 
//					place.getPlaceContent(),place.getPlaceImg(),place.getPlaceDel()));
//		}
//		return test;
//	}
	
////	@GetMapping("/review")
//	public List<BoardReviewDTO> findBoardReviewListByRentCateName(String rentCateName) {
//		Iterator<BoardReview> han = brer.findBoardTipByRentCateName(rentCateName).iterator();
//	
//		BoardReview review;
//		List<BoardReviewDTO> test = new ArrayList<>();
//		while (han.hasNext()) {
//			review = han.next();
//			test.add(new BoardReviewDTO(review.getReviewId(),review.getBoardPlace().getPlaceName(),review.getPuser().getUserEmail(),review.getReviewTitle(), 
//					review.getReviewContent(),review.getReviewDate(),review.getReviewDel(),review.getReviewScore()));
//		}
//		return test;
//	}
//	
////	@GetMapping("/tip")
//	public List<BoardTipDTO> findBoardTipListByRentCateName(String rentCateName) {
//		Iterator<BoardTip> han = btr.findBoardTipByRentCateName(rentCateName).iterator();
//	
//		BoardTip tip;
//		List<BoardTipDTO> test = new ArrayList<>();
//		while (han.hasNext()) {
//			tip = han.next();
//			test.add(new BoardTipDTO(tip.getTipId(),tip.getPuser().getUserEmail() ,tip.getTipTitle(), 
//					tip.getTipContent(),tip.getTipImg(),tip.getTipDate(),tip.getTipDel(),tip.getTipLike()));
//		}
//		return test;
//	}
	
	
	
	/* rent */
	@GetMapping("/boardrentpage22")
	public List<BoardRentDTO> findBoardRentList22(@RequestParam String command, @RequestParam String rentCateName) {
		List<BoardRentDTO> rent = null;
		
		/* rent */
		if(command.equals("rent")) {
			if(rentCateName.equals("한강 피크닉")) {
				rent = findBoardRentListByRentCateName(rentCateName);
			}else if(rentCateName.equals("바다 피크닉")) {
				rent = findBoardRentListByRentCateName(rentCateName);
			}else if(rentCateName.equals("글램핑")) {
				rent = findBoardRentListByRentCateName(rentCateName);
			}else {
				rent = findBoardRentList();
			}
		}
		return rent;
	}
	
	
//	/* place */
//	@GetMapping("/boardplacepage22")
//	public List<BoardPlaceDTO> findBoardPlaceList22(@RequestParam String command, @RequestParam String rentCateName) {
//		List<BoardPlaceDTO> place = null;
//		
//		if(command.equals("place")) {
//			if(rentCateName.equals("한강 피크닉")) {
//				place = findBoardPlaceListByRentCateName(rentCateName);
//			}else if(rentCateName.equals("바다 피크닉")) {
//				place = findBoardPlaceListByRentCateName(rentCateName);
//			}else if(rentCateName.equals("글램핑")) {
//				place = findBoardPlaceListByRentCateName(rentCateName);
//			}else {
//				place = findBoardPlaceList();
//			}
//		}
//		return place;
//	}
	
	
//	/* review */
//	@GetMapping("/boardreviewpage22")
//	public List<BoardReviewDTO> findBoardReviewList22(@RequestParam String command, @RequestParam String rentCateName) {
//		List<BoardReviewDTO> review = null;
//		
//		if(command.equals("review")) {
//			if(rentCateName.equals("한강 피크닉")) {
//				review = findBoardReviewListByRentCateName(rentCateName);
//			}else if(rentCateName.equals("바다 피크닉")) {
//				review = findBoardReviewListByRentCateName(rentCateName);
//			}else if(rentCateName.equals("글램핑")) {
//				review = findBoardReviewListByRentCateName(rentCateName);
//			}else {
//				review = findBoardReviewList();
//			}
//		}
//		return review;
//	}
//	
//	
//	/* tip */
//	@GetMapping("/boardtippage22")
//	public List<BoardTipDTO> findBoardTipList22(@RequestParam String command, @RequestParam String rentCateName) {
//		List<BoardTipDTO> tip = null;
//		
//		if(command.equals("tip")) {
//			if(rentCateName.equals("한강 피크닉")) {
//				tip = findBoardTipListByRentCateName(rentCateName);
//			}else if(rentCateName.equals("바다 피크닉")) {
//				tip = findBoardTipListByRentCateName(rentCateName);
//			}else if(rentCateName.equals("글램핑")) {
//				tip = findBoardTipListByRentCateName(rentCateName);
//			}else {
//				tip = findBoardTipList();
//			}
//		}
//		return tip;
//	}
	
	
	
	

	
	
	
	
	
	
	
	
// ----------------------------------------------------
	
	
	
	
	/* 피크닉 물품대여 read (all)  */
	@GetMapping("/boardrentpage2")
	@Transactional
	public JSONArray findBoardRentList2(){
		List<BoardRent> al = new ArrayList<>();
		
		JSONParser jsonParse = new JSONParser();
		JSONArray array = null;
		
//		if(command.equals("all") {
			al.addAll(brr.findAll());
			try {
				array = (JSONArray) jsonParse.parse(al.toString());
				return array;
			} catch (ParseException e) {
				System.out.println("변환에 실패");
				e.printStackTrace();
			}
			return array;
//		}else if(command.equals("한강 피크닉") {
//			al.addAll(brr.findBoardRentByRentCateName(command));
//			try {
//				array = (JSONArray) jsonParse.parse(al.toString());
//				return array;
//			} catch (ParseException e) {
//				System.out.println("변환에 실패");
//				e.printStackTrace();
//			}
//			return array;
//		}else if(command.equals("바다 피크닉") {
//			
//		}else if(command.equals("글램핑") {
//			
//		}
		
//		return null;
	}
	
	
	/* 피크닉 장소추천 read (all)  @RequestParam String command*/
	@GetMapping("/boardplacepage22")
	@Transactional
	public JSONArray findBoardPlaceAll4(){
		List<BoardPlace> al = new ArrayList<>();
		
		JSONParser jsonParse = new JSONParser();
		JSONArray array = null;
		
		al.addAll(bpr.findAll());
		try {
			array = (JSONArray) jsonParse.parse(al.toString());
			return array;
		} catch (ParseException e) {
			System.out.println("변환에 실패");
			e.printStackTrace();
		}
		return array;
	}
	
	
	/* 피크닉 장소후기 read (all)  @RequestParam String command*/
	@GetMapping("/boardreviewpage22")
	@Transactional
	public JSONArray findBoardReviewAll4(){
		List<BoardReview> al = new ArrayList<>();
		
		JSONParser jsonParse = new JSONParser();
		JSONArray array = null;
		
		al.addAll(brer.findAll());
		try {
			array = (JSONArray) jsonParse.parse(al.toString());
			return array;
		} catch (ParseException e) {
			System.out.println("변환에 실패");
			e.printStackTrace();
		}
		return array;
	}
	
	
	/* 피크닉 꿀팁 read (all)  @RequestParam String command*/
	@GetMapping("/boardtippage22")
	@Transactional
	public JSONArray findBoardTipAll4(){
		List<BoardTip> al = new ArrayList<>();
		
		JSONParser jsonParse = new JSONParser();
		JSONArray array = null;
		
		al.addAll(btr.findAll());
		try {
			array = (JSONArray) jsonParse.parse(al.toString());
			return array;
		} catch (ParseException e) {
			System.out.println("변환에 실패");
			e.printStackTrace();
		}
		return array;
	}
	
}




//	/* read (all) */
//	@GetMapping("/boardrentpage222")
//	@Transactional
//	public String findBoardRentAll3(){ // toString 재정의 안됨
//		List<BoardRent> al = new ArrayList<>();
//		
//		al.addAll(brr.findAll());
//		
//		return al.toString();
//	}
	


