package kr.pe.playdata.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/ycon") 
@SessionAttributes({"puser","nickname","email"})
public class UserContorller {

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
	
	@ApiOperation(value = "회원가입", notes = "API 설명 부분 : 회원 가입창으로 리다이렉트")
	@GetMapping("/signup")
	public void signin(HttpServletResponse response) {
		String redirect_uri="http://localhost/userpage/signup.html";
    	try {
			response.sendRedirect(redirect_uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ApiOperation(value = "로그인", notes = "API 설명 부분 : 로그인 가입창으로 리다이렉트")
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
	@ApiOperation(value = "로그인 확인", notes = "API 설명 부분 :email psw 확인")
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
	@ApiOperation(value = "회원정보 호출", notes = "API 설명 부분 : 회원 정보를 호출")
	@GetMapping("/checkLogininfo")
	public PuserDTO checkLogininfo() {
		Puser puser = pur.findPuserByUserEmail(amail);
		PuserDTO pu = new PuserDTO(puser.getUserEmail(),puser.getUserPassword(),puser.getUserNickname(),puser.getRoles(),puser.getUserOut(),puser.getAssignDate(),puser.getOutDate());
		return pu;
	}
	
	//가입 메소드
	@ApiOperation(value = "회원가입", notes = "API 설명 부분 : 회원 한명 가입")
	@PostMapping("/addPUser")
	@Transactional
	public void addPuser(@ApiParam(value = "email을 입력해주세요", required = true, 
			   example = "test@gmail.com") @RequestParam("email")String email,@ApiParam(value = "password를 입력해주세요", required = true, 
			   example = "123") @RequestParam("psw")String psw,@ApiParam(value = "nickname을 입력해주세요", required = true, 
			   example = "test") @RequestParam("nickname")String nickname,HttpServletResponse response) {
		Puser B = new Puser();
		String redirect_uri ="";
		B.setUserEmail(email);
		B.setUserPassword(psw);
		B.setUserNickname(nickname);
		B.setAssignDate(LocalDate.now(ZoneId.of("Asia/Seoul"))+ " " +LocalTime.now(ZoneId.of("Asia/Seoul")).toString());
		try {
			pur.save(B);
			//저장 확인.
			redirect_uri="http://localhost/index.html";
			response.sendRedirect(redirect_uri);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@ApiOperation(value = "메인화면", notes = "API 설명 부분 : 메인 화면으로 리다이렉트")
	@GetMapping("/moveMainPage")
	public void moveMainPage(HttpServletResponse response) {
		String redirect_uri="http://localhost/index.html";
    	try {
			response.sendRedirect(redirect_uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ApiOperation(value = "마이페이지", notes = "API 설명 부분 : 마이페이지로 리다이렉트")
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
	@ApiOperation(value = "보드 렌트", notes = "API 설명 부분 : 모든 보드 렌트 호출")
	@GetMapping("/boardrentpage2222")
	@Transactional
	public JSONArray findBoardRentAll4(){ // toString 재정의 안됨
		List<BoardRent> al = new ArrayList<>();
		
		al.addAll(brr.findAll());
		
		JSONParser jsonParse = new JSONParser();
		JSONArray array = null;
		try {
			array = (JSONArray) jsonParse.parse(al.toString());
			
			return array;
		} catch (ParseException e) {
			System.out.println("변환에 실패");
			e.printStackTrace();
		}
		return array;
	}
	
	@ApiOperation(value = "마이페이지 체크 로직", notes = "API 설명 부분 : 마이페이지에서 닉네임으로 체크 성공 실패시 리다이렉트")
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
	@ApiOperation(value = "보드 렌트DTO 호출", notes = "API 설명 부분 : 작성자가 본인인 보드 렌트 호출")
	@GetMapping("/mypage2")
	public List<BoardRentDTO> findBoardRentList22(@RequestParam String command, @RequestParam String myCate, @RequestParam String useremail ) {
		List<BoardRentDTO> rent = null;
		List<BoardRentDTO> rent2 = new ArrayList<BoardRentDTO>();
		
		/* rent */
		if(command.equals("rent")) {
			if(myCate.equals("board")) {
				rent = findUser(useremail);
				for(BoardRentDTO tmp:rent) {
					if(tmp.getRentDel().equals("0")) {
						System.out.println(tmp);
						rent2.add(tmp);
					}
				}
			}else if(myCate.equals("comment")) {
//				rent = brr.findUser(myCate);
			}
		}
		return rent2;
	}
	@ApiOperation(value = "보드 팁DTO 호출", notes = "API 설명 부분 : 작성자가 본인인 보드 팁 호출")
	@GetMapping("/mypage21")
	public List<BoardTipDTO> findBoardTipList22(@RequestParam String command, @RequestParam String myCate, @RequestParam String useremail ) {
		List<BoardTipDTO> rent = null;
		List<BoardTipDTO> rent2 = new ArrayList<BoardTipDTO>();
		
		/* rent */
		if(command.equals("rent")) {
			if(myCate.equals("board")) {
				rent = findUserT(useremail);
				for(BoardTipDTO tmp :rent) {
					if(tmp.getTipDel().equals("0")) {
						rent2.add(tmp);
					}
				}
			}else if(myCate.equals("comment")) {
//				rent = brr.findUser(myCate);
			}
		}
		return rent2;
	}

	@ApiOperation(value = "보드 플레이스DTO 호출", notes = "API 설명 부분 : 작성자가 본인인 보드 플레이스 호출")
	@GetMapping("/mypage22")
	public List<BoardPlaceDTO> findBoardPlaceList22(@RequestParam String command, @RequestParam String myCate, @RequestParam String useremail ) {
		List<BoardPlaceDTO> rent = null;
		List<BoardPlaceDTO> rent2 = new ArrayList<BoardPlaceDTO>();
		
		/* rent */
		if(command.equals("rent")) {
			if(myCate.equals("board")) {
				rent = findUserP(useremail);
				for(BoardPlaceDTO tmp : rent) {
					if(tmp.getPlaceDel().equals("0")) {
						rent2.add(tmp);
					}
				}
			}else if(myCate.equals("comment")) {
//				rent = brr.findUser(myCate);
			}
		}
		return rent2;
	}
	
	@ApiOperation(value = "보드 리뷰DTO 호출", notes = "API 설명 부분 : 작성자가 본인인 보드 리뷰 호출")
	@GetMapping("/mypage23")
	public List<BoardReviewDTO> findBoardReviewList22(@RequestParam String command, @RequestParam String myCate, @RequestParam String useremail ) {
		List<BoardReviewDTO> rent = null;
		List<BoardReviewDTO> rent2 = new ArrayList<BoardReviewDTO>();
		
		/* rent */
		if(command.equals("rent")) {
			if(myCate.equals("board")) {
				rent = findUserR(useremail);
				for(BoardReviewDTO tmp : rent) {
					if(tmp.getReviewDel().equals("0")) {
						rent2.add(tmp);
					}
				}
			}else if(myCate.equals("comment")) {
//				rent = brr.findUser(myCate);
			}
		}
		return rent2;
	}
	
	@ApiOperation(value = "회원 찾기", notes = "API 설명 부분 : email로 비밀번호 조회")
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
