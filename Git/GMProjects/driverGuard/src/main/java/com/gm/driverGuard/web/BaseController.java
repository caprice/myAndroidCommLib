package com.gm.driverGuard.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.gm.driverGuard.json.JsonResponse;
import com.gm.driverGuard.util.ConstantUtils;

/**
 * @Description:
 * @author liuwei
 * @date 2013年11月12日 下午3:28:34
 * 
 */
public abstract class BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected ServletContext servletContext;

	@Autowired
	protected HttpSession session;

	@Autowired
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public JsonResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException error) {
		JsonResponse response = new JsonResponse();
		response.setResult(ConstantUtils.JSON.RESULT_VALIDATION_FAILED);
		response.setData(error.getBindingResult().getAllErrors());
		response.setMsg("Validation failed!");
		return response;
	}

	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public JsonResponse handleException(DataAccessException ex) {
		JsonResponse response = new JsonResponse();
		response.setResult(ConstantUtils.JSON.RESULT_DB_ERROR);
		response.setData(ex.getMessage());
		response.setMsg("DB access Error!");
		return response;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public JsonResponse handleMaxUploadSizeExceededException(Exception ex) {
		JsonResponse response = new JsonResponse();
		response.setResult(ConstantUtils.JSON.RESULT_FAILED);
		response.setData(ex.getMessage());
		if (ex instanceof MaxUploadSizeExceededException) {
			response.setMsg("Maximum upload size of " + ((MaxUploadSizeExceededException) ex).getMaxUploadSize() + "bytes exceeded");
		} else {
			response.setMsg(ex.getMessage());
		}

		return response;
	}
}
