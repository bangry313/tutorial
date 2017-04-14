package kr.or.kosta.swag.ygmall.testcase;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.kosta.swag.ygmall.common.domain.Params;
import kr.or.kosta.swag.ygmall.user.dao.JdbcUserDao;
import kr.or.kosta.swag.ygmall.user.dao.UserDao;
import kr.or.kosta.swag.ygmall.user.domain.User;
import kr.or.kosta.swag.ygmall.user.service.UserService;
import kr.or.kosta.swag.ygmall.user.service.UserServiceImpl;

public class UserServiceTest {

	UserService service = new UserServiceImpl();

	@Test
	public void testDupId() {
		assertEquals(true, service.isDupId("bangry"));
		System.out.println(service.isDupId("bangry"));
	}

}
