package kr.pe.playdata.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "rent_category")
public class RentCategory {
	@Id
	@ManyToMany
	@Column(name = "rent_cate_id")
	private int rentCateId;
	@Column(name = "rent_cate_name")
	private String rentCateName;
}
