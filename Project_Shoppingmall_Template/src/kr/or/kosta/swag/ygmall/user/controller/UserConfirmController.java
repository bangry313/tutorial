package kr.or.kosta.swag.ygmall.user.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.swag.ygmall.common.web.Controller;
import kr.or.kosta.swag.ygmall.common.web.ModelAndView;
import kr.or.kosta.swag.ygmall.user.service.UserService;
import kr.or.kosta.swag.ygmall.user.service.UserServiceImpl;

/**
 * /users/confirm(로그인/로그아웃) 요청에 대한 컨트롤러
 * 
 * @author 김기정
 */
public class UserConfirmController implements Controller {

	private UserService service = new UserServiceImpl();

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ModelAndView mav = new ModelAndView();
		
		String method = request.getMethod();
		String view = null;
		
		String referer = request.getParameter("referer");
		if (referer == null) referer = "/";

		// 로그인 처리
		if (method.equalsIgnoreCase("post")) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");

			if (service.isMember(id, passwd)) {
				Cookie loginCookie = new Cookie("loginId", id);
				loginCookie.setPath("/");
				response.addCookie(loginCookie);
				view = "redirect:" + request.getServletContext().getContextPath() + referer;
			} else {
				view = "login_form.mall";
			}

		} else {// 로그아웃 처리
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equalsIgnoreCase("loginId")) {
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
						view = "redirect:" + request.getServletContext().getContextPath() + referer;
					}
				}
			}
		}
		mav.setView(view);

		return mav;
	}

}
