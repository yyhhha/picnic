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
import kr.pe.playdata.model.domain.LocCategory;
import kr.pe.playdata.model.domain.Puser;

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
		if (searchString.equals("All")) {
			List<BoardPlace> A = bpr.findAll();
			List<BoardRent> B = brr.findAll();
			List<BoardReview> C = brer.findAll();
			List<BoardTip> D = btr.findAll();
			try {
				arrayA = jsonParse.parse(A.toString());
				arrayB = jsonParse.parse(B.toString());
				arrayC = jsonParse.parse(C.toString());
				arrayD = jsonParse.parse(D.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			}else {
				List<BoardPlace> A = bpr.findBoardPlaceByPlaceNameContaining(searchString);
				List<BoardRent> B = brr.findBoardRentByRentNameContaining(searchString);
				List<BoardReview> C = brer.findBoardReviewByReviewTitleContaining(searchString);
				List<BoardTip> D = btr.findBoardTipByTipTitleContaining(searchString);
			try {
				arrayA = jsonParse.parse(A.toString());
				arrayB = jsonParse.parse(B.toString());
				arrayC = jsonParse.parse(C.toString());
				arrayD = jsonParse.parse(D.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
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
	
	@GetMapping("/searchUser")
	@ResponseBody
	public JSONArray searchUser(@RequestParam String searchString) {
		JSONParser jsonParse = new JSONParser();
		Object arrayA = null;
		
		if (searchString.equals("All")) {
			List<Puser> A = (List<Puser>) pur.findAll();
			try {
				arrayA = jsonParse.parse(A.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			List<Puser> A = pur.findPuserByUserEmailContaining(searchString);
			try {
				arrayA = jsonParse.parse(A.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String all = null;
		JSONArray array = null;
		all = "[" + arrayA + "]";
		try {
			array = (JSONArray) jsonParse.parse(all);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(array);
		return array;
	}
	
	@GetMapping("/searchLoc")
	@ResponseBody
	public JSONArray searchLoc(@RequestParam String searchString) {
		JSONParser jsonParse = new JSONParser();
		Object arrayA = null;
		
		if (searchString.equals("All")) {
			List<LocCategory> A = lcr.findLocCategoryByLocName("");
			try {
				arrayA = jsonParse.parse(A.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			List<LocCategory> A = lcr.findLocCategoryByLocNameContaining("");
			try {
				arrayA = jsonParse.parse(A.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String all = null;
		JSONArray array = null;
		all = "[" + arrayA + "]";
		try {
			array = (JSONArray) jsonParse.parse(all);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
