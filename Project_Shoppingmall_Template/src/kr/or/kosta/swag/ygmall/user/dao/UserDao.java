package kr.or.kosta.swag.ygmall.user.dao;

import java.util.List;
import kr.or.kosta.swag.ygmall.common.domain.Params;
import kr.or.kosta.swag.ygmall.user.domain.User;

public interface UserDao {
	
	/** 신규 사용자 등록 */
	public void create(User user);
	
	/** 사용자아이디를 이용한 사용자 상세 정보 조회 */
	public User read(String id);
	
	/** 회원정보 수정 */
	public void update(User user);
	
	/** 회원 여부 반환 */
	public boolean isMember(String id, String passwd);
	
	/** 전체 회원목록 반환 */
	public List<User> list();
	
	/** 선택 페이지에 대한 회원목록 반환 */
	public List<User> listPage(int page);
	
	/** {선택페이지, 검색유형, 검색값, 한페이지당 출력 행수}에 대한 회원목록 반환 */
//	public List<User> pageList(int page, String searchType, String searchValue, int pageSize);
	public List<User> pageList(Params params);
	
	/** 출력페이지 계산을 위한 {검색유형, 검색값}에 대한 행의 수 반환 */
	public int pageCount(Params params);
	
	/** 아이디 중복여부 반환 */
	public boolean isDupId(String id);

}







