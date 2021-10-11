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
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "place_category")
public class PlaceCategory {
	@Id
	@Column(name = "place_cate_id")
	@ManyToMany
	private int placeCateId;
	@Column(name = "place_cate_name")
	private String placeCateName;
}
