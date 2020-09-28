package com.biz.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReadBookVO {

	private String r_date;
	private String r_stime;
	private String r_etime;
	private String r_comment;
	
	private int r_read_time;
	
}
