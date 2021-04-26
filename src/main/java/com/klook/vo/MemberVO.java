package com.klook.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	private Integer km_seq;
	private String km_email;
	private String km_pwd;
	private String km_name;
	private String km_phone;
	private Date km_reg_date;
	private Integer km_terms_agree;
	private Integer km_info_agree;
	private Integer no;
}
