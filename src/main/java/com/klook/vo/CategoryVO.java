package com.klook.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryVO {
	
	private Integer kmc_seq;
	private String kmc_name;
	private Date kmc_reg_date;
	
	private Integer ksc_seq;
	private String ksc_name;
	private Date ksc_reg_date;
	private Integer ksc_mc_seq;
	private String category_name;

}
