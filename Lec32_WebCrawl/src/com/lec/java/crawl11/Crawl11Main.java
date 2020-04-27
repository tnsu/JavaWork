package com.lec.java.crawl11;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/*
 ■서울시 지하철 역사 정보
http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12753&srvType=A&serviceKind=1&currentPageNo=1

샘플url

XML 버젼
http://swopenAPI.seoul.go.kr/api/subway/5a54634764746e623131375a7072484e/xml/stationInfo/1/5/서울 utf-8

JSON 버젼
http://swopenAPI.seoul.go.kr/api/subway/5a54634764746e623131375a7072484e/json/stationInfo/1/5/서울
<statnNm>서울</statnNm>
<subwayId>1065</subwayId>
<subwayNm>공항철도</subwayNm>

 */

public class Crawl11Main {
	public static final String REQ_SERVICE = "stationInfo";
	public static final String API_KEY = "5a54634764746e623131375a7072484e"; // 인증받은 키값넣기
	
	public static void main(String[] args) throws IOException {
		System.out.println("서울시 지하철 역사(station) 정보");
		int startIndex;	
		int endIndex;	
		String statnNm;
		
		String url_address; 
		StringBuffer sb; // response 받은 텍스트
		
		//: API url 에 필요한 Parameter 들
		startIndex = 0;
		endIndex = 5;
		statnNm = "서울";
		
		System.out.println("--- XML 파싱 ----");
		//TODO
		/*
		url_address = buildUrlAddress("xml", startIndex, endIndex, statnNm);
		System.out.println(url_address); // 확인
		sb = readFromUrl(url_address);
//		System.out.println(sb); // 확인
		parseXML(sb.toString());
		*/
		
		System.out.println();
		System.out.println("--- JSON 파싱 ----");
		url_address = buildUrlAddress("json", startIndex, endIndex, statnNm);
		sb = readFromUrl(url_address);
	parseJSON(sb.toString());
		
		
		
		
		System.out.println("\n 프로그램 종료");
	}// end main
	
	 //TODO
	public static String buildUrlAddress(String reqType, int startIndex, int endIndex, String statnNm) throws UnsupportedEncodingException {
		String url_address = String.format("http://swopenAPI.seoul.go.kr/api/subway/%s/%s/%s/%d/%d/%s",
                API_KEY, reqType, REQ_SERVICE, startIndex, endIndex, URLEncoder.encode(statnNm, "utf-8"));  
		return url_address;
		
	}
	/**
	 * 
	 * @param urlAddress : 주어진 url 주소
	 * @return 서버로부터 받은 텍스트 데이터(HTML)리턴
	 */
	public static StringBuffer readFromUrl(String urlAddress) {
		
		StringBuffer sb = new StringBuffer();  // response 받은 데이터 담을 객체
		
		URL url = null;    // java.net.URL
		HttpURLConnection conn = null; // java.net.HttpURLConnection
		
		InputStream in = null;
		InputStreamReader reader = null;   // byte 스트림 --> 문자기반 Reader
		BufferedReader br = null; 
		
		char [] buf = new char[512];   // 문자용 버퍼
		
		// BufferedReader <- InputStreamReader <- InputStream <- HttpURLConnection 
		
		try {
			url = new URL(urlAddress);
			conn = (HttpURLConnection) url.openConnection();  // Connection 객체 생성
			
			if(conn != null) {
				conn.setConnectTimeout(2000);  // 2초이내에 '연결' 이 수립안되면
											// java.net.SocketTimeoutException 발생
				
				conn.setRequestMethod("GET");  // GET 방식 request
				// "GET", "POST" ...
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
				conn.setUseCaches(false);  // 캐시사용 안함
				
				System.out.println("request 시작: " + urlAddress);
				conn.connect();    // request 발생 --> 이후 response 받을때까지 delay
				System.out.println("response 완료");
				
				// response 받은후 가장 먼저 response code 값 확인
				int responseCode = conn.getResponseCode();
				System.out.println("response code : " + responseCode);
				// 참조 : https://developer.mozilla.org/ko/docs/Web/HTTP/Status
				if(responseCode == HttpURLConnection.HTTP_OK) {
					
					in = conn.getInputStream();  // InputStream <- HttpURLConnection 
					reader = new InputStreamReader(in, "utf-8"); // InputStreamReader <- InputStream
					br = new BufferedReader(reader);  // BufferedReader <- InputStreamReader
					
					int cnt;  // 읽은 글자 개수
					while((cnt = br.read(buf)) != -1) {
						sb.append(buf, 0, cnt);  // response 받은 텍스트를 StringBuffer 에 추가
					}
					
				} else {
					System.out.println("response 실패");
					return null;
				}
			} else {
				System.out.println("conn null");
				return null;
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(conn != null) conn.disconnect();   // 작업 끝나고 Connection 해제
		}
		return sb;
	}
	
	public static void parseXML(String xmlText) {
		DocumentBuilderFactory dbFactory;
		DocumentBuilder dBuilder;
		
		try {
			// DOM parser 객체 생성
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			
			InputStream in = new ByteArrayInputStream(xmlText.getBytes("utf-8"));
			
			Document dom = dBuilder.parse(in);
			
			Element docElement = dom.getDocumentElement();
			
			docElement.normalize(); // 숨어있는 텍스트노드 없에줌 (좌우공백없에고 한쪽으로 몰아주는 )
			
			NodeList nList =  docElement.getElementsByTagName("row");
			System.out.println("<row> 개수 : " + nList.getLength());
			
			System.out.println();
			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i); 
				//element node 인 경우만 파싱 진행
				if(node.getNodeType() != Node.ELEMENT_NODE) continue;
				
				Element rowElement = (Element)node;
				//  라인을 뽑고 그 다음글자를 꺼내오는 과정
				String rowNum = rowElement.getElementsByTagName("rowNum").item(0).getChildNodes().item(0).getNodeValue().trim();
				String statnNm = rowElement.getElementsByTagName("statnNm").item(0).getChildNodes().item(0).getNodeValue().trim();
				String subwayId = rowElement.getElementsByTagName("subwayId").item(0).getChildNodes().item(0).getNodeValue().trim();
				String subwayNm = rowElement.getElementsByTagName("subwayNm").item(0).getChildNodes().item(0).getNodeValue().trim();
				//이해 안되면 영상보기
				//item(0) 어파피 한개 밖에 없는 것을 알고있으니까  getChildnodeFirst() 이런게없음
				// getNodeValue() :텍스트가 나옴
				System.out.printf("%2s | %6s | %6s | %5s\n",rowNum, statnNm, subwayId, subwayNm);
				
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}// end pardeXML()
	
	public static void parseJSON (String jsonText) {
		JSONObject jobj = new JSONObject(jsonText); // JSON파싱 : JSONObject <- jsonText(String) 
		JSONArray row = jobj.getJSONArray("stationList");
		
		System.out.println("<row>의 개수" + row.length());
		for (int i = 0; i < row.length(); i++) {
			JSONObject station = row.getJSONObject(i);
			
			String statnNm = station.getString("statnNm");
			String subwayNm = station.getString("subwayNm");
			int rowNum = station.getInt("rowNum");
			String subwayId = station.getString("subwayId");
			
			System.out.printf("%2s | %6s | %6s | %5s\n",rowNum, statnNm, subwayId, subwayNm);
			
			
		}
		
	}// end parseJSON()
	
}// end class




















