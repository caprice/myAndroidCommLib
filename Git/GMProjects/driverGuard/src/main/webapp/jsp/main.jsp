<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<form>
${msg}<br>
坐标X:${coordinate.x}<br>
坐标Y:${coordinate.y}
<a href="<c:url value='/main/searchPage?x=${coordinate.x}&y=${coordinate.y}'/>">查询页面</a>
<select name="id">
<option value="${equipNames.id}">${equipNames.name}</option> 
<c:forEach items="${equipNames}" var="equipNames"> 
<option value="${equipNames.id}">${equipNames.name}</option> 
</c:forEach> 
</select>
</form>
</body>
</html>