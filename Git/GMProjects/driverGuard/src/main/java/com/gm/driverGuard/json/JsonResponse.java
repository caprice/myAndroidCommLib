package com.gm.driverGuard.json;

/**
 * @Description: json object that is sent to client.
 * @author liuwei
 * @date 2013年11月13日 上午9:08:18
 * 
 */
public class JsonResponse {
	private String result;
	private Object data;
	private String msg;

	public final Object getData() {
		return data;
	}

	public final void setData(Object data) {
		this.data = data;
	}

	public final String getMsg() {
		return msg;
	}

	public final void setMsg(String msg) {
		this.msg = msg;
	}

	public final String getResult() {
		return result;
	}

	public final void setResult(String result) {
		this.result = result;
	}
}
