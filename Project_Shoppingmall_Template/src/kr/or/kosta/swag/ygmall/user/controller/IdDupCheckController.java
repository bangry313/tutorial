package kr.or.kosta.swag.ygmall.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.swag.ygmall.common.web.Controller;
import kr.or.kosta.swag.ygmall.common.web.ModelAndView;
import kr.or.kosta.swag.ygmall.user.service.UserService;
import kr.or.kosta.swag.ygmall.user.service.UserServiceImpl;

/**
 * /users/idDupCheck 요청에 대한 컨트롤러
 * @author 김기정
 *
 */
public class IdDupCheckController implements Controller {
	
	private UserService service = new UserServiceImpl();
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		String id = request.getParameter("id");
		boolean isDup = service.isDupId(id);
		
		try {
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(isDup);
		} catch (IOException e) {
			throw new ServletException(e);
		}
		
		return null;
	}

}
