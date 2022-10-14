package com.greedy.coffee.file.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_FILE")
@SequenceGenerator(name = "FILE_SEQ_GENERATOR", sequenceName = "SEQ_FILE_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_SEQ_GENERATOR")
	@Column(name = "FILE_CODE")
	private Long fileCode;
	
	@Column(name = "FILE_ORGNAME")
	private String fileOrgName;
	
	@Column(name = "FILE_SAVENAME")
	private String fileSaveName ;
	
	@Column(name = "FILE_PATH")
	private String filePath;
	
	@Column(name = "FILE_STATUS")
	private String fileStatus;
	
	@Column(name = "FILE_TYPE")
	private String fileType;
	
	@Column(name = "FILE_THUM_NAME")
	private String fileThumName;
	
	@Column(name = "FILE_THUM_PATH")
	private String fileThumPath;
	
	@Column(name = "RE_PRO_CODE")
	private Long proCode;
	
	@Column(name = "REV_CODE")
	private Long revCode;

}