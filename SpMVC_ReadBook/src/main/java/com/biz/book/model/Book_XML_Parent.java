package com.biz.book.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rss")
public class Book_XML_Parent {

	@XmlElement(name="channel")
	public Book_XML_Channel channel;

}
