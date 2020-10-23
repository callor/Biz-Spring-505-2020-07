package com.biz.bbs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageVO {

	private long i_seq;
	private long i_b_seq;
	private String i_org_name;
	private String i_file_name;
	private long i_down;
		
}
