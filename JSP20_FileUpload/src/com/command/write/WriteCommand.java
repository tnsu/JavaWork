package com.command.write;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.WriteDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class WriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int cnt = 0;
		WriteDAO dao = new WriteDAO();
		
		//-----------
		// 업로드된 파일(들)
		ServletContext context = request.getServletContext();
		String saveDirectory = context.getRealPath("upload");
		
		int maxPostSize = 5 * 1024* 1024;
		String encoding ="utf-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(
					request,
					saveDirectory,
					maxPostSize,
					encoding,
					policy
					);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		List<String> originalFileNames = new ArrayList<String>();
		List<String> fileSystemNames = new ArrayList<String>();
		
		Enumeration names = multi.getFileNames(); // type="file" 요소의  name들 추출
		while(names.hasMoreElements()) {
			String name = (String)names.nextElement();
			String originalFileName = multi.getOriginalFileName(name);
			String filesystemName = multi.getFilesystemName(name);
			System.out.println("첨부파일 : " + originalFileName + "-> " + filesystemName);
			
			if(originalFileName != null || filesystemName != null) {
				originalFileNames.add(originalFileName);
				fileSystemNames.add(filesystemName);
			}
		}// end while
		
		// 매개변수 받아오기
		String name = multi.getParameter("name"); // MultipartRequest 객체 사용!!
		String subject = multi.getParameter("subject");
		String content = multi.getParameter("content");
		
		if(name != null && subject != null &&
				name.trim().length() > 0 && subject.trim().length() > 0) {
			
			try {
				// 첨부 파일 정보 같이 INSERT
				cnt = dao.insert(subject, content, name, originalFileNames, fileSystemNames);
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
		} // end if
			
		request.setAttribute("result", cnt);

	} // end execute()

} // end Command
















