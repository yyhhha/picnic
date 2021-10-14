package kr.pe.playdata.model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
////@ToString(exclude = "boardRent")
//
//@Entity
//@Table(name = "rent_category")
//public class RentCategory {
//	@Id
//	@Column(name = "rent_cate_id")
//	private int rentCateId;
//	
//	@OneToMany(mappedBy = "rentCategory")
//	@Column(name = "rent_id")
//	private List<BoardRent> boardRent;
//	
//	
//	
//	
//	@Column(name = "rent_cate_name")
//	private String rentCateName;
//
//
//
//
//	@Override
//	public String toString() {
//		return "RentCategory [rentCateId=" + rentCateId + ", rentCateName=" + rentCateName + "]";
//	}
//	
//	
//}
