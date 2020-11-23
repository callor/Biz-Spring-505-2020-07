package com.biz.book.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

@Entity
@Table(name="tbl_book",schema = "user1")
public class BookVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "S_BOOK")
	@SequenceGenerator(name="S_BOOK",
				sequenceName = "SEQ_BOOK",
				allocationSize = 1)	
	private Long id;
	
	@Column(name="title",columnDefinition = "nVARCHAR2(120)")
	private String title;
	
	@Column(columnDefinition = "nVARCHAR2(50)")
	private String author;
	
	@Column(columnDefinition = "nVARCHAR2(50)")
	private String comp;
	
	@Column(nullable = true)
	private int price;

}
