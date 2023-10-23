<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.newview.act.model.*"%>
<%@ page import="com.tha103.newview.act.service.*"%>
<%@ page import="com.tha103.newview.act.controller.*"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat"%>
<%@ page import="com.tha103.newview.cityaddress.model.*"%>
<%@ page import="com.tha103.newview.actcategory.model.*"%>
<%
ActDAO actDAO = new ActDAOHibernateImpl();
ActService actSvc = new ActServiceImpl(actDAO);

List<ActVO> list = actSvc.getAll();
List<ActCategory> categories = actSvc.getAllCategories();
List<CityAddress> city = actSvc.getAllCities();

pageContext.setAttribute("city", city);
pageContext.setAttribute("list", list);
pageContext.setAttribute("categories", categories);
%>

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
	href="<%=request.getContextPath()%>/Backstage/plugins/fontawesome-free/css/all.min.css">
<!-- daterange picker -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Backstage/plugins/daterangepicker/daterangepicker.css">
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
<!-- summernote -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Backstage/plugins/summernote/summernote-bs4.min.css">
<!-- CodeMirror -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/Backstage/plugins/codemirror/codemirror.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/Backstage/plugins/codemirror/theme/monokai.css">
<!-- SimpleMDE -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/Backstage/plugins/simplemde/simplemde.min.css">
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
							<h1>修改活動</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="../../index.html">後台首頁</a></li>
								<li class="breadcrumb-item active">活動管理</li>
							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>
			
			
			<section class="content">
			<c:if test="${not empty actWithPicsList}">
			<form action="<%=request.getContextPath()%>/SearchSe" method="post" enctype="multipart/form-data" accept-charset="UTF-8" onsubmit="prepareDelete();">
				
				
				<div class="row">
					<div class="col-md-10 offset-md-1">
						
						<c:forEach items="${actWithPicsList}" var="actData">
						<input type="hidden" name="actId" value="${actData.actID}" />
						
						<div class="form-group row">
							<label for="actName">*活動名稱：</label>
							<input name="actName" type="text" class="form-control" value="${actData.actName}" />
						</div>
						<div class="form-group row">
							<div class="col-md-6">
								<label for="actPrice">*活動價格：</label>
								<input name="actPrice" type="text" class="form-control" value="${actData.actPrice}" />
							</div>
							<div class="col-md-6">
								<label for="actCategory">*活動類別：</label>
								<select name="actCategory" class="form-control" style="width: 100%;">
									<c:forEach var="category" items="${categories}">
										<option value="${category.actCategoryID}">${category.actCategoryName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<label for="actTime">..活動上架日期時間：</label>
							<div class="input-group date" id="reservationdatetime" data-target-input="nearest">
								<input name="actTime" type="text" class="form-control datetimepicker-input" data-target="#reservationdatetime" />
								<div class="input-group-append" data-target="#reservationdatetime" data-toggle="datetimepicker">
									<div class="input-group-text">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-6">
								<label for="actScope">..活動規模：</label>
								<select name="actScope" class="form-control" style="width: 100%;">
									<option value="1" ${actScope.actScope == '1' ? 'selected' : ''}>大：可容納 900 人</option>
									<option value="2" ${actScope.actScope == '2' ? 'selected' : ''}>中：可容納 400 人</option>
									<option value="3" ${actScope.actScope == '3' ? 'selected' : ''}>小：可容納 100 人</option>
								</select>
							</div>
							<div class="col-md-6">
								<label for="approvalCondition">審查狀態：</label>
								<select name="approvalCondition" class="form-control" style="width: 100%;">
									<option value="1" ${actData.approvalCondition == '1' ? 'selected' : ''}>true</option>
									<option value="2" ${actData.approvalCondition == '2' ? 'selected' : ''}>false</option>
									<option value="3" ${actData.approvalCondition == '3' ? 'selected' : ''}>nothinh</option>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-4">
								<label for="cityName" >*活動縣市：</label>
								<select name="cityName" class="form-control" style="width: 100%;">
									<c:forEach var="cityItem" items="${city}">
										<option value="${cityItem.actAdressID}">${cityItem.cityName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-8">
								<label for="cityAddress">..地址：</label>
								<input name="cityAddress" type="text" class="form-control" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-6">
								<label for="actDate">*活動日期：</label>
								<input name="actDate" type="text" class="form-control"  />
							</div>
							<div class="col-md-6">
								<label for="time">*活動時間：</label> 
								<input name="time" type="text" value="${actData.time}" class="form-control"  />
							</div>
						</div>
						<div class="form-group">
							<label for="actIntroduce">*活動簡介：</label>
							<textarea name="actIntroduce" class="form-control" rows="3" placeholder="請輸入活動簡介" ></textarea>
						</div>
						<div class="form-group row">
							<label for="actImage">上傳圖片：</label> 
							<input type="file" name="actImage" id="actImageInput" accept="image/*" style="width: 100%;"/>
						</div>
						<div class="form-group">
							<label for="actContent">*活動內容：</label>
							<textarea name="actContent" class="form-control" rows="3" placeholder="請輸入活動簡介" ></textarea>
						</div>
						<!-- /.card-header -->
						<label>活動內容：</label>
						<textarea id="summernote"></textarea>
						
						<div class="form-group">
							<input type="submit" class="btn btn-primary" value="送出">
							<input type="hidden" name="action" value="addAct">
						</div>
						</c:forEach>
						
						
						
					</div>
					<!-- /.col-->
				</div>
		
				<!-- ./row -->
			
			</form>
			</c:if>
			</section>
			
			
			
		</div>

		<jsp:include page="../template/footer.jsp" flush="true">
			<jsp:param name="name0" value="peter0" />
		</jsp:include>

	</div>
	<!-- ./wrapper -->
	<script type="text/javascript">
	function prepareDelete() {
	    var selectedImages = document.getElementsByName('deleteImageCheckbox');
	    var imageIdsToDelete = [];
	    for(var i = 0; i < selectedImages.length; i++) {
	        if(selectedImages[i].checked) {
	            imageIdsToDelete.push(selectedImages[i].value);
	        }
	    }
	    document.getElementsByName('toDelete')[0].value = imageIdsToDelete.join(',');
	}

	</script>
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/Backstage/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="<%=request.getContextPath()%>/Backstage/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Select2 -->
	<script src="<%=request.getContextPath()%>/Backstage/plugins/select2/js/select2.full.min.js"></script>
	<!-- Bootstrap4 Duallistbox -->
	<script
		src="<%=request.getContextPath()%>/Backstage/plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
	<!-- InputMask -->
	<script src="<%=request.getContextPath()%>/Backstage/plugins/moment/moment.min.js"></script>
	<script src="<%=request.getContextPath()%>/Backstage/plugins/inputmask/jquery.inputmask.min.js"></script>
	<!-- date-range-picker -->
	<script src="<%=request.getContextPath()%>/Backstage/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap color picker -->
	<script
		src="<%=request.getContextPath()%>/Backstage/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script
		src="<%=request.getContextPath()%>/Backstage/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Bootstrap Switch -->
	<script src="<%=request.getContextPath()%>/Backstage/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	<!-- BS-Stepper -->
	<script src="<%=request.getContextPath()%>/Backstage/plugins/bs-stepper/js/bs-stepper.min.js"></script>
	<!-- dropzonejs -->
	<script src="<%=request.getContextPath()%>/Backstage/plugins/dropzone/min/dropzone.min.js"></script>
	<!-- AdminLTE App -->
	<script src="<%=request.getContextPath()%>/Backstage/dist/js/adminlte.min.js"></script>
	<!-- Summernote -->
	<script src="<%=request.getContextPath()%>/Backstage/plugins/summernote/summernote-bs4.min.js"></script>
	<!-- CodeMirror -->
	<script src="<%=request.getContextPath()%>/Backstage/plugins/codemirror/codemirror.js"></script>
	<script src="<%=request.getContextPath()%>/Backstage/plugins/codemirror/mode/css/css.js"></script>
	<script src="<%=request.getContextPath()%>/Backstage/plugins/codemirror/mode/xml/xml.js"></script>
	<script src="<%=request.getContextPath()%>/Backstage/plugins/codemirror/mode/htmlmixed/htmlmixed.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<%=request.getContextPath()%>/Backstage/dist/js/demo.js"></script>


	<script>
		$(function() {
			$('.select2').select2()
		});
	</script>

	<!-- Page specific script -->
	<script>
		$(function() {
			//Initialize Select2 Elements
			$('.select2').select2()

			//Initialize Select2 Elements
			$('.select2bs4').select2({
				theme : 'bootstrap4'
			})

			//Datemask dd/mm/yyyy
			$('#datemask').inputmask('dd/mm/yyyy', {
				'placeholder' : 'dd/mm/yyyy'
			})
			//Datemask2 mm/dd/yyyy
			$('#datemask2').inputmask('mm/dd/yyyy', {
				'placeholder' : 'mm/dd/yyyy'
			})
			//Money Euro
			$('[data-mask]').inputmask()

			//Date picker
			$('#reservationdate').datetimepicker({
				format : 'L'
			});

			//Date and time picker
			$('#reservationdatetime').datetimepicker({
				icons : {
					time : 'far fa-clock'
				}
			});

			//Date range picker
			$('#reservation').daterangepicker()
			//Date range picker with time picker
			$('#reservationtime').daterangepicker({
				timePicker : true,
				timePickerIncrement : 30,
				locale : {
					format : 'MM/DD/YYYY hh:mm A'
				}
			})
			//Date range as a button
			$('#daterange-btn').daterangepicker(
					{
						ranges : {
							'Today' : [ moment(), moment() ],
							'Yesterday' : [ moment().subtract(1, 'days'),
									moment().subtract(1, 'days') ],
							'Last 7 Days' : [ moment().subtract(6, 'days'),
									moment() ],
							'Last 30 Days' : [ moment().subtract(29, 'days'),
									moment() ],
							'This Month' : [ moment().startOf('month'),
									moment().endOf('month') ],
							'Last Month' : [
									moment().subtract(1, 'month').startOf(
											'month'),
									moment().subtract(1, 'month')
											.endOf('month') ]
						},
						startDate : moment().subtract(29, 'days'),
						endDate : moment()
					},
					function(start, end) {
						$('#reportrange span').html(
								start.format('MMMM D, YYYY') + ' - '
										+ end.format('MMMM D, YYYY'))
					})

			//Timepicker
			$('#timepicker').datetimepicker({
				format : 'LT'
			})

		})

		// Setup the buttons for all transfers
		// The "add files" button doesn't need to be setup because the config
		// `clickable` has already been specified.
		document.querySelector("#actions .start").onclick = function() {
			myDropzone.enqueueFiles(myDropzone
					.getFilesWithStatus(Dropzone.ADDED))
		}
		document.querySelector("#actions .cancel").onclick = function() {
			myDropzone.removeAllFiles(true)
		}
		// DropzoneJS Demo Code End
	</script>
	<script>
		$(function() {
			// Summernote
			$('#summernote').summernote({
				height : 400
			});
		})
	</script>
		<script>
		<c:forEach var="actData" items="${list}">
		var actID = "${actData.actID}";
		var imageUrl = "${pageContext.request.contextPath}/GetImageFromDB?actID="
				+ actID;
		var imageContainer = document.getElementById("imageContainer_" + actID);
		var actImage = document.getElementById("actImage_" + actID);

		actImage.src = imageUrl;
		</c:forEach>
	</script>
</body>

</html>