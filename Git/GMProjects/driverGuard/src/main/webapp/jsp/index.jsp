<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@include file="common.jsp"%>
<script type="text/javascript">
	function showDetail(time, isNew, machine, timeSecond, inc){
		var p_url = '${contextPath}' + '/data/viewDetail.do';
		$('#info').html('');
		var data = 'time='+time/1000+"&"+'isNew='+isNew+"&"+'machine='+machine+"&"+'timeSecond='+timeSecond+"&"+'inc='+inc;
		doAjaxSubmit(p_url, data, p_callback);
	}
	p_callback = function(response) {
		if (response.result == 0) {
			$('#info').html(response.data);
			$('#error').hide('slow');
			$('#info').show('slow');

		} else {
			$('#error').html("Please correct following errors: " + response.msg);
			$('#info').hide('slow');
			$('#error').show('slow');

		}
	};
	
	$(function(){
		$(".multiselect").multiselect();
		$("#type").change(function(){
			 var selected = $(this).children('option:selected').val();
			 if(selected == "getVehicleDataWithTimer"){
				 $("#interval").show();
			 }else{
				 $("#interval").hide();
			 }
		});
		$("#search").click(function(event){
			event.preventDefault();
			$('#result').html('');
			var paramArray = [];
			$("#params").find("option:selected").each(function(){
				paramArray.push($(this).val());
			});
			
			var p_url = '${contextPath}' + '/data/showData.do';
			var data = 'vinStr='+$("#vin").find("option:selected").val()+'&date='+$("#date").val()+'&type='+$("#type").find("option:selected").val()+'&interval='+$("#intervalSel").find("option:selected").val();
			var loadi = layer.load('Loading…');
			$.ajax({
				type : "POST",
				url : p_url,
		        dataType: "json",
		        data: data,
				success : function(response) {
					if (response.result == 0) {
						layer.close(loadi);
						var html = ['<table class="table table-striped table-bordered bootstrap-datatable datatable">'];
						html.push('<thead>');
						html.push('<tr>');
						html.push('<th>Serial Number</th>');
						html.push('<th>upload Date Time</th>');
						html.push('<th>server Time</th>');
						for(var j=0;j<paramArray.length;j++){
							html.push(' <th>'+paramArray[j]+'</th>');
						}	
						html.push('<th>Actions</th>');
						html.push('</tr>');
						html.push('</thead>');
						html.push(' <tbody>');
						var item;
						for(var i=0;i<response.data.length;i++){
							item = response.data[i];
							html.push(' <tr>');
							html.push(' <td class="center">'+i+'</td>');
							html.push(' <td class="center" >');
							if("uploadTime" in item){
								html.push(' <span class="label label-success">'+formatDate(new Date(item.uploadTime),"%H:%m:%s:%S")+'</span>');
							}else{
								html.push(' <span class="label-success"></span>');
							}
							html.push(' </td>');
							html.push(' <td class="center">');
							if("serverTime" in item){
								html.push(' <span class="label label-success">'+formatDate(new Date(item.serverTime),"%H:%m:%s:%S")+'</span>');
							}else{
								html.push(' <span class="label-success"></span>');
							}
							html.push(' </td>');
							for(var j=0;j<paramArray.length;j++){
								html.push(' <td class="center">');
								if(paramArray[j] in item){
									html.push(' <span class="label label-success">'+eval('item.'+paramArray[j])+'</span>');
								}else{
									html.push(' <span class="label-success"></span>');
								}
								html.push(' </td>');								
							}
							html.push(' <td class="center">');
							html.push(' <a class="btn btn-success" href="#" onclick="showDetail('+item._id.time+','+item._id["new"]+','+item._id.machine+','+item._id.timeSecond+','+item._id.inc+')">');
							html.push(' <i class="icon-zoom-in icon-white"></i>  View');
							//html.push(' <a class="btn btn-danger" href="#"><i class="icon-trash icon-white"></i> Delete</a>');
							html.push(' </td> ');
							html.push(' </tr>');
						}
						html.push(' </tbody>');
						html.push('  </table>  ');
						$('#result').html(
							html.join('')
						);
						//datatable
						$('.datatable').dataTable({
								"bPaginate": true, //翻页功能
								"bLengthChange": true, //改变每页显示数据数量
								"bFilter": false, //过滤功能
								"bSort": false, //排序功能
								"bInfo": true,//页脚信息
								"bAutoWidth": false,//自动宽度
								"sDom": "<'row-fluid'<'span12'l>r>t<'row-fluid'<'span12'i>p>",
								"sPaginationType": "bootstrap",
								"oLanguage": {
								"sLengthMenu": "_MENU_ records per page"
								}
							} );
						
					} else {
						layer.close(loadi);
						layer.msg('Error occurring!', 2, -1);

					}
				},
			});
			
		});
		
	});
