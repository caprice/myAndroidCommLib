package com.gm.driverGuard.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.helpers.Loader;


/**
 * <li><li>File Name: ConfigManager.java <li>Title: ConfigManager
 * <li>Description:
 * 可以处理文件在系统运行期间的改动。
 * 
 *  String propValue=ConfigManager.getInstance().getConfigItem(key,null); 
 * 
 */
public class ConfigManager {
	private static ConfigManager m_instance;
	private static String PFILE;

	synchronized public static ConfigManager getInstance() {
		if (m_instance == null) {
			if (PFILE == null) {
				URL url = Loader.getResource("./prop/infobus.properties");
				PFILE = url.getPath();
				PFILE = StringUtils.replace(PFILE, "%20", " ");
			}
			m_instance = new ConfigManager();
		}
		return m_instance;
	}

	private File m_file = null;
	private long m_lastModifiedTime = 0;
	private Properties m_props = null;

	private ConfigManager() {
		m_file = new File(PFILE);
		m_lastModifiedTime = m_file.lastModified();

		if (m_lastModifiedTime == 0) {
			System.err.println(PFILE + " file does not exist!");
		}

		m_props = new Properties();

		try {
			m_props.load(new FileInputStream(PFILE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	final public String getConfigItem(String name, String defaultVal) {
		long newTime = m_file.lastModified();
		if (newTime == 0) {
			if (m_lastModifiedTime == 0) {
				System.err.println(PFILE + " file does not exist!");
			} else {
				System.err.println(PFILE + " file was deleted!!");
			}
			return defaultVal;
		} else if (newTime > m_lastModifiedTime) {
			m_props.clear();
			try {
				m_props.load(new FileInputStream(PFILE));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		m_lastModifiedTime = newTime;

		String val = m_props.getProperty(name);
		if (val == null) {
			return defaultVal;
		} else {
			return val;
		}
	}

}
