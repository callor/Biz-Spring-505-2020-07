package com.biz.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {

	private long o_seq;		//	number
	private String o_num;	//	char(6 byte)
	private String o_date;	//	char(10 byte)
	private String o_cnum;	//	char(5 byte)
	private String o_pcode;	//	char(6 byte)
	private String o_pname;	//	nvarchar2(125 char)
	private int o_price;	//	number
	private int o_qty;		//	number
	private int o_total;	//	number 
		
}
