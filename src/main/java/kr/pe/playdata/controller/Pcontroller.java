package kr.pe.playdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.dao.BoardTipRepo;
import kr.pe.playdata.dao.PcommentRepo;
import kr.pe.playdata.dao.PuserRepo;

@RestController
public class Pcontroller {

	@Autowired
	private BoardTipRepo btr;
	@Autowired
	private PcommentRepo pcor;
	@Autowired
	private PuserRepo pur;

	@GetMapping("/tips/new")
	public String addBoardTip() { 
			
		return "tips/new";
	}
	
	@PostMapping("/tips/create")  //form 에서 post방식으로 던지기로 했기 때문에
	public String createTips() {
		return "tips/create"; 
		
	}
}
