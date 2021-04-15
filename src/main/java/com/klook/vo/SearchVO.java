package com.klook.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchVO {
	private String keyword;
	private Integer main_cate_seq;
	private Integer sub_cate_seq;
}
