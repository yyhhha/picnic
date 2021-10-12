package kr.pe.playdata.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@DynamicInsert
@SequenceGenerator(name="commentId_seq", sequenceName="commentId_seq", initialValue=1, allocationSize=1)
@Table(name = "pcomment")
public class Pcomment {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="commentId_seq")
	@Column(name = "comment_id")
	private int commentId;
	
	@ManyToOne
	@JoinColumn(name = "rent_id")
	private BoardRent boardRent;
	
	@ManyToOne
	@JoinColumn(name = "user_email")
	private Puser puser;
	
	//tip_id 추가
	@ManyToOne
	@JoinColumn(name="tip_id")
	private BoardTip boardTip;
	
	
	
	
	@Column(name = "comment_content")
	private String commentContent;
	
	@Column(name = "comment_date")
	private String commnetDate;
	
	@Column(name = "comment_del")
	private int commentDel; // boolean -> int 
}
