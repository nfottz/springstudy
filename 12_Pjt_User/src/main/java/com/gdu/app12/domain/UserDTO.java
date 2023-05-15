package com.gdu.app12.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  
  private String id;
  private String pw;
  private String name;
  private String gender;
  private String email;
  private String birthyear;
  private String birthdate;
  private String postcode;
  private String roadAddress;
  private String jibunAddress;
  private String detailAddress;
  private String extraAddress;
  private int agreecode;
  private Date joinedAt;
  private Date pwModifiedAt;
  private String autologinId;
  private Date autologinExpiredAt;
  
}
