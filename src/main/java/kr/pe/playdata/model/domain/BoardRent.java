package kr.pe.playdata.model.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

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
@DynamicInsert
@SequenceGenerator(name="rentId_seq", sequenceName="rentId_seq", initialValue=1, allocationSize=1)
@Table(name = "board_rent")
public class BoardRent {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="rentId_seq")
	@Column(name = "rent_id")
	private int rentId;
	@ManyToMany(mappedBy = "rentCateId")
	@JoinTable( name = "rent_category")
	private Set<RentCategory> rentCate;
	@Column(name = "loc_id")
	private LocCategory locCate;//LocCategory 
	@JoinColumn(name = "user_email")
	@ManyToOne
	private Puser puser;
	@Column(name = "rent_name")
	private String rentName;
	@Column(name = "rent_link")
	private String rentLink;
	@Column(name = "rent_price")
	private int rentPrice;
	@Column(name = "rent_time")
	private String rentTime; // 대여시간
	@Column(name = "rent_content")
	private String rentContent;
	@Column(name = "rent_img")
	private String rentImg;
	@Column(name = "rent_del")
	private int rentDel;
}
