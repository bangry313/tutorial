package kr.or.kosta.swag.ygmall.user.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.swag.ygmall.common.domain.Pagination;
import kr.or.kosta.swag.ygmall.common.domain.Params;
import kr.or.kosta.swag.ygmall.common.web.Controller;
import kr.or.kosta.swag.ygmall.common.web.ModelAndView;
import kr.or.kosta.swag.ygmall.user.domain.User;
import kr.or.kosta.swag.ygmall.user.service.UserService;
import kr.or.kosta.swag.ygmall.user.service.UserServiceImpl;

/**
 * /users/list 요청에 대한 컨트롤러
 * @author 김기정
 *
 */
public class UserListController implements Controller {
	
	private UserService service = new UserServiceImpl();
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ModelAndView mav = new ModelAndView();
		
		Params parameters = new Params();
		String page = request.getParameter("page");
		String type = request.getParameter("type");
		String value = request.getParameter("value");
		String pageSize = request.getParameter("pageSize");
		String pageNum = request.getParameter("pageNum");
		
		if(page != null) parameters.setPage(Integer.parseInt(page));
		if(type != null) parameters.setType(type);
		if(value != null) parameters.setValue(value);
		if(pageSize != null) parameters.setPageSize(Integer.parseInt(pageSize));
		if(pageNum != null) parameters.setPageNum(Integer.parseInt(pageNum));
		
		
		// 요청파라메터에 대한 회원목록 반환
		List<User> list = service.listByPage(parameters);
		
		// 페이징 계산을 위한 회원수 반환
	    int rowCount = service.countByPage(parameters);
	    Pagination pagination = new Pagination();
	    pagination.setRequestPage(parameters.getPage());
		pagination.setDisplayPageSize(parameters.getPageSize());
		pagination.setDisplayPageNum(parameters.getPageNum());
		pagination.setTotalRowCount(rowCount);
	  
	    // 검색요청인 경우
	    if(parameters.getType() != null){
	      pagination.setType(parameters.getType());
	      pagination.setValue(parameters.getValue());
	    }
	    // 페이징 계산
	    pagination.operatePageCount();
	    
	    mav.addObject("list", list);
	    mav.addObject("pagination", pagination);
	    mav.addObject("paging", pagination.toHtml());
	    
	    mav.setView("/users/list.jsp");
		
		return mav;
	}

}
