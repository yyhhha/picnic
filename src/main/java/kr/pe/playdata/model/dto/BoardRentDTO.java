package kr.pe.playdata.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardRentDTO {
	
	private int rentId;
	private String rentCateName;
	private String locName;
	private String userEmail;
//	private List<Pcomment> pcomment;
	private String rentName;
	private String rentLink;
	private int rentPrice;
	private String rentTime; // 대여시간
	private String rentContent;
	private String rentImg;
	private int rentDel;

}
