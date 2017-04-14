package kr.or.kosta.swag.ygmall.common.web;

import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Simple Factory 패턴 적용 ControllerFactory
 * mapping 설정 파일로 XML 활용
 * @author 김기정
 */
public class XMLControllerFactory {

	// 요청에 대한 세부 컨트롤러 클래스 생성 및 관리
	private HashMap<String, Controller> controllerMap;
	private Document document;

	public XMLControllerFactory(String controllerMappingPath) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = factory.newDocumentBuilder();
		document = parser.parse(controllerMappingPath);

		controllerMap = new HashMap<String, Controller>();

		Element controllersElement = document.getDocumentElement();
		NodeList controllers = controllersElement.getChildNodes();
		for (int i = 0; i < controllers.getLength(); i++) {
			Node node = controllers.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element controller = (Element)node;
				String name = controller.getAttribute("name");
				String controllerClass = controller.getAttribute("class");
				Controller controllerObject = null;
				controllerObject = (Controller) Class.forName(controllerClass).newInstance();
				controllerMap.put(name, controllerObject);
			}
		}
	}

	public Controller getController(String name) {
		return controllerMap.get(name);
	}
}
