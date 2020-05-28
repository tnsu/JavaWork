<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%-- cos 라이브러리 --%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.FileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>

<%-- parameter 값들, file 값들 추출 --%>
<%@ page import="java.util.Enumeration" %>
    
<%--File 객체 --%>
<%@ page import="java.io.File" %>

<%-- 이미지 파일 다루기 --%>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.imageio.ImageIO" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>파일 업로드 결과</title>
</head>
<body>
<form action="FileCheck.jsp" method="post" name="fileCheck">
<%
	// 	MultipartRequest 객체 생성
	//String saveDirectory = "C:\\tomcat\\upload"; // 업로드 파일의 저장 경로
	
		// 파일 저장경로
	// 서버에서(서블릿) 어디에 어느폴더에서 서블릿으로 변환되는지 알아내기
	ServletContext context = this.getServletContext();
	// 서블릿앙의 upload 폴더의 물리적인 경로 얻어오기
	String saveDirectory = context.getRealPath("upload");


	System.out.println("업로드 경로: " + saveDirectory);
	
	// post 받기 최대 크기 5M byte
	int maxPostSize =  5 * 1024 *1024; 
	String encoding = "utf-8"; // 인코딩
	FileRenamePolicy policy = new DefaultFileRenamePolicy(); // 업로딩 파일 중복에 대한 rename 정책
	MultipartRequest multi = null; // com.oreilly.servlet.MultipartRequest 임포트

	try{ // 실제로 예외를 반드시 catch 할 필요는 없지만 처리
		// jsp 파일에서 예외를 반드시 catch 할 필요는 없지만..
		//500 처리가 싫다면 해즈는 것이 ..
		
		// MultipartRequest 생성단계에서 이미 파일은 저장됨
		multi = new MultipartRequest(
				request,  // JSP 내부 객체 requset
				saveDirectory, // 업로드된 파일 저장경로
				maxPostSize, // 최대파일  크기(post 크기)
				encoding,
				policy  //  중복이름의 파일 rename 객체
				);
	
		Enumeration names = null;
		
		// 1. Paramerter name 추출
		names = multi.getParameterNames(); // 일반 form 요소 name들 추출
		while(names.hasMoreElements()){
			String name = (String)names.nextElement();  // name
			String value = multi.getParameter(name); // value
			out.println(name + " : " + value + "<br>"); // 출력
		}
		out.println("<hr>");
		
		//2. Flie 들 추출
		names = multi.getFileNames();   // type="file" 요소 name들 추출
		while(names.hasMoreElements()){
			// <input type="file"> 의 name 가져오기
			String name = (String)names.nextElement();  
			out.println("input name: " + name + "<br>");
			
			// 위 name 에는 폼요소의 name 이 담겨있다.
			// 그 name 을 가지고 원래 파일 (업로드 할 파일) 을 가져온다
			String originalFileName = multi.getOriginalFileName(name);
			out.println("원본파일 이름: " + originalFileName + "<br>");
			out.println("<input type='hidden' name='originalFileName' value='" + originalFileName + "'/>");
			// 만약 업로드할 폴더에 동일 이름의 파일이 있으면 현재 올리는 파일 이름은 바뀐다 
			// (FileRenamePolicy 중복정책)
			// 그리고 나서 시스템 에 실제 업로딩 된 이름을 알려준다
			String fileSystemName = multi.getFilesystemName(name);
			out.println("파일시스템 이름: " + fileSystemName + "<br>");
			out.println("<input type='hidden' name='fileSystemName' value='" + fileSystemName + "'/>");
			
			// 업로딩된 파일의 타입 : MIME 타입 ( ex: image/png ...)
			String fileType = multi.getContentType(name);
			out.println("파일타입: " + fileType + "<br>");
			
			// 문자열 '파일이름' 이 name 에 들어온 상태
						// 문자열 파일이름을 통해 실제 파일 정보를 -> File객제로 가져오기
						File file = multi.getFile(name);
						if(file != null){
							long fileSize = file.length();  // 파일 크기 (byte)
							out.println("파일 크기: " + fileSize + " bytes<br>");
					
						
						// 이미지 파일 다루기
						BufferedImage bi = ImageIO.read(file);
						if(bi != null){  //  ★이미지 파일 판정 여부★
							int width = bi.getWidth();
							int height = bi.getHeight();
							out.println("이미지파일 WxH: " + width + " x " + height + "<br>");
						}else {
								out.println("이미지가 아닙니다<br>");
							}
						}
						out.println("<hr>");
		} // end while
		
	} catch(Exception e){
		e.printStackTrace();
		out.println("파일 처리 예외 발생<br>");	
	}


%>
<input type="submit"value="업로드 파일 확인"/> <br>
</form>





</body>
</html>