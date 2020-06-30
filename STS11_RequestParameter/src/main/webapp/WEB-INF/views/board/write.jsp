<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글작성</title>
</head>
<script>
//form 검증(validation)용 함수.  onsubmit 에서 호출
//검증을 통과하면 true 를 리턴,  아니면 false 리턴 

function chkSubmit(){
        frm = document.forms["frm"];

        var name = frm["name"].value.trim();
        var subject = frm["subject"].value.trim();

        if(name == ""){
            alert("작성자 란은 반드시 입력해야 합니다.");
            frm["name"].focus();
            return false;
        }
        if(subject == ""){
            alert("제목은 반드시 작성해야 합니다.");
            frm["subject"].focus();
            return false;
        }
        return true;
    }

</script>
<body>
<h2>글작성</h2>
<%-- 글 내용이 많을수 있기 때문에 POST 방식 사용 --%>
<form name="frm" action="writeOk" method="post" onsubmit="return chkSubmit()">

<%-- int 매개변수 받는 setter 도 동직할까?  정수로파싱되어 나옴 --%><!-- 0629 -->
uid : <input type="text" name="uid" value="123"/><br>

작성자:
<input type="text" name="name" value="코쟁이"/><br>
제목:
<input type="text" name="subject" value="코딩 쟁이 입니당"/><br>
내용:<br>
<textarea name="content">안뇽</textarea>
<br><br>
<input type="submit" value="등록"/>
</form>

</body>
</html>