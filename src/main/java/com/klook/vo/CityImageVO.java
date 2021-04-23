package com.klook.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityImageVO {
	private Integer kci_seq;
	private String kci_path;
	private String kci_uri;
	private String kci_name;
	private Long kci_size;
	private Integer kci_city_seq;
}
