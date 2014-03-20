<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common.jsp"%>
<script type="text/javascript" src="<c:url value='/js/searchPage.js'/>"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
绝对路径：${contextPath}<br>
坐标X:${x}<br>
坐标Y:${y}
墓大区：
<select id="id" name="id">
<option value="-99">"请选择"</option> 
<c:forEach items="${zoneAreaList}" var="zoneAreaVar"> 
<option value="${zoneAreaVar.id}">${zoneAreaVar.name}</option> 
</c:forEach> 
</select>
<br/>
分区：
<select id="zoneId" name="zoneId">
<option value="-99">"请选择"</option> 
</select>
</body>
</html>