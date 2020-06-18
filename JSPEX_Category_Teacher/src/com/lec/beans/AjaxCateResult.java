package com.lec.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AjaxCateResult {
	
	private int count; // 결과 데이터 개수
	private String status; // 처리결과
	private String message;// 처리 매세지
	public AjaxCateResult() {
		super();
	}

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
