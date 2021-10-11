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
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@DynamicInsert
@SequenceGenerator(name="tipId_seq", sequenceName="tipId_seq", initialValue=1, allocationSize=1)
@Table(name = "board_tip")
public class BoardTip {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tipId_seq")
	@Column(name = "tip_id")
	private int tipId;
	@JoinColumn(name = "user_email")
	@ManyToOne
	private Puser puser;
	@Column(name = "tip_title")
	private String tipTitle;
	@Column(name = "tip_content")
	private String tipContent;
	@Column(name = "tip_img")
	private String tipImg;
	@Column(name = "tip_date")
	private String tipDate; //YYYYMMDD    sql date -> java String 
	@Column(name = "tip_del")
	private int tipDel; // boolean -> int
	//tip_like 추가
	@Column(name="tip_like")
	private int tipLike;
}