</script>
<title>NGI Data</title>
<body>
	<%@include file="header.jsp"%>
		<div id="tip" class="tips" >
			<div id="info" class="success"></div>
			<div id="error" style="display:none"></div>
		</div>
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
						<a href="#">NGI Data</a>
					</li>
				</ul>
			</div>
					<div id="tip" class="tips" >
			<div id="info" class="success"></div>
			<div id="error" style="display:none"></div>
		</div>		
		
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-search"></i>Search</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal" method="post" action="${contextPath}/data/exportExcel.do">
							<fieldset>

							  <div class="control-group">
								<label class="control-label" for="vid">Vin:</label>
								<div class="controls">
								  <select id="vin" name="vinStr">
								  	<c:forEach var="item" items="${vins}" varStatus="status">
										<option value="${item.vin_2_9};${item.vin_10_17}">${item.vin_2_9}${item.vin_10_17}</option>	  	
									</c:forEach>
								  </select>
								</div>
							  </div>
							
							  <div class="control-group">
							  <div style="float:left;margin-left:12px;">Choose params:</div>
								<div id="sss">
    							<select id="params" class="multiselect"  multiple="multiple" name="params">
								  	<c:forEach var="item" items="${params}" varStatus="status">
										<option value="${item}">${item}</option>	  	
									</c:forEach>
      							</select>
								</div>
							</div>
							<div class="control-group" style="float:left">
							  <label class="control-label" for="date">upload date:</label>
							  <div class="controls">
								<input type="text" class="input-xlarge datepicker" id="date" name="date">
							  </div>
							</div>		
				  				
				  			<div class="control-group"  style="float:left">
								<label class="control-label" for="type">The API of get data:</label>
								<div class="controls">
								  <select id="type" name="type">
										<option value="all">All</option>	  	
										<option value="watchVehicleData">watchVehicleData</option>	  	
										<option value="getVehicleDataWithTimer">getVehicleDataWithTimer</option>
								 </select>	  	
								</div>
							  </div>	
				  			<div class="control-group" id="interval" style="display:none">
								<label class="control-label" for="interval">interval(millis):</label>
								<div class="controls">
								  <select id="intervalSel" name="intervalSel">
										<option value=""></option>	  	
										<option value="100">100</option>	  	
										<option value="1000">1000</option>	  	
										<option value="2000">2000</option>	  	
										<option value="5000">5000</option>	  	
								 </select>	  	
								</div>
							  </div>							    
							  <div class="form-actions" style="clear:both">
								<button id="search" class="btn btn-primary">Search</button>
								<input type="submit" id="exportExcel" class="btn btn-primary" value="Export to Excel"/>
							  </div>
							</fieldset>
						  </form>
					</div>					
					
					
				</div><!--/span-->

		</div><!--/row-->
    		
		<div id="result" class="cust">
		</div>
		<!-- content ends -->
		</div><!--/#content.span10-->			
			
		</div><!--/fluid-row-->
		<%@include file="footer.jsp"%>
	</div><!--/.fluid-container-->
</body>
</html>