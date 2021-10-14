package kr.pe.playdata.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.pe.playdata.dao.PuserRepo;
import kr.pe.playdata.model.domain.Puser;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
@RestController
@RequestMapping("/con")
@SessionAttributes({"puser","nickname","email"})
public class YContorller {

	static String amail="";
	@Autowired
	private PuserRepo pur;
	
	@GetMapping("signup")
	public void signin(HttpServletResponse response) {
		String redirect_uri="http://localhost/signup.html";
    	try {
			response.sendRedirect(redirect_uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@GetMapping("login")
	public void login(HttpServletResponse response) {
		String redirect_uri="http://localhost/login.html";
    	try {
			response.sendRedirect(redirect_uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//로그인 확인 메소드
	@PostMapping("checkLogin")
	public void checkLogin(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam("email")String email,@RequestParam("psw")String psw) {
		Puser puser = new Puser();
		String redirect_uri="";
		try{
			puser = pur.findPuserByUserEmail(email); // find 실패하면 에러. catch로 넘어감.
			if(puser.getUserPassword().equals(psw)&& puser.getUserOut()== 0) {
				//성공 로직 
				redirect_uri ="http://localhost/successLogin.html";
				
				amail =email;
//				HttpSession session =request.getSession();
//				session.setAttribute("puser", puser);
//				session.setAttribute("nickname", puser.getUserNickname());
//				session.setAttribute("email", puser.getUserEmail());
//				
//				
////				System.out.println(session.getAttribute("nickname"));
////				model.addAttribute("puser", puser);
////				model.addAttribute("nickname", puser.getUserNickname());
////				model.addAttribute("email", puser.getUserEmail());
//				response.setHeader("nickname", puser.getUserNickname());
//				response.addHeader("nickname", puser.getUserNickname());
//				System.out.println(response.getHeader("nickname"));
				
				response.sendRedirect(redirect_uri);
			}else {
				System.out.println("패스워드가 틀렸습니다.");
				throw new Exception("패스워드가 틀립니다.");
			}
		}catch (Exception e) {
			//email이 틀렸을경우
			e.printStackTrace();
		}
	}
	@ExceptionHandler
	public void handler(Exception e, HttpServletResponse response) throws IOException {
		System.out.println("예외 처리 " + e.getMessage());
		e.printStackTrace();
		
		String redirect_uri="http://localhost/login.html";
		response.sendRedirect(redirect_uri);
	}
	
	//axios로 받기 위해 사용 
	@GetMapping("checkLogininfo")
	public Puser checkLogininfo() {
		return pur.findPuserByUserEmail(amail);
	}
	
	//가입 메소드
	@PostMapping("addPUser")
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
	
	
	@GetMapping("moveMainPage")
	public void moveMainPage(HttpServletResponse response) {
		String redirect_uri="http://localhost/index.html";
    	try {
			response.sendRedirect(redirect_uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("mypage")
	public void mypage(HttpServletResponse response) {
		String redirect_uri="http://localhost/mypage.html";
    	try {
			response.sendRedirect(redirect_uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@GetMapping("checkEmail")
	public String checkEmail() {
		
		return"";
	}
}
