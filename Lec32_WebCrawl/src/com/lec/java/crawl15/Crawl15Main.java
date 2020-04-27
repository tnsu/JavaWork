package com.lec.java.crawl15;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
■서울시 지하철 역사 정보
http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12753&srvType=A&serviceKind=1&currentPageNo=1

샘플url

XML 버젼
http://swopenAPI.seoul.go.kr/api/subway/5a54634764746e623131375a7072484e/xml/stationInfo/1/5/서울 utf-8

JSON 버젼
http://swopenAPI.seoul.go.kr/api/subway/5a54634764746e623131375a7072484e/json/stationInfo/1/5/서울
stationList
<statnNm>서울</statnNm>
<subwayId>1065</subwayId>
<subwayNm>공항철도</subwayNm>

*/
public class Crawl15Main {

	public static void main(String[] args) throws IOException {
		System.out.println("jackson-databind 연습 : URL -> json -> Java");
		
		ObjectMapper mapper = new ObjectMapper();
		
		URL url = new URL("http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/json/stationInfo/1/5/%EC%84%9C%EC%9A%B8");
		
		Sub subway  = mapper.readValue(url, Sub.class);
		
	
		for (Row e : subway.getStationList()) {
			System.out.printf("%2s | %6s | %6s | %5s\n",e.getRowNum(),  e.getStatnNm() ,e.getSubwayId(), e.getSubwayNm());
		}
		
		
		System.out.println("\n 프로그램 종료");
	}// end main

}// end class

@JsonIgnoreProperties(ignoreUnknown = true)
class Sub{
	private List<Row> stationList;  //ArrayList<> 사용해도됨
 
	public List<Row> getStationList() {
		return stationList;}
	public void setStationList(List<Row> stationList) {
		this.stationList = stationList;}
	
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Row{
	private int rowNum;
	private String statnNm;
	private String subwayId;
	private String subwayNm;
	
	public Row() {
		super();
	}
	
	public Row(int rowNum, String statnNm, String subwayId, String subwayNm) {
		super();
		this.rowNum = rowNum;
		this.statnNm = statnNm;
		this.subwayId = subwayId;
		this.subwayNm = subwayNm;
	}
	
	public int getRowNum() {
		return rowNum;	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;}
	public String getStatnNm() {
		return statnNm;}
	public void setStatnNm(String statnNm) {
		this.statnNm = statnNm;}
	public String getSubwayId() {
		return subwayId;}
	public void setSubwayId(String subwayId) {
		this.subwayId = subwayId;}
	public String getSubwayNm() {
		return subwayNm;}
	public void setSubwayNm(String subwayNm) {
		this.subwayNm = subwayNm;}
	
}






















