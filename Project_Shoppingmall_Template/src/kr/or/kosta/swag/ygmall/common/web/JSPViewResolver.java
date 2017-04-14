package kr.or.kosta.swag.ygmall.common.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * View 선택 및 실행
 * @author 김기정
 *
 */
public class JSPViewResolver implements ViewResolver{
	
	public void execute(HttpServletRequest request, HttpServletResponse response, String uri) throws ServletException, IOException{
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher(uri);
		dispatcher.forward(request, response);
	}
}
