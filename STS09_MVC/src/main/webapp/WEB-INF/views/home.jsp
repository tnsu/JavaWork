<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<img alt="img/icon2.png" src="img/icon2.png"><br>
<img alt="img/icon2.png" src="/sts09_mvc/resources/img/icon2.png"><br>
<img alt="img/icon2.png" src="<%=request.getContextPath() %>/resources/img/icon2.png"><br>
<img alt="img/icon2.png" src="${pageContext.request.contextPath }/resources/img/icon2.png"><br>
<br>
<img alt="img/icon2.png" src="${pageContext.request.contextPath }/myRes/img/icon2.png"><br>

</body>
</html>
