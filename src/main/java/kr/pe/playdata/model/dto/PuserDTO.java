package kr.pe.playdata.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString(exclude = {"boardReview", "boardPlace", "boardRent", "boardTip", "pcomment"})
@ToString
public class PuserDTO {
	private String userEmail;
//	private List<BoardReview> boardReview;
//	private List<BoardPlace> boardPlace;
//	private List<BoardRent> boardRent;
//	private List<BoardTip> boardTip;
//	private List<Pcomment> pcomment;
	private String userPassword;
	private String userNickname;
	private String roles;
	private int userOut; // boolean -> int 
	private Date assignDate;//YYYYMMDD    sql date -> java String 
	private Date outDate;//YYYYMMDD    sql date -> java String 

	
}
