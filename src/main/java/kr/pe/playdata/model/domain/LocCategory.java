package kr.pe.playdata.model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity

@SequenceGenerator(name="locId_seq", sequenceName="locId_seq", initialValue=1, allocationSize=1)
@Table(name = "loc_category")
public class LocCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="locId_seq")
	@Column(name = "loc_id")
	private int locId;
	
	@OneToMany(mappedBy = "locCate")
	@Column(name = "place_id")
	private List<BoardPlace> boardPlaceList;
	
	@OneToMany(mappedBy = "locCate")
	@Column(name = "rent_id")
	private List<BoardRent> boardRentList;
	
	
	
	
	@Column(name = "place_category")
	private String placeCategory;
	
	@Column(name = "loc_name")
	private String locName;
	
	@Column(name = "loc_address")
	private String locAddress;
	
	@Column(name = "loc_sido")
	private String locSido;
	
	@Column(name = "loc_sigungu")
	private String locSigungu;
}
