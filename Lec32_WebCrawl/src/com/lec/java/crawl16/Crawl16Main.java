package com.lec.java.crawl16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.databind.ObjectMapper;

//https://www.yogiyo.co.kr/api/v1/restaurants-geo/?items=20&lat=37.4970362721876&lng=127.036419886188&order=rank&page=0&search=
public class Crawl16Main {

	public static void main(String[] args) throws IOException {
		System.out.println("요기요 맛집정보 : header 추가");
		
		String url = "https://www.yogiyo.co.kr/api/v1/restaurants-geo/?items=20&lat=37.4970362721876&lng=127.036419886188&order=rank&page=0&search=";
		
		StringBuffer sb = readFromUrl(url);
		System.out.println(sb);
		
		ObjectMapper mapper = new ObjectMapper();
		 Yogi yoyo = mapper.readValue(sb.toString(), Yogi.class);
		
		for (Menu e : yoyo.getRestaurants()) {
			System.out.printf("%8s | %2.1f \n", e.getSlug(), e.getReview_avg());
		}
		
//		System.out.println(mapper.readValue(sb.toString(), Yogiyo.class)); // toString()을 이용하면 이렇게 출력해도 한번에 나온다.
		
		System.out.println("\n프로그램 종료");
	}// end main	
	
	public static StringBuffer readFromUrl(String urlAddress) {
		
		StringBuffer sb = new StringBuffer();  // response 받은 데이터 담을 객체
		
		URL url = null;    // java.net.URL
		HttpURLConnection conn = null; // java.net.HttpURLConnection
		
		InputStream in = null;
		InputStreamReader reader = null;   // byte 스트림 --> 문자기반 Reader
		BufferedReader br = null; 
		
		char [] buf = new char[512];   // 문자용 버퍼
		
		
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
				
				// 헤더 정보 추가
				conn.setRequestProperty("x-apikey", "iphoneap");
				conn.setRequestProperty("x-apisecret", "fe5183cc3dea12bd0ce299cf110a75a2");
				
				
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
	
}// end class

@JsonIgnoreProperties(ignoreUnknown = true)
class Yogi{
	
	private List<Menu> restaurants;

	public final List<Menu> getRestaurants() {
		return restaurants;}
	public final void setRestaurants(List<Menu> restaurants) {
		this.restaurants = restaurants;}
	

//	@Override
//	public String toString() {
//		StringBuffer sb = new StringBuffer("");
//		for(Menu e : restaurants) {
//			sb.append(e.toString() + "\n");
//		}
//		return sb.toString();
//	}
	
} // end Yogi

@JsonIgnoreProperties(ignoreUnknown = true)
class Menu{
	private String slug;
	private double review_avg;
	
	public Menu() {}
	public Menu(String slug, double review_avg) {
		super();
		this.slug = slug;
		this.review_avg = review_avg;
	}
	
	public final String getSlug() {
		return slug;}
	public final void setSlug(String slug) {
		this.slug = slug;}
	public final double getReview_avg() {
		return review_avg;}
	public final void setReview_avg(double review_avg) {
		this.review_avg = review_avg;}
//	
//	@Override
//	public String toString() {
//		return String.format("%s %.1f", slug, review_avg);
//	}
	
	
}// end Menu



















