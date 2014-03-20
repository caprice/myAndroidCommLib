package com.gm.driverGuard.exception;

/**
 * @Description:
 * @author liuwei
 * @date 2014年3月20日 下午4:11:32
 * 
 */
public class DataAccessException extends RuntimeException {
	private static final long serialVersionUID = -3689499200738474400L;

	public DataAccessException(Exception e) {
		super(e);
	}

	public DataAccessException(String error, Exception cause) {
		super(error, cause);
	}

	public DataAccessException(String error) {
		super(error);
	}
}
