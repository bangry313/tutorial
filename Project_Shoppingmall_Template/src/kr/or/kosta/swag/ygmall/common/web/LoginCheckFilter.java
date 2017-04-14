package kr.or.kosta.swag.ygmall.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 로그인 체크 필터
 */
public class LoginCheckFilter implements Filter {
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String loginId = null;
		Cookie[] cookies = ((HttpServletRequest)request).getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase("loginId")) {
					loginId = cookie.getValue();
				}
			}
		}
		
		if(loginId != null){
			chain.doFilter(request, response);
		}else{
			StringBuilder sb = new StringBuilder("/users/login_form.mall");
//			sb.append("?referer=/users/list.mall");
			String referer = ((HttpServletRequest)request).getRequestURI();
			
			String applicationName = ((HttpServletRequest)request).getContextPath();
			referer = referer.substring(applicationName.length());
			sb.append("?referer="+referer);
			sb.append("&message=로그인 사용자만 가능합니다.");
			request.getRequestDispatcher(sb.toString()).forward(request, response);
		}
	}

	@Override
	public void destroy() {	}
	
}
