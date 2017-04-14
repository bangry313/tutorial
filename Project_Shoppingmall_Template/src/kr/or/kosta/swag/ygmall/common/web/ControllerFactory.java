package kr.or.kosta.swag.ygmall.common.web;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import kr.or.kosta.swag.ygmall.common.web.Controller;

/**
 * Simple Factory 패턴 적용 ControllerFactory
 * @author 김기정
 */
public class ControllerFactory {
	
	// 매핑파일 경로
	private String controllerMappingPath;
	
	// 요청에 대한 세부 컨트롤러 클래스 생성 및 관리
	private HashMap<String, Controller> controllerMap; 
	
	
	public ControllerFactory(String controllerMappingPath){
		this.controllerMappingPath = controllerMappingPath;
		controllerMap = new HashMap<String, Controller>();

		// 매핑정보를 저장할 Properties 객체 생성
		Properties prop = new Properties();
		FileInputStream fis = null;
		try{
			
			fis = new FileInputStream(controllerMappingPath);
			prop.load(fis);
			Iterator keyIter = prop.keySet().iterator();
			while(keyIter.hasNext()){
				String uri = (String)keyIter.next();
				String controllerClass = prop.getProperty(uri);
				// 컨트롤러 생성
				Controller controllerObject = (Controller)Class.forName(controllerClass).newInstance();
				controllerMap.put(uri, controllerObject);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
		
	public Controller getController(String uri){
		return controllerMap.get(uri);		
	}
}









