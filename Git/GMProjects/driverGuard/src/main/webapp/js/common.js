function doAjaxSubmit(p_url, p_data, p_callback) {
	$.ajax({
		type : "POST",
		url : p_url,
		data : p_data,
        dataType: "json",
		beforeSend : function(XMLHttpRequest) {
			// ShowLoading();
		},
		success : function(data) {
			p_callback(data);
		},
		complete : function(XMLHttpRequest, textStatus) {
			// HideLoading();
		},
		error : function() {
			// 请求出错处理
		}
	});
}


function doAjaxSubmitJson(p_url, p_data, p_callback) {
	$.ajax({
		type : "POST",
		url : p_url,
		data : p_data,
        dataType: "json",
        contentType:"application/json",  
		beforeSend : function(XMLHttpRequest) {
			// ShowLoading();
		},
		success : function(data) {
			p_callback(data);
		},
		complete : function(XMLHttpRequest, textStatus) {
			// HideLoading();
		},
		error : function() {
			// 请求出错处理
		}
	});
}

/*******************************************************************************
 * @serializedParams looks like "prop1=value1&prop2=value2". Nested property
 *                   like 'prop.subprop=value' is also supported
 ******************************************************************************/
function paramString2obj(serializedParams) {

	var obj = {};
	function evalThem(str) {
		var attributeName = str.split("=")[0];
		var attributeValue = str.split("=")[1];
		if (!attributeValue) {
			return;
		}

		var array = attributeName.split(".");
		for (var i = 1; i < array.length; i++) {
			var tmpArray = Array();
			tmpArray.push("obj");
			for (var j = 0; j < i; j++) {
				tmpArray.push(array[j]);
			}
			var evalString = tmpArray.join(".");
			if (!eval(evalString)) {
				eval(evalString + "={};");
			}
		}
		;
		eval("obj." + attributeName + "='" + attributeValue + "';");

	}
	;
	var properties = serializedParams.split("&");
	for (var i = 0; i < properties.length; i++) {
		evalThem(properties[i]);
	}
	;
	return obj;
}

$.fn.form2json = function() {
	var serializedParams = this.serialize();
	var obj = paramString2obj(serializedParams);
	return JSON.stringify(obj);
};

$.fn.serializeObject = function()
{
   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
       } else {
           o[this.name] = this.value || '';
       }
   });
   return o;
};
