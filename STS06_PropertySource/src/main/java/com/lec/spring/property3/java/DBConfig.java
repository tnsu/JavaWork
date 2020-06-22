package com.lec.spring.property3.java;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.lec.spring.property2.xml.DBConn;

@Configuration
public class DBConfig {
// 값을 받아올수 있는 변수가 필요하다 getter/setter 필요없음
	
	@Value("${admin.id}")
	private String id;
	@Value("${admin.pw}")
	private String pw;
	@Value("${db.url}")
	private String url;
	@Value("${db.port}")
	private String port;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer Properties() {
		System.out.println("Properties() 호출");
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		// 파일을 읽어는 들어왔다 값을 받기 위해서는 value 사용해줘야함
		Resource[] locations = new Resource[2];
		locations[0] = new ClassPathResource("admin.auth");
		locations[1] = new ClassPathResource("db.info");
		configurer.setLocations(locations);
		return configurer;
		
	}
	
	@Bean
	public DBConn dbConfig() {
		DBConn conn = new DBConn();
		
		conn.setId(id);
		conn.setPw(pw);
		conn.setPort(port);
		conn.setUrl(url);
		
		return conn;
	}
	
	
}
