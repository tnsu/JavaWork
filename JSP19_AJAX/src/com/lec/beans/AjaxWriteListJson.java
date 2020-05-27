package com.lec.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

// json 데이터 받아오기
// POJO : Plain Old JavaObject
//
public class AjaxWriteListJson {
	// private 안걸어주면 그냥 가져올수 있어서 getter/setter 필요없음 
	int count; // 데이터 개수
	String status; // 처리결과
	
	@JsonIgnore  // 제외할 데이터 일때 사용
	String memo; // response 에서 제외할 필드

	@JsonProperty("data") // Json Property 이름과 Java 필드명이 다른경우
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
