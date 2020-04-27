package com.lec.java.crawl14;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/* XML, Json 파싱1
 * 
 * ■서울시 지하철호선별 역별 승하차 인원 정보 
 * http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12914&srvType=A&serviceKind=1&currentPageNo=1
 * 
 * 샘플url
 * XML 버젼
 * http://openapi.seoul.go.kr:8088/키값을넣으세요/xml/CardSubwayStatsNew/1/5/20181001
 * 예) http://openapi.seoul.go.kr:8088/5a54634764746e623131375a7072484e/xml/CardSubwayStatsNew/1/5/20181001
 *   
 * JSON 버젼
 * http://openapi.seoul.go.kr:8088/키값을넣으세요/json/CardSubwayStatsNew/1/5/20181001
 * 예) http://openapi.seoul.go.kr:8088/5a54634764746e623131375a7072484e/json/CardSubwayStatsNew/1/5/20181001 
 * */

public class Crawl14Main {

	public static void main(String[] args) throws IOException {
		System.out.println("jackson-databind 연습 : URL -> json -> Java");
		
		ObjectMapper mapper = new ObjectMapper();

		URL url = new URL("http://openapi.seoul.go.kr:8088/5a54634764746e623131375a7072484e/json/CardSubwayStatsNew/1/5/20181001"); ///CardSubwayStatsNew/1/5/  번호를 변경해주면 많아짐
		
		Subway subway = mapper.readValue(url, Subway.class); // 변환할 것  , 변환할 자바의 이름(변환해 저장되는 class)
		System.out.println(subway.getCardSubwayStatsNew().getList_total_count()); 
//		System.out.println(subway.getCardSubwayStatsNew().getRESULT().getCODE());
//		System.out.println(subway.getCardSubwayStatsNew().getRESULT().getMESSAGE());
		for (SubRow e : subway.getCardSubwayStatsNew().getRow()) {
			System.out.printf("%5s | %8s역\t [승차 :%6s 하차 :%6s]\n",
					e.getLineNum(), e.getStationName(), e.getRidePassenger() , e.getAlightPassenger() );
		}
		
		
		
		System.out.println("\n프로그램 종료");
	}// end main

}// end class

@JsonIgnoreProperties(ignoreUnknown = true)
class Subway{
	public Stats CardSubwayStatsNew; //public 을 써주니까 nullpoint 에러가 안뜸 
	// 프러퍼티 이름이 동일해야함 대소문자도 똑같이!  오브젝트 타입 

	//get&set은 꼭만들기
	public Stats getCardSubwayStatsNew() {
		return CardSubwayStatsNew;
	}

	public void setCardSubwayStatsNew(Stats cardSubwayStatsNew) {
		CardSubwayStatsNew = cardSubwayStatsNew;
	}
	
}// end Subway


@JsonIgnoreProperties(ignoreUnknown = true) // 원래 모든 프로퍼티를 가져와야하는데 하나만 가져옴으로 @JsonIgnoreProperties 을 꼭 해줘야한다. class안에 명시되지 않은것들은 무시하겠다는의미
class Stats { //CardSubwayStatsNew 안에 list
	private int list_total_count;

	private List<SubRow> row; // list를 담고있는 row
//	public rr RESULT; // 이해하기위해 연습해봄
	
	// RESULT 까지 뽑아야하지만 굳이 사용하지 않기때문에 만들지 않음
	
	
//	public rr getRESULT() {return RESULT;}
//	public void setRESULT(rr rESULT) {RESULT = rESULT;}
	
	public int getList_total_count() {
		return list_total_count;}
	public void setList_total_count(int list_total_count) {
		this.list_total_count = list_total_count;}
	public List<SubRow> getRow() {
		return row;}
	public void setRow(List<SubRow> row) {
		this.row = row;}
	
	
}// end Stats


// 변환하고자 하는 name 과 동일하게 작성 하지만
// JSON 필드명과 매핑되는 Java 객체의 변수명을 달리하고 싶다면 @JsonProperty
@JsonIgnoreProperties(ignoreUnknown = true) 
class SubRow{
	@JsonProperty("LINE_NUM")  // JSON 의 LINE_NUM --> lineNum으로 매핑
	private String lineNum;
	
	@JsonProperty("SUB_STA_NM") 
	private String stationName;
	
	@JsonProperty("RIDE_PASGR_NUM") 
	private int ridePassenger;
	
	@JsonProperty("ALIGHT_PASGR_NUM") 
	private int alightPassenger;

	public SubRow() {	super();}

	public SubRow(String lineNum, String stationName, int ridePassenger, int alightPassenger) {
		super();
		this.lineNum = lineNum;
		this.stationName = stationName;
		this.ridePassenger = ridePassenger;
		this.alightPassenger = alightPassenger;
		}
	
	public String getLineNum() {
		return lineNum;}
	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;}
	public String getStationName() {
		return stationName;}
	public void setStationName(String stationName) {
		this.stationName = stationName;}
	public int getRidePassenger() {
		return ridePassenger;}
	public void setRidePassenger(int ridePassenger) {
		this.ridePassenger = ridePassenger;}
	public int getAlightPassenger() {
		return alightPassenger;}
	public void setAlightPassenger(int alightPassenger) {
		this.alightPassenger = alightPassenger;}

	
}//end SubRow


//class rr{
//	public String CODE;
//	public String MESSAGE;
//	
//	public String getCODE() {
//		return CODE;
//	}
//	public void setCODE(String cODE) {
//		CODE = cODE;
//	}
//	public String getMESSAGE() {
//		return MESSAGE;
//	}
//	public void setMESSAGE(String mESSAGE) {
//		MESSAGE = mESSAGE;
//	}
//	public rr(String cODE, String mESSAGE) {
//		super();
//		CODE = cODE;
//		MESSAGE = mESSAGE;
//	}
//	public rr() {
//		super();
//	}
//	
//	
//}

// 안드로이드 시간에  만들어욤
//Retrofit  
//Volley














