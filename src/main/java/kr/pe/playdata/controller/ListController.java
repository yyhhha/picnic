package kr.pe.playdata.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import kr.pe.playdata.dao.BoardPlaceRepo;
import kr.pe.playdata.dao.BoardRentRepo;
import kr.pe.playdata.dao.BoardReviewRepo;
import kr.pe.playdata.dao.BoardTipRepo;
import kr.pe.playdata.model.domain.BoardPlace;
import kr.pe.playdata.model.domain.BoardRent;
import kr.pe.playdata.model.domain.BoardReview;
import kr.pe.playdata.model.domain.BoardTip;
import kr.pe.playdata.model.dto.BoardPlaceDTO;
import kr.pe.playdata.model.dto.BoardRentDTO;
import kr.pe.playdata.model.dto.BoardReviewDTO;
import kr.pe.playdata.model.dto.BoardTipDTO;

@RestController
@RequestMapping("/bcon")
public class ListController {

	@Autowired
	private BoardPlaceRepo bpr;
	@Autowired
	private BoardRentRepo brr;
	@Autowired
	private BoardReviewRepo brer;
	@Autowired
	private BoardTipRepo btr;

	@ApiOperation(value = "게시판 장소추천 조회", notes = "API 설명 부분 : 게시판 장소추천 리스트 전체 조회")
	@GetMapping("/board/places")
	public List<BoardPlaceDTO> findBoardPlaceList() {
		Iterator<BoardPlace> all = bpr.findAll().iterator();

		BoardPlace place;
		List<BoardPlaceDTO> test = new ArrayList<>();

		while (all.hasNext()) {
			place = all.next();
			if (place.getPlaceDel().equals("0")) {
				test.add(new BoardPlaceDTO(place.getPlaceId(), place.getLocCate().getLocName(),
						place.getPuser().getUserEmail(), place.getPlaceName(), place.getPlaceContent(),
						place.getPlaceImg(), place.getPlaceDel()));
			}
		}
		return test;
	}

	@ApiOperation(value = "게시판 장소후기 조회", notes = "API 설명 부분 : 게시판 장소후기 리스트 전체 조회")
	@GetMapping("/board/reviews")
	public List<BoardReviewDTO> findBoardReviewList() {
		Iterator<BoardReview> all = brer.findAll().iterator();

		BoardReview review;
		List<BoardReviewDTO> test = new ArrayList<>();

		while (all.hasNext()) {
			review = all.next();
			if(review.getReviewDel().equals("0")) {
				test.add(new BoardReviewDTO(review.getReviewId(),review.getBoardPlace().getPlaceId(),review.getPuser().getUserEmail(),review.getReviewTitle(), 
						review.getReviewContent(),review.getReviewDate(),review.getReviewDel(),review.getReviewScore()));
			}
		}
		return test;
	}

	@ApiOperation(value = "게시판 피크닉 꿀팁 조회", notes = "API 설명 부분 : 게시판 피크닉 꿀팁 리스트 전체 조회")
	@GetMapping("/board/tips")
	public List<BoardTipDTO> findBoardTipList() {
		Iterator<BoardTip> all = btr.findAll().iterator();

		BoardTip tip;
		List<BoardTipDTO> test = new ArrayList<>();

		while (all.hasNext()) {
			tip = all.next();
			if (tip.getTipDel().equals("0")) {
				test.add(new BoardTipDTO(tip.getTipId(), tip.getPuser().getUserEmail(), tip.getTipTitle(),
						tip.getTipContent(), tip.getTipImg(), tip.getTipDate(), tip.getTipDel(), tip.getTipLike()));
			}
		}
		return test;
	}

	@ApiOperation(value = "게시판 피크닉 대여업체 조회", notes = "API 설명 부분 : 게시판 피크닉 대여업체 리스트 전체 조회")
	@GetMapping("/board/rents")
	public List<BoardRentDTO> findBoardRentList(@RequestParam String command, @RequestParam String rentCateName) {
		List<BoardRentDTO> rent = null;

		if (command.equals("rent")) {
			if (rentCateName.equals("한강피크닉")) {
				rent = findBoardRentListByRentCateName(rentCateName);
			} else if (rentCateName.equals("바다피크닉")) {
				rent = findBoardRentListByRentCateName(rentCateName);
			} else if (rentCateName.equals("글램핑")) {
				rent = findBoardRentListByRentCateName(rentCateName);
			} else {
				rent = findBoardRentListAll();
			}
		}
		return rent;
	}

	/* read all - board rent list */
	public List<BoardRentDTO> findBoardRentListAll() {
		Iterator<BoardRent> all = brr.findAll().iterator();

		BoardRent rent;
		List<BoardRentDTO> test = new ArrayList<>();

		while (all.hasNext()) {
			rent = all.next();
			if (rent.getRentDel().equals("0")) {
				test.add(new BoardRentDTO(rent.getRentId(), rent.getRentCateName(), rent.getLocCate().getLocName(),
						rent.getPuser().getUserEmail(), rent.getRentName(), rent.getRentLink(), rent.getRentPrice(),
						rent.getRentTime(), rent.getRentContent(), rent.getRentImg(), rent.getRentDel()));
			}
		}
		return test;
	}

	/* read board rent list by rent category */
	public List<BoardRentDTO> findBoardRentListByRentCateName(String rentCateName) {
		Iterator<BoardRent> han = brr.findBoardRentByRentCateName(rentCateName).iterator();

		BoardRent rent;
		List<BoardRentDTO> test = new ArrayList<>();

		while (han.hasNext()) {
			rent = han.next();
			if (rent.getRentDel().equals("0")) {
				test.add(new BoardRentDTO(rent.getRentId(), rent.getRentCateName(), rent.getLocCate().getLocName(),
						rent.getPuser().getUserEmail(), rent.getRentName(), rent.getRentLink(), rent.getRentPrice(),
						rent.getRentTime(), rent.getRentContent(), rent.getRentImg(), rent.getRentDel()));
			}
		}
		return test;
	}

}
