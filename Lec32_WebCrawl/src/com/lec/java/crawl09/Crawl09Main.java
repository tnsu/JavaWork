package com.lec.java.crawl09;
//연습
//네이버 '검색어' 
//블로그 
//- post title
//- post url
//- blog title

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//페이징 구현

public class Crawl09Main {

	public static void main(String[] args) throws IOException {
		System.out.println("네이버 검색, 페이징");
		for (int i = 0; i <= 10; i++) {
				crawlDaumBlog("자바", i);
		}
		
		
		System.out.println("\n프로그램 종료");
	}//end main
	/**
	 * 
	 * @param search 검색어
	 * @param page 검색할 결과 page 번호
	 * @throws IOException 
	 */
	public static void crawlDaumBlog(String search, int page) throws IOException {
		// 목록에서 크롤링 할 내용
		// post title
		// post link url
		// blog title
		
		
		//매개변수 검증
		if(search == null || search.trim().length() == 0 || page < 1) return;
		
		String url;
		Document doc;
		Response response;
		Elements elements;
		Elements rowElements;

		System.out.println(page + " 페이지]");
		
		url = String.format("https://search.naver.com/search.naver?date_from=&date_option=0&date_to=&dup_remove=1&nso=&post_blogurl=&post_blogurl_without=&"
				+ "query=%s&sm=tab_pge&srchby=all&st=sim&where=post&start=%d", URLEncoder.encode(search, "utf-8"), (page - 1) * 10 + 1);
		System.out.println(url); // 확인
		 doc= Jsoup.connect(url).execute().parse();
		 elements = doc.select("#elThumbnailResultArea  .sh_blog_top");
		System.out.println(elements.size());
		for(Element e : elements) {
			String postTitle = e.selectFirst("dt > a").text().trim();
			String blogTitle = e.selectFirst("span.inline").text();
			String postUrl = e.selectFirst("a").attr("href").trim();
			System.out.println(postTitle + " | " + blogTitle + " | " + postUrl);
		}
		System.out.println();
		
// 강사님
//		elements = doc.select("#elThumbnailResultArea > li.sh_blog_top > dl");
//		//System.out.println(elements.size());  // 확인용
//		
//		for(Element e : elements) {
//			String postTitle = e.selectFirst("a.sh_blog_title").text().trim();
//			String blogTitle = e.selectFirst("dd.txt_block a.txt84").text().trim();
//			String postUrl = e.selectFirst("a.sh_blog_title").attr("href").trim();
//			
//			System.out.println(postTitle + " : " + blogTitle + " : " + postUrl);
//		} // end for
	}// end crawlDaumBlog
	
}// end class
