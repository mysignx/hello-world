<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	Enumeration<String> sessNames=session.getAttributeNames();
	while(sessNames.hasMoreElements()){
		String name=sessNames.nextElement().toString();
		out.println(name+"="+session.getAttribute(name)+"<br/>");
	}
%>
</body>
</html>