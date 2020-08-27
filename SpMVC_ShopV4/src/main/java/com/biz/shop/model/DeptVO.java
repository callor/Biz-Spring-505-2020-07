package com.biz.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeptVO {
	private String d_code;//	char(4 byte)
	private String d_name;//	nvarchar2(50 char)
	private String d_ceo;//	nvarchar2(30 char)
	private String d_tel;//	varchar2(20 byte)
	private String d_address;//	nvarchar2(255 char)
	private String d_manager;//	nvarchar2(50 char)
	private String d_mn_tel;//	varchar2(20 byte)
	private Byte d_not_use;//	char(1 byte)
}
