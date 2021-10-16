package kr.pe.playdata.model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
//@ToString(exclude = {"locCate", "puser", "BoardReviewList"})

@Entity
@DynamicInsert
@SequenceGenerator(name="placeId_seq", sequenceName="placeId_seq", initialValue=1, allocationSize=1)
@Table(name = "board_place")
public class BoardPlace  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="placeId_seq")
	@Column(name = "place_id")
	private int placeId;
	
	@ManyToOne
	@JoinColumn(name = "loc_id")
	private LocCategory locCate;
	
	//loc_id -> place_cate_id
//	@Column(name = "place_cate_id")
//	private PlaceCategory placeCateId;
	
	@ManyToOne
	@JoinColumn(name = "user_email")
	private Puser puser;
	
	@OneToMany//(mappedBy = "board_place")
	@Column(name = "review_id")
	private List<BoardReview> BoardReviewList;
	
	@Column(name = "place_name")
	private String placeName;
	
	@Column(name = "place_content")
	private String placeContent;
	
	
	//place_score 삭제 리뷰 스코어 합산해서 계산.
//	@Column(name = "place_score")
//	private int placeScore;
	
	@Column(name = "place_img")
	private String placeImg;
	
	@Column(name = "place_del")
	private int placeDel; //boolean -> int 0,1로 구분 Y N
	
	@Override
	public String toString() {
<<<<<<< Updated upstream
		return "BoardPlace [placeId=" + placeId + ", placeName=" + placeName + ", placeContent=" + placeContent
				+ ", placeImg=" + placeImg + ", placeDel=" + placeDel + "]";
=======
		return "{\"placeId\":\"" + placeId + "\", " 
				+ "\"placeName\":\"" + placeName + "\", "
				+ "\"placeContent\":\"" + placeContent + "\", "
				+ "\"placeImg\":\"" + placeImg + "\", "
				+ "\"puser\":" + puser + "\", "
				+ "\"placeDel\":\"" + placeDel + "\"}";
>>>>>>> Stashed changes
	}

}
