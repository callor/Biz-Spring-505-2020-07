package com.biz.valid.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * validation-api, hibernate-validate를 이용한 서버단 유효성검사
 * 
 * @NotBlank(message="") : 빈칸이면 안된다
 * @NotNull : Null이면 안된다
 * @Null : 입력하면 안된다.
 * @Size(max,min) : 배열, 문자열의 개수범위
 * @Email : Email 형식에 맞아야 한다.
 * @Min(x) :숫자가 x 이하가 아니어야 한다
 * @Max(x) : 숫자가 x 이상이면 안된다
 * @DecimalMax(x) : 숫자가 10진수 이고 x 이상이면 안된다
 * @DecimalMinx(x) : 숫자가 10진이수 이고 x 이하이면 안된다
 * 
 *
 */



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserVO {

	@NotBlank(message = "* 이름은 반드시 입력해야 합니다.")
	private String name;
	
	@NotBlank(message = "* Email은 반드시 입력해야 합니다.")
	@Email(message = "* Email 형식이 잘못 되었습니다.")
	private String email;
	
	@DecimalMax(value = "30",message = "* 나이는 30세 이하로 입력하세요")
	@DecimalMin(value = "10", message = "* 나이는 10세 이상으로 입력하세요")
	// @Size(min = 10, max = 30, message = "* 나이는 10세 ~ 30세까지만 입력하세요.")
	private String age;
	
}
