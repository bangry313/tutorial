package kr.or.kosta.swag.ygmall.testcase;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.kosta.swag.ygmall.common.domain.Params;
import kr.or.kosta.swag.ygmall.user.dao.JdbcUserDao;
import kr.or.kosta.swag.ygmall.user.dao.UserDao;
import kr.or.kosta.swag.ygmall.user.domain.User;

public class UserDaoTest {

	UserDao dao = new JdbcUserDao();

	@Test
	public void testDupId() {
		assertEquals(true, dao.isDupId("bangry"));

	}
	
	@Test
	public void testRead() {
		// 사용자 정보 조회
		User user = dao.read("bangry");
		System.out.println(user);
	}
	
	@Test
	public void testUpdate() {
		// 사용자 정보 수정
		User user = new User("gildong", "홍길동", "9999", "gildong@chosun.com", "010-9999-9999", "기타", "조선 제일 검객",
				"1500-01-01");
		dao.update(user);
		System.out.println(dao.read("gildong"));
	}
	
	@Test
	public void testList() {
		// 전체 회원목록 조회
		List<User> list = dao.list();
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testListPage() {
		// 선택페이지에 대한 회원목록 조회
		List<User> list = dao.listPage(2);
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testPageList() {
		// {선택페이지, 검색유형, 검색값, 한페이지당 출력 행수}에 대한 회원목록 조회
		Params params = new Params();
		params.setPage(2);
		// params.setType("name");
		// params.setValue("테스터");
		// params.setPageSize(10);

		List<User> list = dao.pageList(params);
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testPageCount() {
		// 페이징 계산에 필요한 회원수 조회
		Params params = new Params();
		// params.setType("name");
		// params.setValue("테스터");

		int count = dao.pageCount(params);
		System.out.println("조회된 회원수: " + count);
	}

}
