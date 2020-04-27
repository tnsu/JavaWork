package com.lec.java.crawl07;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 	
 	검색어 입력받아
 	첫 페이지 '국내도서' 첫페이지 20개 아이템 크롤링
 	책이름/ 책가격/ 상세페이지 URL/ 썸네일 Url
 	
 	인터파크 검색페이지는  euc-kr 로 URL 인코딩 되어 있다.
    한글 1글자당 2byte 인코딩   
 
 */
public class Crawl07Main {
	
	private static final String PATH = "books";
	
	public static void main(String[] args) throws IOException {
		System.out.println("인터파크 검색결과 페이지");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어를 입력 : ");
		String search = sc.nextLine();
		sc.close();

		Crawl07Main app = new Crawl07Main();
		ArrayList<InfoBook> list= app.crawlInterPark(search);
		
		//썸네일 이미지 다운로드 받아서 thumb001.jpg ~ thumb020.jpg... 
		int fileIndex = 1;
		for (InfoBook e : list) {
			System.out.println(e); //크롤링 정보 출력
			
			// 썸네일 이미지 다운로드
			String fileName = String.format(PATH + File.separator + "thumb%03d.jpg", fileIndex);
			URL imgUrl = new URL(e.getImgUrl());
			
			BufferedImage imgDate = ImageIO.read(imgUrl);
			File file = new File(fileName);
			ImageIO.write(imgDate, "jpg", file);
			System.out.println( fileName + " 이 저장되었습니다.");
			fileIndex++;
		}
		System.out.println("\n프로그램 종료");
	}//end main

	private ArrayList<InfoBook> crawlInterPark(String search) throws IOException { // main 에서 다이렉트로 선언이안되서 app 인스턴스 생성
		ArrayList<InfoBook> list = new ArrayList<InfoBook>();
		String url;
		Document doc;
		Response response;
		Elements elements;
		Elements rowElements;

		//http://book.interpark.com
		url = "http://bsearch.interpark.com/dsearch/book.jsp?sch=all&sc.shopNo=&bookblockname=s_main&booklinkname=s_main&bid1=search_auto&bid2=product&bid3=000&bid4=001&query="
				+ URLEncoder.encode(search,"euc-kr");
		System.out.println(url); // 확인용
		
		doc = Jsoup.connect(url).execute().parse();
		rowElements = doc.select("#content > #bookresult div.list_wrap"); //띄어쓰기를 하지 않으면 에러뜸 
		System.out.println(rowElements.size() + "개"); //확인용 
		for(Element e : rowElements) {
			
			String imgUrl = e.selectFirst("div.bookImg  div.bimgWrap > a > img").attr("src").trim();
//		System.out.println(imgUrl); // 확인용
			Element infoElement = e.selectFirst("div.info > p.tit > b a");
			String bookTitle = infoElement.text().trim();
			String linkUrl =infoElement.attr("href").trim();
			double price= Double.parseDouble(e.selectFirst("div.price > div.topCon  p.defaultNum").text().trim().replace(",", "").replace("원", ""));
//			 Double.parseDouble(e.selectFirst("div.price  > p.FnowCoupon  > span.nowMoney").text().trim().split("원")[0].replace(",", ""));
			list.add(new InfoBook(bookTitle, price, linkUrl, imgUrl));
		}// end for
		
		return list;
	}// 
	
	
	
	
}// end class











