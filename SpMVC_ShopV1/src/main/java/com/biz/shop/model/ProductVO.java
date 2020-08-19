package com.biz.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {

	private String p_code;	//	char(6)
	private String p_name;	//	nvarchar2(30)
	private String p_dcode;	//	char(4)
	private String p_std;	//	nvarchar2(20)
	private int p_iprice;	//	number
	private int p_oprice;	//	number
	private String p_image;	//	nvarchar2(125)
	
}
