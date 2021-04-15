package com.klook.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingVO {
	private Integer kb_seq;
	private Date kb_book_date;
	private Integer kb_payment;
	private Date kb_payment_date;
	private Integer kb_member_seq;
	private Integer kb_prod_seq;
	private String prod_name;
	private String member_name;
}
