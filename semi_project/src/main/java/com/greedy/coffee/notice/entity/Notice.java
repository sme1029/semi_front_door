package com.greedy.coffee.notice.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.coffee.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TBL_NOTICE")
@SequenceGenerator(name = "NOT_SEQ_GENERATOR", sequenceName = "SEQ_NOT_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class Notice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOT_SEQ_GENERATOR")
	@Column(name = "NOT_CODE")
	private Long notCode;

	@Column(name = "NOT_TITLE")
	private String notTitle;

	@Column(name = "NOT_CONTENT")
	private String notContent;
	
	@Column(name = "NOT_DATE")
	private Date notDate;
	
	@Column(name = "NOT_EDIT_DATE")
	private Date editDate;
	
	@Column(name = "NOT_DELETE_DATE")
	private Date deleteDate;
	
	@Column(name = "NOT_STATUS")
	private String notStatus;
	
	@ManyToOne
	@JoinColumn(name = "MEM_ID")
	private Member member;
	
}
