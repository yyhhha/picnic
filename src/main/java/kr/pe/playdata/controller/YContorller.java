package kr.pe.playdata.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

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
import kr.pe.playdata.model.domain.Pcomment;
import kr.pe.playdata.model.domain.Puser;
import kr.pe.playdata.model.dto.BoardPlaceDTO;
import kr.pe.playdata.model.dto.BoardRentDTO;
import kr.pe.playdata.model.dto.BoardReviewDTO;
import kr.pe.playdata.model.dto.BoardTipDTO;
import kr.pe.playdata.model.dto.PuserDTO;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
@RestController
@RequestMapping("/ycon") // ycon/boardrentpage222222
@SessionAttributes({"puser","nickname","email"})
public class YContorller {

	static String amail="";
	
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
	
	@GetMapping("/signup")
	public void signin(HttpServletResponse response) {
		String redirect_uri="http://localhost/userpage/signup.html";
    	try {
			response.sendRedirect(redirect_uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@GetMapping("/login")
	public void login(HttpServletResponse response) {
		String redirect_uri="http://localhost/userpage/login.html";
    	try {
			response.sendRedirect(redirect_uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//로그인 확인 메소드
	@PostMapping("/checkLogin")
	public void checkLogin(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam("email")String email,@RequestParam("psw")String psw) throws IOException {
		Puser puser = new Puser();
		String redirect_uri="";
		//작성자 세션 불러오기 위함
		HttpSession session = request.getSession();

		try{
			puser = pur.findPuserByUserEmail(email); // find 실패하면 에러. catch로 넘어감.
			if(puser.getUserPassword().equals(psw)&& puser.getUserOut()== 0) {
				if(puser.getRoles().equals("admin")||puser.getRoles().equals("ADMIN")) {
					redirect_uri ="http://localhost/admin/deleteBoardList.html";
					amail =email;
					session.setAttribute("userEmail", email);
					response.sendRedirect(redirect_uri);
				}else {
					//성공 로직 
					redirect_uri ="http://localhost/index3.html";
					amail =email;
					//작성자 세션 불러오기 위함
					session.setAttribute("userEmail", email);
					response.sendRedirect(redirect_uri);
				}
				
			}else {
				System.out.println("패스워드가 틀렸습니다.");
				redirect_uri ="http://localhost/userpage/login.html";
				response.sendRedirect(redirect_uri);
				throw new Exception("패스워드가 틀립니다.");
			}
		}catch (Exception e) {
			//email이 틀렸을경우
			System.out.println("email을 틀렸습니다.");
			 redirect_uri="http://localhost/userpage/login.html";
			 response.sendRedirect(redirect_uri);
			e.printStackTrace();
		}
	}
	@ExceptionHandler
	public void handler(Exception e, HttpServletResponse response) throws IOException {
		System.out.println("예외 처리 " + e.getMessage());
		e.printStackTrace();
		
		String redirect_uri="http://localhost/userpage/login.html";
		response.sendRedirect(redirect_uri);
	}
	
	//axios로 받기 위해 사용 
	@GetMapping("/checkLogininfo")
	public PuserDTO checkLogininfo() {
		Puser puser = pur.findPuserByUserEmail(amail);
		
		PuserDTO pu = new PuserDTO(puser.getUserEmail(),puser.getUserPassword(),puser.getUserNickname(),puser.getRoles(),puser.getUserOut(),puser.getAssignDate(),puser.getOutDate());
		return pu;
	}
	
	//가입 메소드
	@PostMapping("/addPUser")
	@Transactional
	public void addPuser(@RequestParam("email")String email,@RequestParam("psw")String psw,@RequestParam("nickname")String nickname,HttpServletResponse response) {
		Puser B = new Puser();
		String redirect_uri ="";
		
		B.setUserEmail(email);
		B.setUserPassword(psw);
		B.setUserNickname(nickname);
		try {
			pur.save(B);
			//저장 확인.
			redirect_uri="http://localhost/index.html";
			response.sendRedirect(redirect_uri);
		}catch (Exception e) {
			e.printStackTrace();
//			redirect_uri="http://localhost/index.html";
//			response.sendRedirect(redirect_uri);
		}
		
	}
	
	
	@GetMapping("/moveMainPage")
	public void moveMainPage(HttpServletResponse response) {
		String redirect_uri="http://localhost/index.html";
    	try {
			response.sendRedirect(redirect_uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/mypage")
	public void mypage(HttpServletResponse response) {
		String redirect_uri="http://localhost/userpage/mypage.html";
    	try {
			response.sendRedirect(redirect_uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@GetMapping("/checkEmail")
	public String checkEmail() {
		
		return"";
	}
	/* read (all) */
	@GetMapping("/boardrentpage2222")
	@Transactional
	public JSONArray findBoardRentAll4(){ // toString 재정의 안됨
		List<BoardRent> al = new ArrayList<>();
		
		al.addAll(brr.findAll());
		
		JSONParser jsonParse = new JSONParser();
//		JSONObject jsonObj = null;
		JSONArray array = null;
		try {
//			jsonObj = (JSONObject) jsonParse.parse(al.toString());
			array = (JSONArray) jsonParse.parse(al.toString());
			
			return array;
		} catch (ParseException e) {
			System.out.println("변환에 실패");
			e.printStackTrace();
		}
		return array;
	}
	
	@RequestMapping("/captcheck")
	public void captcheck( HttpServletResponse response,@RequestParam("psw")String psw) throws IOException {
		
		System.out.println("pse ;" +psw);
		String redirect_uri="";
		try {
			Puser puser = new Puser();
			puser = pur.findPuserByUserEmail(amail); // find 실패하면 에러. catch로 넘어감.
			redirect_uri="http://localhost/userinfo.html";
			if(puser.getUserPassword().equals(psw)) {
				response.sendRedirect(redirect_uri);
			}
		} catch (IOException e) {
			redirect_uri="http://localhost/userpage/mypage.html";
			response.sendRedirect(redirect_uri);
			e.printStackTrace();
		}
	}
	
	public List<BoardRentDTO> findUser(String useremail) {
		Puser puer = pur.findPuserByUserEmail(useremail);
		List<BoardRentDTO> test = new ArrayList<>();
			List<BoardRent> han = brr.findBoardRentByPuser(puer);
			
			for(BoardRent rent:han) {
				System.out.println(rent.toString());
				test.add(new BoardRentDTO(rent.getRentId(),rent.getRentCateName(),rent.getLocCate().getLocName(),rent.getPuser().getUserEmail(),rent.getRentName(),
						rent.getRentLink(),rent.getRentPrice(),rent.getRentTime(),rent.getRentContent(),
						rent.getRentImg(),rent.getRentDel()));
			}
		return test;
	}
	
	public List<BoardTipDTO> findUserT(String useremail) {
		Puser puer = pur.findPuserByUserEmail(useremail);
		List<BoardTipDTO> test = new ArrayList<>();

			
	
			List<BoardTip> han = btr.findBoardTipByPuser(puer);
			
			for(BoardTip tip:han) {
				test.add(new BoardTipDTO(tip.getTipId(),tip.getPuser().getUserEmail() ,tip.getTipTitle(), 
						tip.getTipContent(),tip.getTipImg(),tip.getTipDate(),tip.getTipDel(),tip.getTipLike()));
			}
		
		return test;
	}

	private List<BoardPlaceDTO> findUserP(String useremail) {
		Puser puer = pur.findPuserByUserEmail(useremail);
		List<BoardPlaceDTO> test = new ArrayList<>();
		List<BoardPlace> han = bpr.findBoardPlaceByPuser(puer);
		
		for(BoardPlace place:han) {
			test.add(new BoardPlaceDTO(place.getPlaceId(),place.getLocCate().getLocName(),place.getPuser().getUserEmail(),place.getPlaceName(), 
					place.getPlaceContent(),place.getPlaceImg(),place.getPlaceDel()));
		}
		return test;
	}
	
	private List<BoardReviewDTO> findUserR(String useremail) {
		Puser puer = pur.findPuserByUserEmail(useremail);
		List<BoardReviewDTO> test = new ArrayList<>();
		List<BoardReview> han = brer.findBoardReviewByPuser(puer);
		
		for(BoardReview review:han) {
			test.add(new BoardReviewDTO(review.getReviewId(),review.getBoardPlace().getPlaceId(),review.getPuser().getUserEmail(),review.getReviewTitle(), 
					review.getReviewContent(),review.getReviewDate(),review.getReviewDel(),review.getReviewScore()));
		}
		return test;
	}
	
	@GetMapping("/mypage2")
	public List<BoardRentDTO> findBoardRentList22(@RequestParam String command, @RequestParam String myCate, @RequestParam String useremail ) {
//		List<BoardRentDTO> rent = null;
		List<BoardRentDTO> rent = null;
		
		/* rent */
		if(command.equals("rent")) {
			if(myCate.equals("board")) {
				rent = findUser(useremail);
			}else if(myCate.equals("comment")) {
//				rent = brr.findUser(myCate);
			}
		}
		return rent;
	}

	@GetMapping("/mypage21")
	public List<BoardTipDTO> findBoardTipList22(@RequestParam String command, @RequestParam String myCate, @RequestParam String useremail ) {
		List<BoardTipDTO> rent = null;
		
		/* rent */
		if(command.equals("rent")) {
			if(myCate.equals("board")) {
				rent = findUserT(useremail);
			}else if(myCate.equals("comment")) {
//				rent = brr.findUser(myCate);
			}
		}
		return rent;
	}

	@GetMapping("/mypage22")
	public List<BoardPlaceDTO> findBoardPlaceList22(@RequestParam String command, @RequestParam String myCate, @RequestParam String useremail ) {
		List<BoardPlaceDTO> rent = null;
		
		/* rent */
		if(command.equals("rent")) {
			if(myCate.equals("board")) {
				rent = findUserP(useremail);
			}else if(myCate.equals("comment")) {
//				rent = brr.findUser(myCate);
			}
		}
		return rent;
	}
	
	@GetMapping("/mypage23")
	public List<BoardReviewDTO> findBoardReviewList22(@RequestParam String command, @RequestParam String myCate, @RequestParam String useremail ) {
		List<BoardReviewDTO> rent = null;
		
		/* rent */
		if(command.equals("rent")) {
			if(myCate.equals("board")) {
				rent = findUserR(useremail);
			}else if(myCate.equals("comment")) {
//				rent = brr.findUser(myCate);
			}
		}
		return rent;
	}
	
//	@GetMapping("/mypage24")
//	public List<PcommentDTO> findComment(@RequestParam String useremail ) {
////		List<PcommentDTO> rent = null;
//		
//		/* rent */
////				rent = findUserR(useremail);
//		return rent;
//	}
	
	
	@PostMapping("/findpswByEmail")
	public void findpswByEmail(HttpServletRequest request,HttpServletResponse response,@RequestParam("email")String email) {
		Puser puser = new Puser();
		String redirect_uri="";
		try{
			System.out.println(email);
			puser = pur.findPuserByUserEmail(email); // find 실패하면 에러. catch로 넘어감.
				redirect_uri ="http://localhost/userpage/FindID2.html";
				amail =email;
				response.sendRedirect(redirect_uri);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
