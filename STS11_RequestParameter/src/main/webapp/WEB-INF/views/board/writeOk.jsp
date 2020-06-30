<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
기존방식으로 구현
작성자 : ${dto.name}<br>
제목 : ${dto.subject}<br>
내용 : ${dto.content}<br> 

--%>

<%-- 
<!-- WriteDTO  이나 앞글자는 소문자로 해주어야 들어간다(Spring 에서 그렇게 만들어 놓았다.)-->
작성자 : ${writeDTO.name}<br>
제목 : ${writeDTO.subject}<br>
내용 : ${writeDTO.content}<br> 
<!--  내용이 안나왔던 이유는 대문자 이기 때문이다  WriteDTO-->

<!-- 0629 -->
uid : ${writeDTO.uid}<br>
조회수 : ${writeDTO.viewCnt}<br>
등록일 : ${writeDTO.regDate}<br>
--%>

<!-- 0629 -->
작성자 : ${DTO.name}<br>
제목 : ${DTO.subject}<br>
내용 : ${DTO.content}<br> 
uid : ${DTO.uid}<br>
조회수 : ${DTO.viewCnt}<br>
등록일 : ${DTO.regDate}<br>

