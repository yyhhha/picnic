package kr.pe.playdata.model.dto;

import kr.pe.playdata.model.domain.BoardTip;
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
	private String tipDate; 
	private int tipDel;
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
