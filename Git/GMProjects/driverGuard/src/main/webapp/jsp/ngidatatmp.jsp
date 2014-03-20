<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="common.jsp"%>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript">
$(function(){
	$('#save').click(function(event){
		event.preventDefault();
		var p_url = '${contextPath}' + '/data/saveNgiDataTmp.do';
		$('#info').html('');
		var data = $('#template').val();
		doAjaxSubmitJson(p_url, data, function(response){
			if (response.result == 0) {
				$('#info').html("Save successfully!");
				$('#error').hide('slow');
				$('#info').show('slow');
			} else {
				$('#info').html("Save Error!");
				$('#error').hide('slow');
				$('#info').show('slow');
			}
		});
	});
})
</script>
<title>NGI Data Template</title>
<body>
	<%@include file="header.jsp"%>
		<div class="container-fluid">
		<div class="row-fluid">
			<!-- left menu starts -->
			<%@include file="leftmenu.jsp"%>
			<!-- left menu ends -->
			<div id="content" class="span10">
			<!-- content starts -->

			<div>
				<ul class="breadcrumb">
					<li>
						<a href="#">Home</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="#">NGI Data Template Setting</a>
					</li>
				</ul>
			</div>
					<div id="tip" class="tips" >
			<div id="info" class="success"></div>
			<div id="error" style="display:none"></div>
		</div>		
				<div id="tip" class="tips" >
			<div id="info" class="success"></div>
			<div id="error" style="display:none"></div>
		</div>
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-search"></i>NGI Data Template Setting</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal">
							<fieldset>
							<div class="control-group">
							  <div class="controls" style="margin-left:20px">
								<textarea  id="template"  cols="90" rows="20" style="width:800px;height:400px"></textarea>
							  </div>
							</div>
							<div class="form-actions">
							  <button  class="btn btn-primary" id="save">Save changes</button>
							  <button type="reset" class="btn">Cancel</button>
							</div>							  
							</fieldset>
						  </form>
					</div>					
					
					
				</div><!--/span-->

		</div><!--/row-->
		<!-- content ends -->
		</div><!--/#content.span10-->			
		</div><!--/fluid-row-->
		<%@include file="footer.jsp"%>
	</div><!--/.fluid-container-->
</div>
</body>
</html>