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
	
	//get으로 json 가져오는 방식
	@GetMapping("/boardrents")
	public List<BoardRentDTO> findBoardRentList() {
		Iterator<BoardRent> all = brr.findAll().iterator();
	
		BoardRent rent;
		List<BoardRentDTO> test = new ArrayList<>();
		while (all.hasNext()) {
			rent = all.next();
			test.add(new BoardRentDTO(rent.getRentId(),rent.getLocCate().getLocName(),rent.getPuser().getUserEmail(),rent.getRentName(), 
					rent.getRentLink(),rent.getRentPrice(),rent.getRentTime(),rent.getRentContent(),rent.getRentImg(), rent.getRentDel()));
		}
	
		return test;
	}
	
	
	//get으로 json 가져오는 방식
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
	
	
	//get으로 json 가져오는 방식
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
	
	
	
	/* 피크닉 물품대여 read (all)  @RequestParam String command*/
	@GetMapping("/boardrentpage2")
	@Transactional
	public JSONArray findBoardRentList2(){
		List<BoardRent> al = new ArrayList<>();
		
		JSONParser jsonParse = new JSONParser();
		JSONArray array = null;
		
//		if(command == "all") {
			al.addAll(brr.findAll());
			try {
				array = (JSONArray) jsonParse.parse(al.toString());
				return array;
			} catch (ParseException e) {
				System.out.println("변환에 실패");
				e.printStackTrace();
			}
			return array;
//		}else if(command == "한강 피크닉") {
//			al.addAll(brr.findBoardRentByRentCateName(command));
//			try {
//				array = (JSONArray) jsonParse.parse(al.toString());
//				return array;
//			} catch (ParseException e) {
//				System.out.println("변환에 실패");
//				e.printStackTrace();
//			}
//			return array;
//		}else if(command == "바다 피크닉") {
//			
//		}else if(command == "글램핑") {
//			
//		}
		
//		return null;
	}
	
	
	/* 피크닉 장소추천 read (all)  @RequestParam String command*/
	@GetMapping("/boardplacepage2")
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
	@GetMapping("/boardreviewpage2")
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
	@GetMapping("/boardtippage2")
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
	


