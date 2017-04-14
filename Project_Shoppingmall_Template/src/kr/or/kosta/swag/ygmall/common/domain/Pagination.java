package kr.or.kosta.swag.ygmall.common.domain;

/**
 * 페이징 계산 및 페이지 번호(1|2|3|4|5....) 출력을 각각의 JSP에서 처리하지 않고,
 * 재사용할 수 있도록 유틸리티 클래스 정의
 * @author 김기정
 */
public class Pagination {
	
	private int requestPage;          /** 사용자 요청 페이지 */
	private String type;              /** 사용자 검색 유형 */
	private String value;             /** 사용자 검색 값 */
	private int displayPageSize;      /** 페이지에 출력할 행의 수 */
	private int displayPageNum;       /** 페이지에 출력할 페이지 수 */
	
	
	private int totalRowCount;        /** 테이블로부터 검색된 행의 수 */
	private int totalPageCount;       /** 연산에 따른 전체페이지 수 */
	private int listNo;               /** 목록 번호 */
	private int currentStartPage;     /** 현재 목록의 시작페이지 번호 */
	private int currentEndPage;       /** 현재 목록의 마지막페이지 번호 */
	private int previousStartPage;    /** 이전 목록의 시작페이지 번호 */
	private int nextStartPage;        /** 다음 목록의 시작페이지 번호 */
	
	
	public Pagination() {
		this(1, null, null, 10, 10, 0);
	}
	
	/**
	 * @param displayPageSize  페이지에 출력할 행의 수
	 * @param displayPageNum   페이지에 출력할 페이지 수
	 * @param totalRowCount    검색타입별 검색된 행의 수
	 * @param requestPage      사용자 요청 페이지
	 */
	public Pagination(int requestPage, String type, String value, int displayPageSize, int displayPageNum, int totalRowCount ) {
		this.requestPage = requestPage;
		this.type = type;
		this.value = value;
		this.displayPageSize = displayPageSize;
		this.displayPageNum = displayPageNum;
		this.totalRowCount = totalRowCount;
	}
	
	public int getRequestPage() {
		return requestPage;
	}

