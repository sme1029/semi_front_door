package com.greedy.coffee.oder.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.member.entity.Member;
import com.greedy.coffee.product.dto.ProDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name= "TBL_ORDER")
@SequenceGenerator(name = "ORD_SEQ_GENERATOR", sequenceName = "SEQ_ORD_CODE", initialValue = 1, allocationSize = 1)
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORD_SEQ_GENERATOR")
	@Column(name = "ORD_CODE")
	private Long ordCode;
	
	@Column(name = "ORD_NAME")
	private String ordName;
	
	@Column(name = "ORD_PHONE")
	private Long ordPhone;
	
	@ManyToOne
	@JoinColumn(name = "MEM_ID")
	private Member member;
	
	@Column(name = "ORD_ADDRESS")
	private String ordAddress;
	
	@Column(name = "ORD_TOTALAMOUNT")
	private Long ordTotalamount;
	
	@Column(name = "ORD_STATUS")
	private String ordStatus;
	
	@Column(name = "ORD_NUM")
	private Long ordNum;
	
	@Column(name = "ORD_DATE")
	private Date ordDate;
	
	@Column(name = "QUANTITY")
	private Long quantity;
	
	@Column(name = "ORD_TYPE")
	private Long ordType;
}
