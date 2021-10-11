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
@SequenceGenerator(name="placeId_seq", sequenceName="placeId_seq", initialValue=1, allocationSize=1)
@Table(name = "board_place")
public class BoardPlace  {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="placeId_seq")
	@Column(name = "place_id")
	private int placeId;
	@JoinColumn(name = "loc_id")
	@ManyToOne
	private LocCategory locCate;
	//loc_id -> place_cate_id
//	private PlaceCategory placeCateId;
	@JoinColumn(name = "user_email")
	@ManyToOne
	private Puser puser;
	@Column(name = "place_name")
	private String palceName;
	@Column(name = "place_content")
	private String placeContent;
	//place_score 삭제 리뷰 스코어 합산해서 계산.
//	@Column(name = "place_score")
//	private int placeScore;
	@Column(name = "place_img")
	private String placeImg;
	@Column(name = "place_del")
	private int placeDel; //boolean -> int 0,1로 구분 Y N
}
