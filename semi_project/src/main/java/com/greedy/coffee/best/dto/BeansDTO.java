package com.greedy.coffee.best.dto;

import com.greedy.coffee.product.dto.ProDTO;

import lombok.Data;

@Data
public class BeansDTO {

	private Long beanCode;
	private String beanName;
	private String beanPrice;
	private String beanSolidity;
	private String beanCountry;
	private String beanExplan;
	private int beanType;
	private String beanStatus;
	private ProDTO proCode;
	//private List<FileDTO> fileList;
	
}
