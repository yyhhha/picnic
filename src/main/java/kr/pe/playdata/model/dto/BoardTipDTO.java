package kr.pe.playdata.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardTipDTO {

	private int tipId;
	private String userEmail;
	private String tipTitle;
	private String tipContent;
	private String tipImg;
	private Date tipDate; 
	private String tipDel;
	private int tipLike; 
//	private List<Pcomment> pcomment;
	
	


//	public void BoardTipDTO2(BoardTip boardTip) {
//		tipId = boardTip.getTipId();
//		userEmail = boardTip.getPuser().getUserEmail();
//		tipTitle = boardTip.getTipTitle();
//		tipContent = boardTip.getTipContent();
//		tipImg = boardTip.getTipContent();
//		tipDate = boardTip.getTipDate();
//		tipDel = boardTip.getTipDel();
//		tipLike = boardTip.getTipLike();
//		
//		
//	}
}
