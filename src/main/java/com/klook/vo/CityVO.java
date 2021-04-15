package com.klook.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityVO {
	private Integer kc_seq;
	private String kc_name;
	private Date kc_reg_date;
	private String city_name;
	private String image_uri;
	private String image_name;
}
