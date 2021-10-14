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
public class BoardRentDTO {
	
	private int rentId;
	private String rentCateName;
	private LocCategory locCate;
	private Puser puser;
	private List<Pcomment> pcomment;
	private String rentName;
	private String rentLink;
	private int rentPrice;
	private String rentTime; // 대여시간
	private String rentContent;
	private String rentImg;
	private int rentDel;
}
