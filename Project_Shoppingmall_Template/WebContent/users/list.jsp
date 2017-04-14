<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>쇼핑몰 가상 메인페이지</title>
<!-- Bootstrap core CSS -->
<link  href="${pageContext.servletContext.contextPath }/css/bootstrap.min.css"  rel="stylesheet">
<link  href="${pageContext.servletContext.contextPath }/css/bootstrap-theme.min.css"  rel="stylesheet">
</head>
<body>
  <%-- Header include --%>
  <jsp:include page="../include/header.jsp" />

  <br>

  <%-- Contents Begin --%>
  <div class="container">
    <div class="row">
      <div class="page-header">
        <h2>
          회원 목록<span class="badge">${pagination.totalRowCount}</span>
        </h2>
      </div>
    </div>

    <div class="row">
      <div>
        <table
          class="table table-striped table-bordered table-condensed table-hover">
          <thead>
            <tr class="success">
              <th style="width: 50px">번호</th>
              <th>이름</th>
              <th>아이디</th>
              <th>이메일</th>
              <th>전화번호</th>
              <th>직업</th>
              <th>가입일자</th>
            </tr>
          </thead>
          <tbody>
            <c:choose>
              <c:when test="${empty list }">
                <tr>
                  <td colspan="7"
                    style="text-align: center; color: red;">검색된 회원이 존재하지 않습니다.</td>
                </tr>
              </c:when>
              <c:otherwise>
                <c:forEach var="user" items="${list}" varStatus="status">
                  <tr>
                    <td>${(pagination.totalRowCount - ((pagination.requestPage - 1) * pagination.displayPageSize)) - status.index}</td>
                    <td><a href="#">${user.name }</a></td>
                    <td>${user.id}</td>
                    <td><a href="mailto:${user.email}">${user.email}</a></td>
                    <td>${user.telephone}</td>
                    <td>${user.job}</td>
                    <td>${user.regdate}</td>
                  </tr>
                </c:forEach>
              </c:otherwise>
            </c:choose>
          </tbody>
        </table>
      </div>
    </div>

    <div class="row">
      <div style="text-align: center;">
        ${paging }

        <%-- 검색 --%>
        <form name="search" class="form-inline" role="form">
          <div class="form-group">
            <select class="form-control" id="type" name="type">
              <option value="id">아이디</option>
              <option value="name">이름</option>
              <option value="job">직업</option>
            </select> <input type="text" class="form-control" id="value"
              name="value" required>
          </div>
          <button type="submit" class="btn btn-default">검색</button>
        </form>


      </div>
    </div>
    <%-- Contents End --%>

    <hr>

    <%-- Footer include --%>
    <jsp:include page="../include/footer.jsp" />

  </div>
  <!-- /container -->

  <script
    src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script    src="${pageContext.servletContext.contextPath }/js/bootstrap.min.js"></script>

</body>
</html>
