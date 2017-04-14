package kr.or.kosta.swag.ygmall.user.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.swag.ygmall.common.web.Controller;
import kr.or.kosta.swag.ygmall.common.web.ModelAndView;
import kr.or.kosta.swag.ygmall.user.domain.User;
import kr.or.kosta.swag.ygmall.user.service.UserService;
import kr.or.kosta.swag.ygmall.user.service.UserServiceImpl;

/**
 * /users/regist 요청에 대한 컨트롤러
 * @author 김기정
 *
 */
public class UserRegistController implements Controller {
	
	private UserService service = new UserServiceImpl();
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ModelAndView mav = new ModelAndView();
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email") + "@" + request.getParameter("server");
		String telephone = request.getParameter("telephone");
		String job = request.getParameter("job");
		String message = request.getParameter("message");
		
		User user = new User();
		user.setId(id);
		user.setPasswd(passwd);
		user.setName(name);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setJob(job);
		user.setMessage(message);
		
		service.regist(user);
		
		request.getSession().setAttribute("user", user);
			
		mav.setView("redirect:result.mall");
		
		return mav;
	}

}
