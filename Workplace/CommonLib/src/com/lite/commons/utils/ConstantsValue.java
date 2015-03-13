package com.lite.commons.utils;

import android.os.Environment;

public class ConstantsValue {

	public static final String COMMON_ACTION = "lite";
	private static final String IMAGE_PATH_DIR = "/.lite/img/";
    private static final String IMAGE_APP_PATH_DIR = "/.lite/app_img/";
    private static final String APP_LOG_PATH_DIR = "/.lite/log/";
    public static final String IMAGE_APP_PATH = Environment.getExternalStorageDirectory() + IMAGE_APP_PATH_DIR;
	
    public static final boolean DEBUG = true;

}
