package com.klook.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageVO {
	private Integer kpi_seq;
	private String kpi_name;
	private String kpi_path;
	private String kpi_uri;
	private String kpi_type;
	private Long kpi_size;
	private Integer kpi_prod_seq;
}
