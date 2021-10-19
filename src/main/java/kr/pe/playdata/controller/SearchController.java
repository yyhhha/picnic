package kr.pe.playdata.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

	@ApiOperation(value = "전체 검색", notes = "API 설명 부분 : 게시글 전체 검색 기능입니다. ")
	@GetMapping("/search/all")
	@ResponseBody
	public JSONArray searchAll(@RequestParam String searchString) {

		JSONParser jsonParse = new JSONParser();
		Object arrayA = null;
		Object arrayB = null;
		Object arrayC = null;
		Object arrayD = null;
		if (searchString.equals("All")) {
			List<BoardPlace> A = bpr.findAll();
			List<BoardPlace> Aa = new ArrayList<BoardPlace>();
			for(BoardPlace tmp:A) {
				if(tmp.getPlaceDel().equals("0")) {
					Aa.add(tmp);
				}
			}
			List<BoardRent> B = brr.findAll();
			List<BoardRent> Bb = new ArrayList<BoardRent>();
			for(BoardRent tmp: B) {
				if(tmp.getRentDel().equals("0")) {
					Bb.add(tmp);
				}
			}
			List<BoardReview> C = brer.findAll();
			List<BoardReview> Cc = new ArrayList<BoardReview>();
			for(BoardReview tmp:Cc) {
				if(tmp.getReviewDel().equals("0")) {
					Cc.add(tmp);
				}
			}
			List<BoardTip> D = btr.findAll();
			List<BoardTip> Dd = new ArrayList<BoardTip>();
			for(BoardTip tmp:D) {
				if(tmp.getTipDel().equals("0")) {
					Dd.add(tmp);
				}
			}
			try {
				arrayA = jsonParse.parse(Aa.toString());
				arrayB = jsonParse.parse(Bb.toString());
				arrayC = jsonParse.parse(Cc.toString());
				arrayD = jsonParse.parse(Dd.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
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

		all = "[" + arrayA;
		all = all + arrayB;
		all = all + arrayC;
		all = all + arrayD + "]";

		try {
			array = (JSONArray) jsonParse.parse(all);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(array);
		return array;
	}
	@ApiOperation(value = "유저 검색", notes = "API 설명 부분 : 유저 검색 기능입니다. ")
	@GetMapping("/search/user")
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
		} else {
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

	@ApiOperation(value = "위치 검색", notes = "API 설명 부분 : loc 위치 검색 기능입니다. ")
	@GetMapping("/search/loc")
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
		} else {
			List<LocCategory> A = lcr.findLocCategoryByLocNameContaining(searchString);
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

}
