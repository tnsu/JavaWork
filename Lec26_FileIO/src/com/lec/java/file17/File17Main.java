package com.lec.java.file17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/* 
 HTML 데이터, 웹데이터 (텍스트)
 Java 에서 웹 연결을 위한 객체 두가지
 	URL : 웹 상의 주소, 
 	HttpURLConnection : 웹연결
  		URLConnection
  		 └─ HttpURLConnection
  
  	java.io.Reader    프로그램이 '문자 단위' 데이터를 읽어들이는(read) 통로
  		├─ java.io.InputStreamReader    // 스트림 기반의 reader
  		└─ java.io.BufferedReader 		// 문자(character) 기반 reader
 */

public class File17Main {

	public static void main(String[] args) {
		System.out.println("웹데이터 가져오기(텍스트)");

		String url = "https://www.naver.com/srchrank?frm=main&ag=all&gr=1&ma=-2&si=0&en=0&sp=0";

		StringBuffer sb = readFromUrl(url);
		System.out.println(sb);
		//System.out.println(sb.toString().substring(0, 200));
		System.out.println("\n프로그램 종료");
	} // end main()

	/**
	 * 
	 * @param urlAddress : 주어진 url 주소
	 * @return 서버로부터 받은 텍스트 데이터(HTML)리턴
	 */
	public static StringBuffer readFromUrl(String urlAddress) {

		StringBuffer sb = new StringBuffer(); // response 받은 데이터를 담을 객체

		URL url = null; // java.net.URL
		HttpURLConnection conn = null; // java.net.HttpURLConnection

		InputStream in = null; // 바이트 단위
		InputStreamReader reader = null; // byte 스트림 -> 문자기반 reader로
		BufferedReader br = null;

		char[] buf = new char[512]; // 문자용 버퍼

		// BufferedReader <- InputStreamReader <- InputStream <-HttpURLConnection

		try {
			url = new URL(urlAddress);
			conn = (HttpURLConnection) url.openConnection(); // Connection 객체 생성

			if (conn != null) {
				conn.setConnectTimeout(2000); // 2초이내에 연결이 수립이안되면 java.net.SocketTimeoutException 발생
				conn.setRequestMethod("GET"); // GET방식 request ('GET', 'POST')

				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
				conn.setUseCaches(false); // 캐시사용 안함

				System.out.println("requset 시작 : " + urlAddress);
				conn.connect(); // request 발생 -> 이후 response 받을 떄까지 delay
				System.out.println("response 완료");

				// response 받은후 가장먼저 response code 값 확인 ,서버쪽에서 처리가 제대로 됬는지 안됬는지
				int responseCode = conn.getResponseCode(); // 코드값이 200 이 담겨왔을때만 정상적으로 받아온것
				System.out.println("response code : " + responseCode);
				if (responseCode == HttpURLConnection.HTTP_OK) {

					in = conn.getInputStream(); // InputStream <-HttpURLConnection
					reader = new InputStreamReader(in, "UTF-8"); // InputStreamReader <- InputStream
					br = new BufferedReader(reader); // BufferedReader <- InputStreamReader

					int cnt; // 읽은 글자의 개수
					br.read(buf);
					while ((cnt = br.read(buf)) != -1) {
						sb.append(buf, 0, cnt);
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
			if (conn != null)
				conn.disconnect(); // 작업 끝나고 Connection 해제
		}

		// 서버에서 차곡차곡 넣어야하는데 Stirng 보다는 StringBuffer 를 사용하는것이 좋음
		return sb;
	}

} // end class
