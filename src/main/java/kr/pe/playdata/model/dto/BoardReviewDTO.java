package kr.pe.playdata.model.dto;

import java.util.List;

import kr.pe.playdata.model.domain.LocCategory;
import kr.pe.playdata.model.domain.Pcomment;
import kr.pe.playdata.model.domain.Puser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardReviewDTO {
	
	private int reviewId;
	private String placeName;
	private String userEmail;
	private String reviewTitle;
	private String reviewContent;
	private String reviewDate;
	private String reviewDel;
	private int reviewScore;
}
