<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

	final String SQL_W_INC_VIEWCNT =
					"UPDATE pract_board SET pr_viewcnt = pr_viewcnt +1 WHERE pr_uid = ?";
	final String SQL_W_SELECT_BY_UID = // 글 읽어오기
					"SELECT * FROM pract_board WHERE pr_uid = ?";