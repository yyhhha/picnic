package kr.pe.playdata.model.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import kr.pe.playdata.model.domain.LocCategory;
import kr.pe.playdata.model.domain.Pcomment;
import kr.pe.playdata.model.domain.Puser;
import kr.pe.playdata.model.domain.RentCategory;
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
	private RentCategory rentCategory;
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
