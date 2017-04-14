package kr.or.kosta.swag.ygmall.user.service;

import java.util.List;
import kr.or.kosta.swag.ygmall.common.domain.Params;
import kr.or.kosta.swag.ygmall.user.domain.User;

/**
 * Domain(업무영역)별 고객의 요구사항을 반영하여 비즈니스 메소드 정의
 * 
 * @author 김기정
 *
 */
public interface UserService {

	/** 회원 가입 */
	public void regist(User user);

	/** 회원 인증 */
	public boolean isMember(String id, String passwd);

	/** 회원 목록 반환 */
	public List<User> listAll();

	/** {선택페이지, 검색유형, 검색값, 한페이지당 출력 행수}에 대한 회원목록 반환 */
	public List<User> listByPage(Params params);

	/** 출력페이지 계산을 위한 {검색유형, 검색값}에 대한 행의 수 반환 */
	public int countByPage(Params params);
	
	
	/** 아이디 중복여부 반환 */
	public boolean isDupId(String id);
	
	/** 기타 .... */

}
