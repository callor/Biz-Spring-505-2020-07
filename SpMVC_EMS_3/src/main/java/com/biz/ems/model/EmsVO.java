package com.biz.ems.model;

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
@ToString
@Builder
public class EmsVO {
	
	private long id;			//number
	private String from_email;	//varchar2(30 byte)
	private String to_email;	//varchar2(30 byte)
	private String s_date;		//varchar2(10 byte)
	private String s_time;		//varchar2(10 byte)
	private String s_subject;	//nvarchar2(125 char)
	private String s_content;	//nvarchar2(2000 char)
	private String s_file1;		//nvarchar2(125 char)
	private String s_file2;		//nvarchar2(125 char)

}
