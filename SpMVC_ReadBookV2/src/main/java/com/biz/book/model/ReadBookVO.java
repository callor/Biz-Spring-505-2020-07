package com.biz.book.model;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Alias("ReadBook")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReadBookVO {

	private long r_seq;
	private long r_book_seq;
	private String r_date;
	private String r_stime;
	private String r_etime;
	private String r_comment;
	
	private int r_read_time;
	
}
