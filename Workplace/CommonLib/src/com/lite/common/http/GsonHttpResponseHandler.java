package com.lite.common.http;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.HttpStatus;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class GsonHttpResponseHandler<T> extends AsyncHttpResponseHandler {
	private static final String LOG_TAG = "GsonHttpResponseHandler";
	private Gson gson;
	private Class<T> clazz;

	public GsonHttpResponseHandler(Class<T> clazz) {
		super();
		this.clazz = clazz;
		setCharset(DEFAULT_CHARSET);
		this.gson = new Gson();
	}

	public void onSuccess(int statusCode, Header[] headers, T obj) {

	}

	public void onFailure(int statusCode, Header[] headers, String errorResponse) {

	}

	@Override
	public final void onSuccess(final int statusCode, final Header[] headers, final byte[] responseBytes) {
		if (statusCode != HttpStatus.SC_NO_CONTENT) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					final String json = getResponseString(responseBytes, getCharset());
					postRunnable(new Runnable() {
						@Override
						public void run() {
							try {
								onSuccess(statusCode, headers, gson.fromJson(json, clazz));
							} catch (JsonSyntaxException e) {
								onFailure(statusCode, headers, gson.toJson(e));
							}
						}
					});
				}
			}).start();
		} else {
			try {
				onSuccess(statusCode, headers, clazz.newInstance());
			} catch (InstantiationException e) {
				onFailure(statusCode, headers, gson.toJson(e));
			} catch (IllegalAccessException e) {
				onFailure(statusCode, headers, gson.toJson(e));
			}
		}
	}

	@Override
	public final void onFailure(final int statusCode, final Header[] headers, final byte[] responseBytes, final Throwable throwable) {
		if (responseBytes != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					final String json = getResponseString(responseBytes, getCharset());
					postRunnable(new Runnable() {
						@Override
						public void run() {
							try {
								onFailure(statusCode, headers, json);
							} catch (JsonSyntaxException e) {
								onFailure(statusCode, headers, gson.toJson(e));
							}
						}
					});

				}
			}).start();
		} else {
			Log.v(LOG_TAG, "response body is null, calling onFailure(Throwable, JSONObject)");
			onFailure(statusCode, headers, null);
		}
	}

	/**
	 * Attempts to encode response bytes as string of set encoding
	 * 
	 * @param charset
	 *            charset to create string with
	 * @param stringBytes
	 *            response bytes
	 * @return String of set encoding or null
	 */
	public static String getResponseString(byte[] stringBytes, String charset) {
		try {
			return stringBytes == null ? null : new String(stringBytes, charset);
		} catch (UnsupportedEncodingException e) {
			Log.e(LOG_TAG, "Encoding response into string failed", e);
			return null;
		}
	}

}
