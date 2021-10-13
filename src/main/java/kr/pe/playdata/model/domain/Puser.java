package kr.pe.playdata.model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@DynamicInsert
@Table(name = "puser")
public class Puser {
	@Id
	@Column(name = "user_email")
	private String userEmail;
	
	@OneToMany(mappedBy = "puser")
	@Column(name = "board_review_id")
	private List<BoardReview> boardReview;
	
	@OneToMany(mappedBy = "puser")
	@Column(name = "board_place_id")
	private List<BoardPlace> boardPlace;
	
	@OneToMany(mappedBy = "puser")
	@Column(name = "rent_id")
	private List<BoardRent> boardRent;
	
	@OneToMany(mappedBy = "puser")
	@Column(name = "tip_id")
	private List<BoardTip> boardTip;
	
	@OneToMany(mappedBy = "puser")
	@Column(name = "pcomment")
	private List<Pcomment> pcomment;
	
	
	
	
	@Column(name = "user_password")
	private String userPassword;
	
	@Column(name = "user_nickname")
	private String userNickname;
	
	@Column(name = "roles")
	private String roles;
	
	@Column(name = "user_out")
	private int userOut; // boolean -> int 
	
	@Column(name = "assign_date")
	private String assignDate;//YYYYMMDD    sql date -> java String 
	
	@Column(name = "out_date")
	private String outDate;//YYYYMMDD    sql date -> java String 
	
}
