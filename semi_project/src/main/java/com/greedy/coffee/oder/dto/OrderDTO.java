package com.greedy.coffee.oder.dto;

import java.sql.Date;

import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.product.dto.ProDTO;

import lombok.Data;

@Data
public class OrderDTO {
	
	private Long ordCode;
	private String ordName;
	private Long ordPhone;
	private MemberDTO member;
	private String ordAddress;
	private Long ordTotalamount;
	private String ordStatus;
	private Long ordNum;
	private Date ordDate;
	private Long quantity;
	private Long ordType;
}
