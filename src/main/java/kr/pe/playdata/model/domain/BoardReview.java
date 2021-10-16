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
//@ToString(exclude = {"boardPlace", "puser"})

@Entity
@DynamicInsert
@SequenceGenerator(name="reviewId_seq", sequenceName="reviewId_seq", initialValue=1, allocationSize=1)
@Table(name = "board_review")
public class BoardReview {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="reviewId_seq")
	@Column(name = "review_id")
	private int reviewId;
	
//	private PlaceCategory placeCate; //placeId.userEmail
//	private locid;
	
	@ManyToOne
	@JoinColumn(name = "place_id")
	private BoardPlace boardPlace;
	
	@ManyToOne
	@JoinColumn(name = "user_email")
	private Puser puser;
	
	
	
	
	@Column(name = "review_title")
	private String reviewTitle;
	
	@Column(name = "review_content")
	private String reviewContent;
	
	@Column(name = "review_date")
	private String reviewDate; //YYYYMMDD
	
	@Column(name = "review_del")
	private int reviewDel; //boolean -> int
	
	//리뷰 스코어 추가
	@Column(name="review_score")
	private int reviewScore;

	@Override
	public String toString() {
<<<<<<< Updated upstream
		return "BoardReview [reviewId=" + reviewId + ", reviewTitle=" + reviewTitle + ", reviewContent=" + reviewContent
				+ ", reviewDate=" + reviewDate + ", reviewDel=" + reviewDel + ", reviewScore=" + reviewScore + "]";
=======
		return "{\"reviewId\":\"" + reviewId + "\", "
				+ "\"reviewTitle\":\"" + reviewTitle + "\", "
				+ "\"reviewContent\":\"" + reviewContent + "\", "
				+ "\"reviewDate\":\"" + reviewDate + "\", "
				+ "\"reviewDel\":\"" + reviewDel + "\", "
				+ "\"puser\":" + puser + "\", "
				+ "\"reviewScore\":\"" + reviewScore + "\"}";
>>>>>>> Stashed changes
	}
	
}
