package daily.dailysum;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 연습 : 자치구단위 서울 생활인구 일별 집계표
 * ■자치구단위 서울 생활인구 일별 집계표
 * 	http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-15379&srvType=S&serviceKind=1&currentPageNo=1
 * http://data.seoul.go.kr/dataList/OA-15379/S/1/datasetView.do;jsessionid=B90F15E83DE470F5DE0EEB52A212807D.new_portal-svr-21
 * 	http://openapi.seoul.go.kr:8088/(인증키)/(요청파일타입)/SPOP_DAILYSUM_JACHI/(요청시작INDEX)/(요청종료INDEX)/(기준일ID)/(시군구코드)
 * 
 * 샘플url
 * 	XML 버젼
 * 	http://openapi.seoul.go.kr:8088/(인증키)/xml/SPOP_DAILYSUM_JACHI/1/5/
 * 		예] http://openapi.seoul.go.kr:8088/5a54634764746e623131375a7072484e/xml/SPOP_DAILYSUM_JACHI/1/5/
 * 		예] http://openapi.seoul.go.kr:8088/5a54634764746e623131375a7072484e/xml/SPOP_DAILYSUM_JACHI/1/5/20190101
 * 		예] http://openapi.seoul.go.kr:8088/5a54634764746e623131375a7072484e/xml/SPOP_DAILYSUM_JACHI/1/5/20190101/11000
 * 	JSON 버젼
 * 	http://openapi.seoul.go.kr:8088/(인증키)/json/SPOP_DAILYSUM_JACHI/1/5/
 * 		예] http://openapi.seoul.go.kr:8088/5a54634764746e623131375a7072484e/json/SPOP_DAILYSUM_JACHI/1/5/	
 * 		예] http://openapi.seoul.go.kr:8088/5a54634764746e623131375a7072484e/json/SPOP_DAILYSUM_JACHI/1/5/20190101
 * 		예] http://openapi.seoul.go.kr:8088/5a54634764746e623131375a7072484e/json/SPOP_DAILYSUM_JACHI/1/5/20190101/11000
 * 
 * ※ 한번에 1000개 까지의 데이터만 볼수 있슴
 * 5a54634764746e623131375a7072484e
 */

/*  입력예]
 *  날짜입력: 20190101
 *  시작Index : 1
 *  끝Index: 5
 *  
 *  [XML]
 *  날짜             구ID        총생활인구수           일최대이동인구수
 *  ----------------------------------------------------------------------
 *  20190101		11000    11121182.98210      4764635.18080 
 *  20190101    11110    274919.65940        177877.95000 
 *  20190101    11140    267096.65940        149729.45840 
 *  20190101    11170    315220.18480        149329.14120 
 *  20190101    11200    351993.77950        153738.94490
 *   
 *  [JSON]
 *  날짜             구ID        총생활인구수           일최대이동인구수
 *  ----------------------------------------------------------------------
 *  20190101		11000    11121182.98210      4764635.18080 
 *  20190101    11110    274919.65940        177877.95000 
 *  20190101    11140    267096.65940        149729.45840 
 *  20190101    11170    315220.18480        149329.14120 
 *  20190101    11200    351993.77950        153738.94490 
<STDR_DE_ID>20190101</STDR_DE_ID>
<SIGNGU_CODE_SE>11000</SIGNGU_CODE_SE>
<TOT_LVPOP_CO>11121182.98210</TOT_LVPOP_CO>
<DAIL_MXMM_MVMN_LVPOP_CO>4764635.18080</DAIL_MXMM_MVMN_LVPOP_CO>
 * 
 */

// ★ 주목 ★
// XML 은 Jsoup 를 활용하여 파싱하세요 12
// JSON 은  jackson 을 활용하여 파싱하세요 131415


public class DailySumMain {
	
	public static final String API_KEY = "5a54634764746e623131375a7072484e";
	
