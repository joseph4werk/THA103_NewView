<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tha103.newview.pubuser.model.*"%>
<%@ page import="com.tha103.newview.pubuser.service.*"%>

<%
PubUserVO pubuserVO = (PubUserVO) request.getAttribute("pubuserVO");
//EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
System.out.println("pubuserVO=" + pubuserVO);
%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NEW VIEW | Backstage</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Backstage/plugins/fontawesome-free/css/all.min.css">
<!-- daterange picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Backstage/plugins/daterangepicker/daterangepicker.css">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Backstage/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Bootstrap Color Picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Backstage/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Backstage/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- Select2 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/Backstage/plugins/select2/css/select2.min.css">
<!-- Bootstrap4 Duallistbox -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Backstage/plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
<!-- BS Stepper -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Backstage/plugins/bs-stepper/css/bs-stepper.min.css">
<!-- dropzonejs -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Backstage/plugins/dropzone/min/dropzone.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/Backstage/dist/css/adminlte.min.css">
</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">

		<jsp:include page="../template/nav.jsp" flush="true">
			<jsp:param name="name0" value="peter0" />
		</jsp:include>

		<jsp:include page="../template/aside.jsp" flush="true">
			<jsp:param name="name0" value="peter0" />
		</jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>修改使用者</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/Backstage/Allpage-publisher/pub-index.jsp">後台首頁</a></li>
								<li class="breadcrumb-item active">權限管理</li>
							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- Main content -->
			<section class="content">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<!-- Default box -->
				<div class="card">
					<form METHOD="post" action="<%=request.getContextPath()%>/pubuser/pubuser.do" name="form1"
						style="padding: 30px 0px;">
						<div class="col-md-10 offset-md-1">
							<div class="form-group">
								<label for="inputPubID">使用者編號：</label> 
								<input type="text" class="form-control" id="inputPubID" name="pubUserID" value="<%=pubuserVO.getPubUserID()%>"/>	
							</div>
							<div class="form-group">
								<label for="inputNickname">使用者暱稱：</label> 
								<input type="text" class="form-control" id="inputNickname" name="pubNickname" value="<%=pubuserVO.getPubNickname() %>"/>	
							</div>
							<div class="form-group">
								<label for="inputAccount">使用者帳號：</label> 
								<input type="text" class="form-control" id="inputAccount" name="pubAccount" value="<%=pubuserVO.getPubAccount()%>" />
							</div>
							<div class="form-group">
								<label for="inputPassword">使用者密碼：</label> 
								<input type="text" class="form-control" id="inputPassword" name="pubPassword" value="<%=pubuserVO.getPubPassword()%>" />
							</div>
							<div class="form-group">
								<label for="inputAuthority">使用權限：</label> <input type="radio"
									id="Authority0" checked /> <label for="Authority0"
									style="margin-right: 20px;">最高權限</label> <input type="radio"
									id="Authority1" name="1" /> <label for="Authority1"
									style="margin-right: 20px;">一般權限</label> <input type="hidden"
									id="pubAuthority" name="pubAuthority" value="0">
							</div>
							<div class="form-group">
								<input type="hidden" name="action" value="update"> 
								<input type="hidden" name="pubUserID" value="<%=pubuserVO.getPubUserID() %>"> 
								<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> 
								<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
								<input type="submit" class="btn btn-primary" value="送出">
							</div>
						</div>
					</form>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<jsp:include page="../template/footer.jsp" flush="true">
			<jsp:param name="footer" value="footer" />
		</jsp:include>

	</div>
	<!-- ./wrapper -->

	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/Backstage/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath()%>/Backstage/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/Backstage/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/Backstage/dist/js/demo.js"></script>
</body>

</html>