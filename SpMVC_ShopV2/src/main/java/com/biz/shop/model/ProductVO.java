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
	
	// DB연동을 할때 CHAR(1) TinyInt 형으로 선언된 칼럼과 연동하기 위해
	// 사용하는 type으로
	//		이 type 0 ~ 255, -128 ~ 127까지의 값만을 저장하는 데이터
	// 메모리 공간 크기가 1byte(8bit)
	// int 형은 java에서 4byte 크기를 사용
	// String형은 wrapper 클래스이다 보니 실제적으로 상당히 큰
	// 메모리 공간을 차지한다.
	// 단순히 flag와 같은 데이터를 취급할때는 char, byte형으로 사용하면
	// 메모리를 절약할 수 있다.
	private byte p_not_use;
	
}




