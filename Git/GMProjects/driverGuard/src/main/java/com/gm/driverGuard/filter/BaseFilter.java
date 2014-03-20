package com.gm.driverGuard.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gm.driverGuard.json.JsonResponse;

/**
 * @Description: base filter that is used to be extended by its children class
 * @author liuwei
 * @date 2013年11月14日 上午9:27:03
 * 
 */
@Component
public abstract class BaseFilter implements Filter {
	public final Logger logger = Logger.getLogger(this.getClass());
	public static final String EXCLUDED_URLS = "excludedUrls";
	public static final String INCLUDED_URLS = "includedUrls";
	private List<String> includedUrlList = new ArrayList<String>();
	private List<String> excludedUrlList = new ArrayList<String>();
	private FilterConfig filterConfig = null;
	private boolean initialized = false;
	private String context;

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		if (!(req instanceof HttpServletRequest) || !(res instanceof HttpServletResponse)) {
			throw new ServletException("It just supports HTTP requests");
		}
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (this.isApplicable(request)) {
			this.processRequest(request, response);
		}
		chain.doFilter(req, res);

	}

	/**
	 * @Title: processRequest
	 * @Description: core logic should be wroten in this place
	 * @return:void
	 * @author: liuwei
	 * @date: 2013年12月6日 Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2013年12月6日 liuwei v1.0.0 修改原因
	 */
	public abstract void processRequest(HttpServletRequest request, HttpServletResponse response);

	/**
	 * @Title: isApplicable
	 * @Description: Indicates whether current url is going to the logic of the
	 *               filter
	 * @return:boolean
	 * @author: liuwei
	 * @date: 2013年12月6日 Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2013年12月6日 liuwei v1.0.0 修改原因
	 */
	protected boolean isApplicable(final HttpServletRequest request) {
		String currentUrl = request.getRequestURI();
		if (this.isInExcludedUrlList(currentUrl)) {
			return false;
		}
		return true;
	}

	private boolean isInExcludedUrlList(String currentUrl) {
		for (String url : this.excludedUrlList) {
			if (currentUrl.equals(url)) {
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig config) throws ServletException {
		context = config.getServletContext().getContextPath();
		this.filterConfig = config;
		this.processConfig();
	}

	/**
	 * @Title: processConfig
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return:void
	 * @author: liuwei
	 * @date: 2013年11月14日 Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2013年11月14日 liuwei v1.0.0 修改原因
	 */
	protected void processConfig() {
		if (this.filterConfig == null) {
			this.initialized = false;
			return;
		}
		// now gather list of applicable(included) urls
		this.includedUrlList = this.initListParams(BaseFilter.INCLUDED_URLS);
		// now gather list of excluded urls
		this.excludedUrlList = this.initListParams(BaseFilter.EXCLUDED_URLS);

		this.logger.debug("initialized");
		this.initialized = true;
	}

	/**
	 * read params from web.xml
	 * 
	 * @param parameterName
	 * 
	 */
	private List<String> initListParams(final String parameterName) {
		final List<String> paramValueList = new ArrayList<String>();
		final String parameterValue = this.filterConfig.getInitParameter(parameterName);
		if (parameterValue == null) {
			this.initialized = false;
			return paramValueList;
		}
		final String[] parameterValueArray = parameterValue.split(";");
		for (int i = 0; i < parameterValueArray.length; i++) {
			paramValueList.add(context + StringUtils.trim(parameterValueArray[i]));
		}
		return paramValueList;
	}

	/**
	 * @Title: writeJsonObject
	 * @Description: output json to browser
	 * @return:void
	 * @author: liuwei
	 * @date: 2013年12月6日 Modification History: Date Author Version Description
	 *        ---------------------------------------------------------*
	 *        2013年12月6日 liuwei v1.0.0 修改原因
	 */
	public void writeJsonObject(HttpServletResponse res, JsonResponse jsonObj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(res.getOutputStream(), jsonObj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
