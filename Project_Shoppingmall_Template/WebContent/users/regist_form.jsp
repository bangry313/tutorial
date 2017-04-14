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
<link href="${pageContext.servletContext.contextPath }/css/bootstrap-theme.min.css"  rel="stylesheet">
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript">
$(function name() {
	$("#id").keyup(function(){
		var inputId = $(this).val();
		if(inputId.length >= 6 && inputId.length <= 12){
			send(inputId);
		}else{
			$("#idDupBox").html("");
		}
	});
});

function send(id){
	$.ajax("idDupCheck.mall", {
		data : "id="+id,
		dataType: "text",
		success : function(data) {
			processResult(data);
		}
	});
}

function processResult(result){
	  var message = null;
	  if(result == "true"){
		  message = "<span style='color: red'>사용불가능</span>"        		  
	  }else{
		  message = "<span style='color: blue'>사용가능</span>"  
	  }
	  
	  $("#idDupBox").html(message);
}
</script>

</head>
<body>

  <%-- Header include --%>
  <jsp:include page="../include/header.jsp" />
  
  <br>
  
  <%-- Contents Begin --%>
  <div class="container">
    <div class="row">
      <div class="page-header">
        <h2>회원 가입</h2>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-5">
        
        <form class="form-horizontal" role="form" action="regist.mall" method="post">
          
          <div class="form-group">
            <label class="control-label col-sm-3" for="id">아이디:</label>
            <div class="col-sm-5">
              <input type="text" class="form-control" id="id"  name="id" max="12"   placeholder="Enter ID">
            </div>
            <label class="control-label col-sm-4" id="idDupBox"></label>
          </div>
          
          <div class="form-group">
            <label class="control-label col-sm-3" for="passwd">비밀번호:</label>
            <div class="col-sm-9">
              <input type="password" class="form-control" id="passwd"  name="passwd"   placeholder="Enter Password">
            </div>
          </div>
          
          <div class="form-group">
            <label class="control-label col-sm-3" for="repasswd">비밀번호확인:</label>
            <div class="col-sm-9">
              <input type="password" class="form-control" id="repasswd"  name="repasswd"   placeholder="Enter Password">
            </div>
          </div>
          
          <div class="form-group">
            <label class="control-label col-sm-3" for="name">이름:</label>
            <div class="col-sm-9">
              <input type="text" class="form-control" id="name"  name="name"   placeholder="Enter Name">
            </div>
          </div>
          
          <div class="form-group">
            <label class="control-label col-sm-3" for="email">이메일:</label>
            <div class="col-sm-4">
              <input type="text" class="form-control" id="email"  name="email"  placeholder="Enter Email">
            </div>
            <label class="control-label col-sm-1">@</label>
            <div class="col-sm-4">
              <select class="form-control" name="server">
                <option>naver.com</option>
                <option>daum.net</option>
                <option>korea.com</option>
              </select>
            </div>
          </div>
          
          <div class="form-group">
            <label class="control-label col-sm-3" for="telephone">전화번호:</label>
            <div class="col-sm-9">
              <input type="tel" class="form-control" id="telephone"  name="telephone"   placeholder="Enter Telephone">
            </div>
          </div>
          
          <div class="form-group">
            <label class="control-label col-sm-3" for="job">직업:</label>
            <div class="col-sm-9">
              <select class="form-control" id="job" name="job">
                <option>회사원</option>
                <option>강사</option>
                <option>학생</option>
                <option>기타</option>
              </select>
            </div>
          </div>
          
          <div class="form-group">
            <label class="control-label col-sm-3" for="message">가입인사:</label>
            <div class="col-sm-9">
              <textarea class="form-control" id="message" name="message" rows="3"></textarea>
            </div>
          </div>         
          
          <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
              <button type="submit" class="btn btn-success">가입하기</button>
              <button type="reset" class="btn btn-warning">취소하기</button>
            </div>
          </div>
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
  <script src="${pageContext.servletContext.contextPath }/js/bootstrap.min.js"></script>

</body>
</html>
