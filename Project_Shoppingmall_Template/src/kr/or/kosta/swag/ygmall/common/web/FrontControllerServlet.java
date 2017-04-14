package kr.or.kosta.swag.ygmall.common.web;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 모든 웹 클라이언트 요청에 대한 단일 진입점 역할의 프론트 컨트롤러 서블릿(메인 컨트롤러)
 * @author 김기정
 */
public class FrontControllerServlet extends HttpServlet {

	private XMLControllerFactory controllerFactory;

	public void init() throws ServletException {
		String controllerMappingPath = getInitParameter("controllerMappingPath");
		try {
			controllerFactory = new XMLControllerFactory(controllerMappingPath);
		} catch (Exception e) {
			throw new ServletException("XMLControllerFactory Create Fail!", e);
		}
	}
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		process(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/** 모든 세부 컨트롤러들에 대한 공통 기능 처리 */
		//모든 요청파라메터에 대한 한글 인코딩 처리
		//request.setCharacterEncoding("utf-8");

		// 웹 클라이언트 요청(브라우저 명령) 분석
		String uri = request.getRequestURI();

		// 확장자 형식 매핑시..
		// /appName/hello.do -> /hello
		// /appName/board/list.do -> /board/list
		String applicationName = request.getContextPath();
		uri = uri.substring(applicationName.length(), uri.lastIndexOf("."));
		System.out.println("[Debug] : 요청 URI: " + uri);

		Controller controller = null;
		ModelAndView mav = null;

		// 컨트롤러 팩토리로부터 필요한 세부 컨트롤러 검색
		controller = controllerFactory.getController(uri);
		
		ViewResolver viewResolver = new JSPViewResolver();
		// ViewResolver viewResolver = new XXXViewResolver();
		
		// 세부 컨트롤러가 등록되어 있지 않은 경우..
		if (controller == null) {
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return;
			// 단순 포워드
			viewResolver.execute(request, response, uri+".jsp");
			return;
		}

		mav = controller.handleRequest(request, response);
		if(mav == null){
			return;
		}
		
		Map<String, Object> map = mav.getMap();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			Object value = map.get(key);
			// 뷰(JSP)가 결과정보를 사용할 수 있도록 request 속성에 저장
			request.setAttribute(key, value);
		}
		
		String viewPath = mav.getView();
		if (viewPath == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		if(viewPath.startsWith("redirect")){// redirect
			String[] tokens = viewPath.split(":", 2);
			response.sendRedirect(tokens[1]);
		}else{//forward
			viewResolver.execute(request, response, viewPath);
		}
	}
}
