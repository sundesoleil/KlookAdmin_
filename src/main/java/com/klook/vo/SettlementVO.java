package com.klook.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettlementVO {
	private Integer ks_seq;
	private Integer ks_method;
	private Integer ks_amount;
	private Date ks_pay_date;
	private Integer ks_member_seq;
	private Integer ks_prod_seq;
	private String email;
	private String product_name;
}
