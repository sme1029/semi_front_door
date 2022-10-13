package com.greedy.coffee.store.dto;
  
import java.sql.Date;

import com.greedy.coffee.member.dto.MemberDTO;

import lombok.Data;
  
@Data 
public class StoreDTO {
  
  private Long stoCode; 
  private Integer stoType;
  private String stoName; 
  private String stoAdd;
  private char stoStatus; 
  private Date stoDate;
  private Date stoEditDate;
  private Date stoDeleteDate;
  private MemberDTO memId;
  
}
 