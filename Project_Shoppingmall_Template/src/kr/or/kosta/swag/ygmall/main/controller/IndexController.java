package kr.or.kosta.swag.ygmall.main.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.swag.ygmall.common.web.Controller;
import kr.or.kosta.swag.ygmall.common.web.ModelAndView;

/**
 * /index 요청에 대한 세부 컨트롤러
 * @author 김기정
 */
public class IndexController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView();
		// String message = xxxService.bizMethod();
		// 서비스(비즈니스)객체로 부터 메인페이지에서 출력하고자 하는 동적정보 취득
		// ModelAndView에 저장
		// mav.addObject(key, value);
		mav.addObject("mainTitle", "Model2 기반 팀별 쇼핑몰 구축을 위한 템플릿");

		mav.setView("index.jsp");
		return mav;
	}

}
