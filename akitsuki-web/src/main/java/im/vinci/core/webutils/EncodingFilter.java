package im.vinci.core.webutils;

import javax.servlet.*;
import java.io.IOException;

/**
 * 字符编码过滤器
 */
public class EncodingFilter implements Filter {
	protected String encoding;
	protected FilterConfig filterConfig;
	protected boolean enabled;

	public EncodingFilter() {
		encoding = null;
		filterConfig = null;
		enabled = true;
	}

	public void destroy() {
		encoding = null;
		filterConfig = null;
	}

	/**
	 * 过滤器主体方法
	 * 
	 * @param
	 * @return void
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		if (enabled || request.getCharacterEncoding() == null) {
			String encoding = selectEncoding(request);
			if (encoding != null)
				request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}

	/**
	 * 过滤器初始化方法
	 * 
	 * @param
	 * @return void
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("enabled");
		if (value == null)
			enabled = true;
		else if (value.equalsIgnoreCase("true"))
			enabled = true;
		else enabled = value.equalsIgnoreCase("yes");
	}

	protected String selectEncoding(ServletRequest request) {
		return encoding;
	}
}