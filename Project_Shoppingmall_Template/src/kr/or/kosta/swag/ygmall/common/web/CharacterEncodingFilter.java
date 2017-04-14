package kr.or.kosta.swag.ygmall.common.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 요청파라메터 한글인코딩 처리 필터
 */
public class CharacterEncodingFilter implements Filter {
	
	private String encoding;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 전처리
		// 서블릿 실행전 공통기능(인증, 로깅,이미지 변환, 데이터 압축, 암호화, XML 변환 등) 처리
		if(encoding != null){
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
		// 후처리
	}

	@Override
	public void destroy() {	}

	

}