	public void setRequestPage(int requestPage) {
		this.requestPage = requestPage;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getDisplayPageSize() {
		return displayPageSize;
	}

	public void setDisplayPageSize(int displayPageSize) {
		this.displayPageSize = displayPageSize;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getListNo() {
		return listNo;
	}

	public void setListNo(int listNo) {
		this.listNo = listNo;
	}
	
	public int getCurrentStartPage() {
		return currentStartPage;
	}

	public void setCurrentStartPage(int currentStartPage) {
		this.currentStartPage = currentStartPage;
	}

	public int getCurrentEndPage() {
		return currentEndPage;
	}

	public void setCurrentEndPage(int currentEndPage) {
		this.currentEndPage = currentEndPage;
	}

	public int getPreviousStartPage() {
		return previousStartPage;
	}

	public void setPreviousStartPage(int previousStartPage) {
		this.previousStartPage = previousStartPage;
	}

	public int getNextStartPage() {
		return nextStartPage;
	}

	public void setNextStartPage(int nextStartPage) {
		this.nextStartPage = nextStartPage;
	}

	/** 페이지네이션 계산 */
	public void operatePageCount(){
		// DB로부터 검색된 행의 수에 따른 전체페이지수 계산
		totalPageCount = (int)Math.ceil((double)totalRowCount / displayPageSize);
		
		// 목록별 번호
		listNo = (requestPage - 1) / displayPageNum;
		//(1~5): 0, (6~10): 1, (11~15): 2, .....
		
		// 현재 목록의 시작페이지번호와 마지막페이지번호 계산
		currentStartPage = (listNo * displayPageNum) + 1;
		currentEndPage = (listNo * displayPageNum) + displayPageNum;
		
		/*
		if (currentStartPage > totalPageCount){
			currentStartPage = currentStartPage - displayPageNum + 1;
		}
		*/
		if (currentEndPage > totalPageCount){
			currentEndPage = totalPageCount;
		}
		
		// 이전 목록의 시작페이지 번호 계산
		previousStartPage = currentStartPage - displayPageNum;
		// 첫번째 목록인 경우 1페이지로 설정
		if (previousStartPage < 0)  previousStartPage = 1;
		
		// 다음 목록의 시작페이지 번호 계산
		nextStartPage = currentStartPage + displayPageNum;
	}
	
	/** 현재 목록에서 [처음으로] 출력 여부 반환 */
	public boolean isShowFirst() {
		return listNo > 0;
	}
	
	/** 현재 목록에서 [끝으로] 출력 여부 반환 */
	public boolean isShowLast() {
		return currentEndPage < totalPageCount;
	}
	
	/** 현재 목록에서 [이전목록] 출력 여부 반환 */
	public boolean isShowPrevious() {
		return listNo > 0;
	}
	
	/** 현재 목록에서 [다음목록] 출력 여부 반환 */
	public boolean isShowNext() {
		return currentEndPage < totalPageCount;
	}
	
	
	
	/** JSP에서 페이징 출력을 쉽게 할 수 있도록 문자열(HTML) 반환 */
	public String toHtml(){
		String parameters = null;
		
		// 조건검색이 있는 경우 쿼리스트링(?type=id&value=bangry) 추가
		parameters = type != null ? "&type=" + type + "&value=" + value  :  "";
		
		StringBuilder sb = new StringBuilder();
		sb.append("<ul class=\"pagination\">");
		// 처음으로 출력 여부
		if(isShowFirst()){
			sb.append("<li><a href=\"?page=" + previousStartPage + parameters + "\">First</a></li>");		
		}
		// 이전목록 출력 여부
		if(isShowPrevious()){
			sb.append("<li class=\"prev\"><a href=\"?page=" + previousStartPage + parameters + "\">«</a></li>");
		}
		
		// 페이지 번호 출력
		for(int i=currentStartPage; i<=currentEndPage; i++){
			if(i == requestPage){
				sb.append("<li class=\"active\"><a>" + i + "</a></li>");
			}else{
				sb.append("<li><a href=\"?page=" + i + parameters + "\">" + i + "</a></li>");;
			}
		}
		
		// 다음 목록 보여주기 여부
		if(isShowNext()){
			 sb.append("<li class=\"next\"><a href=\"?page=" + nextStartPage + parameters + "\">»</a></li>");
		}
		
		// 끝으로 출력 여부
		if(isShowLast()){
			sb.append("<li><a href=\"?page=" + totalPageCount + parameters + "\">Last</a></li>");		
		}
		
		sb.append("</ul>");
		
		return sb.toString();
	}
	
	
	/** 테스트을 위한 main */
	public static void main(String[] args) {
		/** 사용자 선택페이지, 검색유형, 검색값, 페이지에 출력할 행의 수, 페이지 수, 테이블에서 검색된 행의 수 */
		Pagination pagination = new Pagination();
		pagination.setRequestPage(15);
		pagination.setType(null);
		pagination.setValue(null);
		pagination.setDisplayPageSize(10);
		pagination.setDisplayPageNum(10);
		pagination.setTotalRowCount(156);
		pagination.operatePageCount();
		
		System.out.println("검색된 행수: " + pagination.getTotalRowCount());
		System.out.println("요청페이지: " + pagination.getRequestPage());
		
		System.out.println("전체페이지수: " + pagination.getTotalPageCount());
		
		System.out.println("현재목록의 시작페이지: " + pagination.getCurrentStartPage());
		System.out.println("현재목록의 끝페이지: " + pagination.getCurrentEndPage());
		
		System.out.println("처음으로 버튼 보여주기 여부: " + pagination.isShowFirst());
		System.out.println("이전목록 버튼 보여주기 여부: " + pagination.isShowPrevious());
		
		System.out.println("다음목록 버튼 보여주기 여부: " + pagination.isShowNext());
		System.out.println("끝으로 버튼 보여주기 여부: " + pagination.isShowLast());
		
		// JSP에서 페이지 번호 출력		
		for(int i=pagination.getCurrentStartPage(); i<=pagination.getCurrentEndPage(); i++){
			System.out.print(i + " | ");
		}
		System.out.println();
		
		
		// 페이지번호 출력용 문자열(HTML) 반환
		System.out.println(pagination.toHtml());//전체검색
		// 이름으로 검색 시
//		pagination.setType("name");
//		pagination.setValue("테스터");
//		System.out.println(pagination.toHtml());
		
	}
}
