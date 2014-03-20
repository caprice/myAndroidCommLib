package com.gm.driverGuard.util;

public interface ConstantUtils {
	public final static String SALT_KEY = "GM";
	public static final String TOKEN_KEY = "DiDi7538";
	public interface Request {
	}

	public interface JSON {
		String KEY_RESULT = "result";
		String KEY_MESSAGE = "message";
		String KEY_DATA = "data";

		String RESULT_OK = "0";
		String RESULT_FAILED = "-1";
		String RESULT_VALIDATION_FAILED = "-2";
		String RESULT_DB_ERROR = "-3";
	}
	
	public interface JSP {
		String PAGE_ERROR = "error";
		String PAGE_NOT_FOUND = "404";
	}
}
