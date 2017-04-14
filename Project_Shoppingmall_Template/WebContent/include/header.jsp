<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	window.onload = function() {
		// 이벤트소스(타겟)에 이벤트리스너 등록
		var ulElement = document.getElementsByClassName("nav navbar-nav")[0];
		var list = ulElement.childNodes;
		for (var i = 0; i < list.length; i++) {
			var li = list.item(i);
			if (li.nodeType == 1) {
				li.onclick = function() {
					var items = this.parentNode.childNodes;
					for (var i = 0; i < items.length; i++) {
						var item = items[i];
						if (item.nodeType == 1) {
							var value = item.getAttribute("class");
							if (value == "active") {
								item.removeAttribute("class");
							}
						}
					}
					this.setAttribute("class", "active");
				}
			}

		}

		var button = document.getElementById("logoutButton");
		button.onclick = function() {
			logout();
		}
	}

	function logout() {
		window.location.href = "${pageContext.servletContext.contextPath }/users/confirm.mall";
	}
</script>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed"
        data-toggle="collapse" data-target="#navbar"
        aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span> <span
          class="icon-bar"></span> <span class="icon-bar"></span> <span
          class="icon-bar"></span>
      </button>
      <a class="navbar-brand glyphicon glyphicon-thumbs-up" href="/"
        style="color: yellow;">OOO쇼핑몰 타이틀</a>
    </div>

    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li class="active"><a class="glyphicon glyphicon-home"
          href="/">Home</a></li>
        <li><a class="glyphicon glyphicon-book" href="#">XXX</a></li>
        <li><a class="glyphicon glyphicon-list" href="#">OOO</a></li>
        <li class="dropdown"><a href="#"
          class="dropdown-toggle glyphicon glyphicon-blackboard"
          data-toggle="dropdown" role="button" aria-expanded="false">XXX
            <span class="caret"></span>
        </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="/board/list_resource.jsp">자료실</a></li>
            <li><a href="#">묻고답하기</a></li>
            <li class="divider"></li>
            <li><a href="#">공지사항</a></li>
          </ul></li>
        <li><a class="glyphicon glyphicon-align-justify"
          href="${pageContext.servletContext.contextPath }/users/list.mall">회원목록</a></li>
      </ul>

      <c:choose>
        <c:when test="${empty cookie.loginId}">
          <form class="navbar-form navbar-right"
            action="users/confirm.mall" method="post">
            <div class="form-group">
              <input type="text" name="id" placeholder="아이디"
                class="form-control" size="10">
            </div>
            <div class="form-group">
              <input type="password" name="passwd" placeholder="비밀번호"
                class="form-control" size="10">
            </div>
            <button type="submit" class="btn btn-success">로그인</button>
            <a class="glyphicon glyphicon-user"
              href="${pageContext.servletContext.contextPath }/users/regist_form.mall"
              style="color: white;">회원가입</a>
          </form>
        </c:when>
        <c:otherwise>
          <form class="navbar-form navbar-right">
            <div class="form-group">
              <label style="color: white">${cookie.loginId.value }님 로그인중</label>
            </div>
            <button type="button" id="logoutButton"
              class="btn btn-success btn-sm">Log out</button>
          </form>
        </c:otherwise>
      </c:choose>
    </div>
    <!--/.navbar-collapse -->
  </div>
</nav>