	public static void main(String[] args) throws IOException {
		System.out.println("자치구단위 서울 생활인구 일별 집계표");
//Scanner sc = new Scanner(System.in);
//		
//		//입력
//		System.out.print("날짜입력 :");
//		String date = sc.nextLine();
//		System.out.print("시작Index :");
//		int startIndex = sc.nextInt();
//		System.out.print("끝Index : ");
//		int endIndex = sc.nextInt();
//		sc.close();
		int date = 20190101;
		int startindex = 1;
		int endindex = 5;
		
		System.out.println("------XML parser------");
		System.out.println("날짜\t\t구ID\t\t총생활인구수\t\t일최대이동인구수");
		System.out.println("-----------------------------------------------------------------");
		String url = buildUrlAddress("xml", startindex, endindex, date);
		XmlParser(url);
	
		System.out.println();
		System.out.println("------Jsoup parser------");
		System.out.println("날짜\t\t구ID\t\t총생활인구수\t\t일최대이동인구수");
		System.out.println("-----------------------------------------------------------------");
		ObjectMapper mapper = new ObjectMapper();
		url = buildUrlAddress("json", startindex, endindex, date);
		URL url1 = new URL(url);
		LivePerson live =  mapper.readValue(url1, LivePerson.class);
		for (Local r : live.getSpopDaliy().getRow()) {
			System.out.printf("%s | %s | %s| %s\n", r.getDateId(), r.getSignguCode() , r.getTotLv(), r.getDailLv());
		}
		

	} // end main
	
	public static String buildUrlAddress(String reqType, int startin, int endin, int date) {
		String urlAddress = String.format("http://openapi.seoul.go.kr:8088/%s/%s/SPOP_DAILYSUM_JACHI/%d/%d/%d", API_KEY, reqType, startin, endin,date);
		return urlAddress;
	}
	public static void XmlParser(String url) throws IOException{
		Document doc = Jsoup.connect(url).parser(Parser.xmlParser()).get(); // 
		Elements elements = doc.select("row");
		for (Element e : elements) {
			String STDR_DE_ID = e.selectFirst("STDR_DE_ID").text().trim();
			String SIGNGU_CODE_SE = e.selectFirst("SIGNGU_CODE_SE").text().trim();
			String TOT_LVPOP_CO = e.selectFirst("TOT_LVPOP_CO").text().trim();
			String DAIL_MXMM_MVMN_LVPOP_CO = e.selectFirst("DAIL_MXMM_MVMN_LVPOP_CO").text().trim();
			System.out.printf("%s | %s | %s | %s\n", STDR_DE_ID, SIGNGU_CODE_SE, TOT_LVPOP_CO, DAIL_MXMM_MVMN_LVPOP_CO);
		}
	}
	

} // end class

@JsonIgnoreProperties(ignoreUnknown = true)
class LivePerson{  
	@JsonProperty("SPOP_DAILYSUM_JACHI")
	public LocalRow spopDaliy;

	public final LocalRow getSpopDaliy() {
		return spopDaliy;}
	public final void setSpopDaliy(LocalRow spopDaliy) {
		this.spopDaliy = spopDaliy;}
	
	
	
}

@JsonIgnoreProperties(ignoreUnknown = true)
class LocalRow{
	private List<Local> row;

	public final List<Local> getRow() {
		return row;}
	public final void setRow(List<Local> row) {
		this.row = row;}
	
	
	
}
@JsonIgnoreProperties(ignoreUnknown = true)
class Local{
	@JsonProperty("STDR_DE_ID")
	private String dateId;
	@JsonProperty("SIGNGU_CODE_SE")
	private String signguCode;
	@JsonProperty("TOT_LVPOP_CO")
	private String totLv;
	@JsonProperty("DAIL_MXMM_MVMN_LVPOP_CO")
	private String dailLv;
	
	
	public Local() {}

	public Local(String dateId, String signguCode, String totLv, String dailLv) {
		super();
		this.dateId = dateId;
		this.signguCode = signguCode;
		this.totLv = totLv;
		this.dailLv = dailLv;
		}
	public final String getDateId() {return dateId;}
	public final void setDateId(String dateId) {this.dateId = dateId;}
	public final String getSignguCode() {return signguCode;}
	public final void setSignguCode(String signguCode) {this.signguCode = signguCode;}
	public final String getTotLv() {return totLv;}
	public final void setTotLv(String totLv) {	this.totLv = totLv;}
	public final String getDailLv() {return dailLv;}
	public final void setDailLv(String dailLv) {this.dailLv = dailLv;}
	
}

















