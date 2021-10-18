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
public class BoardPlaceDTO {
	
	private int placeId;
	private String locName;
	private String userEmail;
//	private List<Pcomment> pcomment;
	private String placeName;
	private String placeContent;
	private String placeImg;
	private String placeDel; 
	
}
