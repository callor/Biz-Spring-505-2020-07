package com.biz.hello.model;

public class StudentVO {
	
	private String st_num;
	private String st_name;
	private String st_tel;
	private String st_addr;
	private int st_grade;
	
	public StudentVO() {
	}
	public StudentVO(String st_num, String st_name, String st_tel, String st_addr, int st_grade) {
		super();
		this.st_num = st_num;
		this.st_name = st_name;
		this.st_tel = st_tel;
		this.st_addr = st_addr;
		this.st_grade = st_grade;
	}
	
	public String getSt_num() {
		return st_num;
	}
	public void setSt_num(String st_num) {
		this.st_num = st_num;
	}
	public String getSt_name() {
		return st_name;
	}
	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}
	public String getSt_tel() {
		return st_tel;
	}
	public void setSt_tel(String st_tel) {
		this.st_tel = st_tel;
	}
	public String getSt_addr() {
		return st_addr;
	}
	public void setSt_addr(String st_addr) {
		this.st_addr = st_addr;
	}
	public int getSt_grade() {
		return st_grade;
	}
	public void setSt_grade(int st_grade) {
		this.st_grade = st_grade;
	}
	
	@Override
	public String toString() {
		return "StudentVO [st_num=" + st_num + ", st_name=" + st_name + ", st_tel=" + st_tel + ", st_addr=" + st_addr
				+ ", st_grade=" + st_grade + "]";
	}
	
	
	
	

}
