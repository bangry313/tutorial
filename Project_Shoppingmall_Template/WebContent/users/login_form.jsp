<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>쇼핑몰 가상 메인페이지</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.servletContext.contextPath }/css/bootstrap.min.css"  rel="stylesheet">
<link href="${pageContext.servletContext.contextPath }/css/signin.css"  rel="stylesheet">
</head>

<body>
  <div class="container">

    <form class="form-signin" action="confirm.mall" method="post">
      <h2 class="form-signin-heading">로그인</h2>
      <label for="inputEmail" class="sr-only">ID</label> <input
        type="text" id="id" name="id" class="form-control"
        placeholder="ID" required autofocus> <label for="passwd"
        class="sr-only">Password</label> <input type="password"
        id="passwd" name="passwd" class="form-control"
        placeholder="Password" required>
        <input type="hidden" name="referer"  value="${param.referer}">
      <div>
        <p style="color: red;">${param.message}</p>
        <p style="color: red;">아이디 또는 비밀번호를 다시 확인하세요.</p>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Log
        in</button>
    </form>

  </div>

</body>
</html>
