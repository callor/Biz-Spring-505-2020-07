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
	
	/*
	 * DB p_not_use 칼럼의 값이 NULL(IS NULL)이면 정상적인 데이터이고
	 * NULL 이  아니면 (IS NOT NULL)이면 삭제된 데이터로 취급하기로 하였다
	 * Update를 수행할때 VO의 칼럼에 값이 null이면 
	 * 		jdbcType.VARCHAR, config에 수행 설정 때문에
	 * DB에 업데이트할때 NULL이 아닌 다른 값이 저장되어 버린다.
	 * Dao.Update() 수행하기 전에 VO에 해당 칼럼의 값을 강제로 NULL로 해줄필요가 있다.
	 * 하지만 기본형인 변수는 NULL을 저장하여 DB로 보낼수 없다.
	 * 
	 * 때문에
	 * 이 칼럼에 타입을 byte 형이 아닌 Byte wrapper 클래스형으로 설정한다.
	 *  
	 */
	private Byte p_not_use;
	
}




