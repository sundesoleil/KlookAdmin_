package com.klook.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVO {
	private Integer kp_seq;
	private String kp_name;
	private Integer kp_price;
	private String kp_description;
	private Integer kp_discount;
	private Integer kp_discount_rate;
	private Integer kp_point;
	private Integer kp_point_rate;
	private Date kp_reg_date;
	private Integer kp_city_seq;
	private Integer kp_mc_seq;
	private Integer kp_sc_seq;
	private Integer kp_pimg_seq;
	private String main_category_name;
	private String sub_category_name;
	private String city_name;
	private String image_uri;
	private String image_name;
}
