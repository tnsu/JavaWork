package com.lec.java.crawl10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class main {
	public static StringBuffer readFromUrl(String urlAddress) { // 이거고치기 강사님

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

				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
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

		return sb;
	}

}
