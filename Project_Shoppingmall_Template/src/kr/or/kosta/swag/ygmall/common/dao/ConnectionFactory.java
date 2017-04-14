package kr.or.kosta.swag.ygmall.common.dao;

import java.sql.Connection;
import org.apache.commons.dbcp2.BasicDataSource;
/**
 * JDBC Connection 생성 팩토리
 * Singleton 패턴 적용
 * Apache DBCP(커넥션풀라이브러리) API 사용
 * @author 김기정
 */
public class ConnectionFactory {
	
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER_ID = "hr";
	private static final String USER_PW = "hr";
	private static final int MAX_COUNT = 5;
	private static final int IDLE_COUNT = 2;
	
	// Apache 재단에서 무료로 제공하는 커넥션 풀
	private BasicDataSource dataSource;
	
	private static ConnectionFactory instance = new ConnectionFactory();
	
	private ConnectionFactory(){
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DRIVER_NAME);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(USER_ID);
		dataSource.setPassword(USER_PW);
		dataSource.setMaxTotal(MAX_COUNT);
		dataSource.setMaxIdle(IDLE_COUNT);
	}
	
	public static ConnectionFactory getInstance(){
		return instance;
	}

	public Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}
}







