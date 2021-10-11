package kr.pe.playdata.model.domain;

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
@ToString
@Entity
@DynamicInsert
@Table(name = "puser")
public class Puser {
	@Id
	@Column(name = "user_email")
	@OneToMany
	private String userEmail;
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
