package com.lec.java.crawl03;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawl03Main {

	public static void main(String[] args) throws IOException{
		System.out.println("Daum 인기 검색어");
		//15개
		String url; 
		Response response;
		Document doc; 
		Element element; 
		Elements elements; 
		
		url = "https://www.daum.net";
		response =Jsoup.connect(url).execute(); 
		doc = response.parse();
		
		elements = doc.select("div.slide_favorsch ul li a");
		System.out.println(elements.size() + " 개");
		
		for (Element e : elements) {
			System.out.println(e.text().trim()); 
			System.out.println(e.attr("href"));
		}
		
		System.out.println("\n 프로그램 종료");
	}// end main

}// end class
