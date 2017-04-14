<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="kr.or.kosta.swag.ygmall.user.domain.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>김기정님 블로그</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.servletContext.contextPath }/css/bootstrap.min.css"
  rel="stylesheet">
<link
  href="${pageContext.servletContext.contextPath }/css/bootstrap-theme.min.css"
  rel="stylesheet">
</head>
<body>

  <%-- Header include --%>
  <jsp:include page="../include/header.jsp" />

  <%-- Contents Begin --%>
  <div class="jumbotron">
    <div class="container">
      <h2 style="color: green;">${user.name}님 회원 가입을 환영합니다.
      </h2>
      <p>
        아이디 : ${user.id}<br>
        이메일 : ${user.email}<br>
        전화번호 : ${user.telephone}<br>
      </p>
      <p>
        <a class="btn btn-primary btn-lg"
          href="${pageContext.servletContext.contextPath }" role="button">홈으로
          &raquo;</a>
      </p>
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
  <script src="${pageContext.servletContext.contextPath }/js/bootstrap.min.js"></script>

</body>
</html>
