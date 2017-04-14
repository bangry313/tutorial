package kr.or.kosta.swag.ygmall.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosta.swag.ygmall.common.dao.ConnectionFactory;
import kr.or.kosta.swag.ygmall.common.domain.Params;
import kr.or.kosta.swag.ygmall.user.domain.User;

/**
 * JDBC API를 이용한 사용자(users) 테이블 관련 영속성 처리 DAO 클래스
 * DAO 패턴 적용
 * @author 김기정
 */
public class JdbcUserDao implements UserDao{
	
	/** 싱글톤 패턴이 적용된 ConnectionFactory 생성 */
	private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
	
	/** 신규 사용자 등록 */
	public void create(User user){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = " INSERT INTO users " +
		             "             (id," +
				     "              name," +
		             "              passwd," +
				     "              email," +
		             "              telephone," +
				     "              job," +
		             "              message)" +
		             " VALUES      (?," +
				     "              ?," +
		             "              ?," +
		             "              ?," +
		             "              ?," +
		             "              ?," +
		             "              ?)";
		
		try {
			con = connectionFactory.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPasswd());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getTelephone());
			pstmt.setString(6, user.getJob());
			pstmt.setString(7, user.getMessage());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException("회원 등록중 예외 발생", e);
		} finally {
			try {
				if (pstmt != null)	pstmt.close();
				// 사용한 커넥션을 커넥션풀에 반환
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/** 사용자아이디를 이용한 사용자 상세 정보 조회 */
	public User read(String id){
		User user = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT id, " +
		             "        name," +
				     "        passwd," +
		             " 	      email," +
				     "        telephone," +
		             "        job," +
				     "        message," +
		             "        TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') regdate" +
				     " FROM   users" +
		             " WHERE  id = ?";
		
		try {
			con = connectionFactory.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPasswd(rs.getString("passwd"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setJob(rs.getString("job"));
				user.setMessage(rs.getString("message"));
				user.setRegdate(rs.getString("regdate"));
			}
		} catch (Exception e) {
			throw new RuntimeException("회원상세 조회중 예외 발생", e);
		} finally {
			try {
				if (rs != null)	    rs.close();
				if (pstmt != null)	pstmt.close();
				if (con != null)	con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	
	/** 회원정보 수정 */
	public void update(User user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = " UPDATE users " +
		             " SET    passwd = ?," +
				     "        email = ?," +
		             "        telephone = ?," +
				     "        job = ?" +
		             " WHERE  id = ?";
		
		try {
			con = connectionFactory.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getPasswd());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getTelephone());
			pstmt.setString(4, user.getJob());
			pstmt.setString(5, user.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException("회원정보 수정 중 예외 발생", e);
		} finally {
			try {
				if (pstmt != null)	pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	@Override
	public boolean isMember(String id, String passwd) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT id " +
		             " FROM   users" +
				     " WHERE  id = ?" +
		             "        AND passwd = ?";
		
		try {
			con = connectionFactory.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			
			return rs.next();
			
		} catch (Exception e) {
			throw new RuntimeException("회원상세 조회중 예외 발생", e);
		} finally {
			try {
				if (rs != null)	    rs.close();
				if (pstmt != null)	pstmt.close();
				if (con != null)	con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	/** 전체 회원목록 반환 */
	public List<User> list() {
		List<User> list = new ArrayList<User>();		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT id, " +
		             "        name," +
		             " 	      email," +
				     "        telephone," +
		             "        job," +
		             "        message," +
		             "        TO_CHAR(regdate, 'YYYY/MM/DD HH24:MI:SS') regdate" +
				     " FROM   users" + 
				     " ORDER BY regdate DESC";
		
		try {
			con = connectionFactory.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				User user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setJob(rs.getString("job"));
				user.setMessage(rs.getString("message"));
				user.setRegdate(rs.getString("regdate"));
				list.add(user);
			}
		} catch (Exception e) {
			throw new RuntimeException("회원목록 조회중  예외 발생", e);
		} finally {
			try {
				if (rs != null)	    rs.close();
				if (pstmt != null)	pstmt.close();
				if (con != null)	con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/** 선택 페이지에 대한 회원목록 반환 */
	public List<User> listPage(int page) {
		List<User> list = new ArrayList<User>();		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT id,");
		sb.append("        name,");
		sb.append("        email,");
		sb.append("        telephone,");
		sb.append("        job,");
		sb.append("        regdate");
		sb.append(" FROM   (SELECT CEIL(rownum / 10) request_page,");
		sb.append("                id,");
		sb.append("                name,");
		sb.append("                email,");
		sb.append("                telephone,");
		sb.append("                job,");
		sb.append("                regdate");
		sb.append("         FROM   (SELECT id,");
		sb.append("                        name,");
		sb.append("                        email,");
		sb.append("                        telephone,");
		sb.append("                        job,");
		sb.append("                        TO_CHAR(regdate, 'YYYY/MM/DD HH24:MI:SS') regdate");
		sb.append("                 FROM   users");
		sb.append("                 ORDER BY regdate DESC))");
		sb.append(" WHERE  request_page = ?");
		
		try {
			con = connectionFactory.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, page);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				User user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setJob(rs.getString("job"));
				user.setRegdate(rs.getString("regdate"));
				list.add(user);
			}
		} catch (Exception e) {
			throw new RuntimeException("선택페이지에 대한 회원목록 조회중  예외 발생", e);
		} finally {
			try {
				if (rs != null)	    rs.close();
				if (pstmt != null)	pstmt.close();
				if (con != null)	con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	/** {선택페이지, 검색유형, 검색값, 한페이지당 출력 행수}에 대한 회원목록 반환 */
	public List<User> pageList(Params params) {
		List<User> list = new ArrayList<User>();		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT id,");
		sb.append("        name,");
		sb.append("        email,");
		sb.append("        telephone,");
		sb.append("        job,");
		sb.append("        regdate");
		sb.append(" FROM   (SELECT CEIL(rownum / ?) request_page,");
		sb.append("                id,");
		sb.append("                name,");
		sb.append("                email,");
		sb.append("                telephone,");
		sb.append("                job,");
		sb.append("                regdate");
		sb.append("         FROM   (SELECT id,");
		sb.append("                        name,");
		sb.append("                        email,");
		sb.append("                        telephone,");
		sb.append("                        job,");
		sb.append("                        TO_CHAR(regdate, 'YYYY/MM/DD HH24:MI:SS') regdate");
		sb.append("                 FROM   users");
		
		// 검색 유형별 WHERE 절 동적 추가
		String value = null;
		if(params.getType() != null){
			switch (params.getType()) {
				case "id":    
					sb.append("                 WHERE  id  = ?");
					value = params.getValue();
					break;
				case "name":  
					sb.append("                 WHERE  name LIKE ?");
					value = "%" + params.getValue() + "%";
					break;
				case "job":   
					sb.append("                 WHERE  job LIKE ?");
					value = "%" + params.getValue() + "%";
					break;
			}
		}
		sb.append("                 ORDER BY regdate DESC))");
		sb.append(" WHERE  request_page = ?");
		
		
		try {
			con = connectionFactory.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, params.getPageSize());
			
			// 전체검색이 아닌경우 경우
			if(params.getType() != null){
				pstmt.setString(2, value);
				pstmt.setInt(3, params.getPage());
			}else{// 전체검색인 경우
				pstmt.setInt(2, params.getPage());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				User user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setJob(rs.getString("job"));
				user.setRegdate(rs.getString("regdate"));
				list.add(user);
			}
		} catch (Exception e) {
			throw new RuntimeException("페이징 처리 회원목록 조회중  예외 발생", e);
		} finally {
			try {
				if (rs != null)	    rs.close();
				if (pstmt != null)	pstmt.close();
				if (con != null)	con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/** 출력페이지 계산을 위한 {검색유형, 검색값}에 대한 행의 수 반환 */
	public int pageCount(Params params) {
		int count = 0;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT COUNT(id) count");
		sb.append(" FROM   users");
		
		// 검색 유형별 WHERE 절 동적 추가
		String value = null;
		if(params.getType() != null){
			switch (params.getType()) {
				case "id":
					sb.append(" WHERE  id  = ?");
					value = params.getValue();
					break;
				case "name":
					sb.append(" WHERE  name LIKE ?");
					value = "%" + params.getValue() + "%";
					break;
				case "job":
					sb.append(" WHERE  job LIKE ?");
					value = "%" + params.getValue() + "%";
					break;
			}
		}
				
		try {
			con = connectionFactory.getConnection();
			pstmt = con.prepareStatement(sb.toString());

			// 전체검색이 아닌경우 경우
			if(params.getType() != null){
				pstmt.setString(1, value);
			}

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			throw new RuntimeException("페이징 처리 회원수 조회중  예외 발생", e);
		} finally {
			try {
				if (rs != null)	    rs.close();
				if (pstmt != null)	pstmt.close();
				if (con != null)	con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}


	/** 아이디 중복여부 반환 */
	public boolean isDupId(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT id " +
		             " FROM   users" +
				     " WHERE  id = ?";
		
		try {
			con = connectionFactory.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			return rs.next();
			
		} catch (Exception e) {
			throw new RuntimeException("회원아이디 중복 체크 중 예외 발생", e);
		} finally {
			try {
				if (rs != null)	    rs.close();
				if (pstmt != null)	pstmt.close();
				if (con != null)	con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}












