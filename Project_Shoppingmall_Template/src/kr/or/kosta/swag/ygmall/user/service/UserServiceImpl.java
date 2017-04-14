package kr.or.kosta.swag.ygmall.user.service;

import java.util.List;

import kr.or.kosta.swag.ygmall.common.domain.Params;
import kr.or.kosta.swag.ygmall.user.dao.JdbcUserDao;
import kr.or.kosta.swag.ygmall.user.dao.UserDao;
import kr.or.kosta.swag.ygmall.user.domain.User;

public class UserServiceImpl implements UserService {
	
	private UserDao dao = new JdbcUserDao();

	@Override
	public void regist(User user) {
		dao.create(user);		
	}

	@Override
	public boolean isMember(String id, String passwd) {
		return dao.isMember(id, passwd);
	}

	@Override
	public List<User> listAll() {
		return dao.list();
	}
	
	@Override
	public List<User> listByPage(Params params) {
		return dao.pageList(params);
	}

	@Override
	public int countByPage(Params params) {
		return dao.pageCount(params);
	}
	
	
	@Override
	public boolean isDupId(String id) {
		return dao.isDupId(id);
	}
	
	public static void main(String[] args) {
		UserService service = new UserServiceImpl();
//		System.out.println(service.isMember("bangry", "1111"));
		System.out.println(service.isDupId("bangry"));
		
	}
}








