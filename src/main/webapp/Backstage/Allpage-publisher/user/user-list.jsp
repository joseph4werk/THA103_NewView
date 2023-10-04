<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.newview.publisher.model.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NEW VIEW | Backstage</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="../../plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../../dist/css/adminlte.min.css">
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
							<h1>使用者列表</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="../../index.html">後台首頁</a></li>
								<li class="breadcrumb-item active">權限管理</li>
							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<!-- /.row -->
					<div class="row">
						<div class="col-12">
							<div class="form-group">
								<button type="button" class="btn btn-dark">新增使用者</button>
							</div>
							<div class="card">
								<!-- /.card-header -->
								<div class="card-body table-responsive p-0">
									<table class="table table-hover text-nowrap">
										<thead>
											<tr>
												<th>編號</th>
												<th>使用者暱稱</th>
												<th>使用者帳號</th>
												<th>使用者權限</th>
												<th style="text-align: center;" colspan="2">操作</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>小咪</td>
												<td>miAccount</td>
												<td>高階權限</td>
												<td>
													<button type="button"
														class="btn btn-block btn-success btn-sm">修改</button>
												</td>
												<td>
													<button type="button"
														class="btn btn-block btn-danger btn-sm">刪除</button>
												</td>
											</tr>
											<tr>
												<td>1</td>
												<td>小咪</td>
												<td>miAccount</td>
												<td>一般權限</td>
												<td>
													<button type="button"
														class="btn btn-block btn-success btn-sm">修改</button>
												</td>
												<td>
													<button type="button"
														class="btn btn-block btn-danger btn-sm">刪除</button>
												</td>
											</tr>
											<tr>
												<td>1</td>
												<td>小咪</td>
												<td>miAccount</td>
												<td>一般權限</td>
												<td>
													<button type="button"
														class="btn btn-block btn-success btn-sm">修改</button>
												</td>
												<td>
													<button type="button"
														class="btn btn-block btn-danger btn-sm">刪除</button>
												</td>
											</tr>
											<tr>
												<td>1</td>
												<td>小咪</td>
												<td>miAccount</td>
												<td>一般權限</td>
												<td>
													<button type="button"
														class="btn btn-block btn-success btn-sm">修改</button>
												</td>
												<td>
													<button type="button"
														class="btn btn-block btn-danger btn-sm">刪除</button>
												</td>
											</tr>
											<tr>
												<td>1</td>
												<td>小咪</td>
												<td>miAccount</td>
												<td>一般權限</td>
												<td>
													<button type="button"
														class="btn btn-block btn-success btn-sm">修改</button>
												</td>
												<td>
													<button type="button"
														class="btn btn-block btn-danger btn-sm">刪除</button>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->

		</div>

		<jsp:include page="../template/footer.jsp" flush="true">
			<jsp:param name="name0" value="peter0" />
		</jsp:include>

	</div>
	<!-- ./wrapper -->

	<!-- jQuery -->
	<script src="../../plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="../../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="../../dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="../../dist/js/demo.js"></script>
</body>

</html>