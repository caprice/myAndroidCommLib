<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/easyui/themes/icon.css">
<link rel="stylesheet" href="${contextPath}/css/default.css"
	type="text/css" media="screen" />
<script type="text/javascript"
	src="${contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
function goToUser(){
	var url = contextPath+"/privilege/userMaintance";
	alert(url);
	$("#mainContent").load(url);
}
</script>	
</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'用户权限管理界面',split:false"
		style="height: 70px;text-align:left">
		<div>username : <sec:authentication property="name"/></div>
		</div>
	<div data-options="region:'south',title:'South Title',split:true"
		style="height: 100px;"></div>
	<div
		data-options="region:'east',iconCls:'icon-reload',title:'East',split:true"
		style="width: 100px;"></div>
	<div data-options="region:'west',title:'菜单',split:true"
		style="width: 200px;">
		<div class="easyui-accordion menu-bar"
			data-options="fit:true,border:false">
			<div class="menu-group" data-options="iconCls:'icon-menu-article'"
				title="用户管理">
				<div class="menu-item">
					<a href="#">添加用户</a>
				</div>
				<div class="menu-item">
					<a href="javascript:void(0)" onclick="goToUser()">新增</a>
				</div>
			</div>
			<div class="menu-group" data-options="iconCls:'icon-menu-article'"
				title="角色管理">
				<div class="menu-item">
					<a href="#">添加新角色</a>
				</div>
				<div class="menu-item">
					<a href="#">删除</a>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div data-options="region:'center',title:'center title'"
		style="padding: 5px; background: #eee;">
		<div id="mainContent">123</div>
		
		</div>
</body>
</html>