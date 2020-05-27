package com.lec.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "WriteList")  // 이름을 변경해 줄때 사용
public class AjaxWriteListXML {

	// 각각의 데이터에 Property 를 해줘야 좋다.? 
	
	int count; // 데이터 개수
	
	String status; // 처리결과
	
	@JsonIgnore
	String memo; // response 에서 제외할 필드

	// List 멤버 변수 --> <data>~ </data> 들로 만들기
	@JacksonXmlElementWrapper(useWrapping = false)
	
	@JacksonXmlProperty(localName = "data")
	List<WriteDTO> list;  // 변수명이 list 인것에 주목하기
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public List<WriteDTO> getList() {
		return list;
	}
	public void setList(List<WriteDTO> list) {
		this.list = list;
	}
	
	
	
}
