<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common.jsp"%>
<html>
<head>
<title>aaa</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="wrap">
		<%@ include file="right_nav.jsp"%>
		<!-- the middle area -->
		<div class="right">
			<h1 class="tit_1">
				<span class="fr"><a href="/my/code/add">添加代码</a></span>代码<span
					class="num_tit"></span>
			</h1>
			<div class="code_classfiy">
				<form>
					<ul>
						<li></li>
					</ul>
				</form>
			</div>
			<div class="notice tc">您可以将简洁、高效、有用、喜欢的代码，记录下来，以后备用。</div>
		</div>
		<div class="page_nav"></div>

	</div>
	<script type="text/javascript">
		function code_delete(id) {
			if (window.confirm('确定要删除吗?')) {
				$.post("/index.php/my/code/do_delete/", {
					"id" : id
				}, function(data) {
					window.location.reload();
				});
			}
		}
	</script>
	<!-- the right area -->

	<!-- public footer -->
	<%@ include file="footer.jsp"%>
</body>
</html>