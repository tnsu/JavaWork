package com.lec.java.crawl04;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Scanner;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawl04Main {

	public static void main(String[] args) throws IOException {
		
		String url; 
		Document doc; 
		Elements elements; 
		Element element;
		String searchKeyword;
		
		Scanner sc  = new Scanner(System.in);
		System.out.println("검색어를 입력하세요");
		searchKeyword = sc.nextLine();
		sc.close();
		
		String encoded = URLEncoder.encode(searchKeyword, "utf-8");  // 네이버 검색페이지는 utf-8로 url encode 함, 네이버 페이지가 utf-8로 인코딩하기때문에
		
		url = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="+encoded;
		System.out.println(url);
		
		doc = Jsoup.connect(url).execute().parse(); //Response 객체 안하고 하는법
		elements = doc.select("div#nx_related_keywords dd ul._related_keyword_ul li");
		System.out.println(elements.size() + " 개");
		
		for (Element e : elements) {
			element = e.selectFirst("a"); // e.attr("href");  : 주소나오게 하려면
			System.out.println(e.text().trim()); 
		}
//		// 연관검색어 출력하기, 강사님
//				elements = doc.select(".lst_relate ul li");		
//				System.out.println("총 " + elements.size() + "개");
//				
//				for(Element e : elements) {
//					System.out.println(e.selectFirst("a").text());
//				}
//				
		
		System.out.println("\n프로그램 종료");
	}// end main

}// end class























