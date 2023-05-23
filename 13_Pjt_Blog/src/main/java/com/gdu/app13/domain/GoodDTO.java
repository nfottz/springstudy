package com.gdu.app13.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodDTO {

  private int memberNo;
  private int blogNo;
  private Date markedAt;
  
}
