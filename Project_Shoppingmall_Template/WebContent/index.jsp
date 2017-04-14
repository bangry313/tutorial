<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>쇼핑몰 가상 메인페이지</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css"  rel="stylesheet">
<link href="${pageContext.servletContext.contextPath }/css/bootstrap-theme.min.css"  rel="stylesheet">
</head>
<body>

  <%-- Header include --%>
  <jsp:include page="include/header.jsp" />

  <%-- jumbotron Begin --%>
  <div class="jumbotron">
    <div class="container">
      <h2 style="color: tomato">${mainTitle }</h2>
      <p>This is a Homepage for Servlet/JSP Trainning website. It
        includes a large callout called a jumbotron and three supporting
        pieces of content. Use it as a starting point to create
        something more unique.</p>
      <p>
        <a class="btn btn-primary btn-lg" href="#" role="button">Learn
          more &raquo;</a>
      </p>
    </div>
  </div>
  <%-- jumbotron End --%>

  <%-- Contents Begin --%>
  <div class="container">
    <!-- Example row of columns -->
    <div class="row">
      <div class="col-md-4">
        <h2>타이틀1</h2>
        <p>Donec id elit non mi porta gravida at eget metus. Fusce
          dapibus, tellus ac cursus commodo, tortor mauris condimentum
          nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
          malesuada magna mollis euismod. Donec sed odio dui.</p>
        <p>
          <a class="btn btn-info" href="#" role="button">상세보기 &raquo;</a>
        </p>
      </div>
      <div class="col-md-4">
        <h2>타이틀2</h2>
        <p>Donec id elit non mi porta gravida at eget metus. Fusce
          dapibus, tellus ac cursus commodo, tortor mauris condimentum
          nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
          malesuada magna mollis euismod. Donec sed odio dui.</p>
        <p>
          <a class="btn btn-info" href="#" role="button">상세보기 &raquo;</a>
        </p>
      </div>
      <div class="col-md-4">
        <h2>타이틀3</h2>
        <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis
          in, egestas eget quam. Vestibulum id ligula porta felis
          euismod semper. Fusce dapibus, tellus ac cursus commodo,
          tortor mauris condimentum nibh, ut fermentum massa justo sit
          amet risus.</p>
        <p>
          <a class="btn btn-info" href="#" role="button">상세보기 &raquo;</a>
        </p>
      </div>
    </div>
    <%-- Contents End --%>

    <hr>

    <%-- Footer include --%>
    <jsp:include page="include/footer.jsp" />

  </div>
  <!-- /container -->

  <script
    src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${pageContext.servletContext.contextPath }/js/bootstrap.min.js"></script>

</body>
</html>
