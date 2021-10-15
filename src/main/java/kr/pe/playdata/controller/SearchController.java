package kr.pe.playdata.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/scon")
public class SearchController {

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
	
	
	@GetMapping("/searchAll")
	@ResponseBody
	public JSONArray searchAll(@RequestParam String searchString) {
		JSONParser jsonParse = new JSONParser();
		Object arrayA = null;
		Object arrayB = null;
		Object arrayC = null;
		Object arrayD = null;
		
		List<BoardPlace> A = bpr.findBoardPlaceByPlaceNameContaining(searchString);
		List<BoardRent> B = brr.findBoardRentByRentNameContaining(searchString);
		List<BoardReview> C = brer.findBoardReviewByReviewTitleContaining(searchString);
		List<BoardTip> D = btr.findBoardTipByTipTitleContaining(searchString);
		
		try {
			arrayA = jsonParse.parse(A.toString());
			arrayB = jsonParse.parse(B.toString());
			arrayC = jsonParse.parse(C.toString());
			arrayD = jsonParse.parse(D.toString());
		} catch (ParseException e) {//+B.toString()+C.toString()+D.toString()
			e.printStackTrace();
		}
//		List<Object> all = new ArrayList<>();
//		all.add(0,A);
//		all.add(1,B);
//		all.add(2,C);
//		all.add(3,D);
		String all = null;
		JSONArray array = null;
		
		all = "["+arrayA;
		all = all+arrayB;
		all = all+arrayC;
		all = all+arrayD+"]";
		
		try {
			array = (JSONArray) jsonParse.parse(all);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(array);
		return array;
	}
	
	
//	@GetMapping("boardrentpage2222")
//	@Transactional
//	public JSONArray findBoardRentAll4(@RequestParam String command){ // toString 재정의 안됨
//		List<BoardRent> al = new ArrayList<>();
//		
//		JSONParser jsonParse = new JSONParser();
//		JSONArray array = null;
//		
//		if(command == "all") {
//			al.addAll(brr.findAll());
//			try {
//				array = (JSONArray) jsonParse.parse(al.toString());
//				return array;
//			} catch (ParseException e) {
//				System.out.println("변환에 실패");
//				e.printStackTrace();
//			}
//			return array;}}
	
	
	
	
}
