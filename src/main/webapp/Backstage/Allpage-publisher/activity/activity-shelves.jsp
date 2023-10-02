<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<!-- daterange picker -->
<link rel="stylesheet"
	href="../../plugins/daterangepicker/daterangepicker.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="../../plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- Select2 -->
<link rel="stylesheet" href="../../plugins/select2/css/select2.min.css">
<!-- Bootstrap4 Duallistbox -->
<link rel="stylesheet"
	href="../../plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
<!-- BS Stepper -->
<link rel="stylesheet"
	href="../../plugins/bs-stepper/css/bs-stepper.min.css">
<!-- dropzonejs -->
<link rel="stylesheet"
	href="../../plugins/dropzone/min/dropzone.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../../dist/css/adminlte.min.css">
<!-- summernote -->
<link rel="stylesheet"
	href="../../plugins/summernote/summernote-bs4.min.css">
<!-- CodeMirror -->
<link rel="stylesheet" href="../../plugins/codemirror/codemirror.css">
<link rel="stylesheet" href="../../plugins/codemirror/theme/monokai.css">
<!-- SimpleMDE -->
<link rel="stylesheet" href="../../plugins/simplemde/simplemde.min.css">
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
							<h1>活動上/下架</h1>
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
				<div class="row">
					<div class="col-md-10 offset-md-1">
						<div class="form-group row">
							<div class="col-md-6">
								<label for="inputPassword">活動類別：</label> <select
									class="form-control" style="width: 100%;">
									<option>脫口秀</option>
									<option>演講</option>
									<option>音樂劇</option>
									<option>舞台劇</option>
									<option>演唱會</option>
								</select>
							</div>
							<div class="col-md-6">
								<label for="inputPassword">活動規模：</label> <select
									class="form-control" style="width: 100%;">
									<option>大：可容納 900 人</option>
									<option>中：可容納 400 人</option>
									<option>小：可容納 100 人</option>
								</select>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-md-6">
								<label for="inputActPrice">活動價格：</label> <input type="text"
									id="inputActPrice" class="form-control" />
							</div>
							<div class="col-md-6">
								<label>活動日期時間：</label>
								<div class="input-group date" id="reservationdatetime"
									data-target-input="nearest">
									<input type="text" class="form-control datetimepicker-input"
										data-target="#reservationdatetime" />
									<div class="input-group-append"
										data-target="#reservationdatetime"
										data-toggle="datetimepicker">
										<div class="input-group-text">
											<i class="fa fa-calendar"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="inputActName">活動名稱：</label> <input type="text"
								id="inputActName" class="form-control" />
						</div>
						<div class="form-group row">
							<div class="col-md-4">
								<label>活動縣市：</label> <select class="form-control"
									style="width: 100%;">
									<option value="1">基隆市</option>
									<option value="2">台北市</option>
									<option value="3">新北市</option>
									<option value="4">桃園縣</option>
									<option value="5">新竹市</option>
									<option value="6">新竹縣</option>
									<option value="7">苗栗縣</option>
									<option value="8">台中市</option>
									<option value="9">彰化縣</option>
									<option value="10">南投縣</option>
									<option value="11">雲林縣</option>
									<option value="12">嘉義市</option>
									<option value="13">嘉義縣</option>
									<option value="14">台南市</option>
									<option value="15">高雄市</option>
									<option value="16">屏東縣</option>
									<option value="17">台東縣</option>
									<option value="18">花蓮縣</option>
									<option value="19">宜蘭縣</option>
									<option value="20">澎湖縣</option>
									<option value="21">金門縣</option>
									<option value="22">連江縣</option>
								</select>
							</div>
							<div class="col-md-8">
								<label for="inputNickname">地址：</label> <input type="text"
									id="inputNickname" class="form-control" />
							</div>
						</div>
						<!-- Date range-->
						<div class="form-group">
							<label>活動上架期間：</label>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i
										class="far fa-calendar-alt"></i>
									</span>
								</div>
								<input type="text" class="form-control float-right"
									id="reservation">
							</div>
						</div>
						<div class="form-group">
							<label>活動簡介：</label>
							<textarea class="form-control" rows="3" placeholder="請輸入活動簡介"></textarea>
						</div>
						<!-- /.card-header -->
						<label>活動內容：</label>
						<textarea id="summernote"></textarea>
						<div class="form-group">
							<input type="submit" class="btn btn-primary" value="送出">
							<input type="submit" class="btn btn-danger" value="清空">
						</div>
					</div>
					<!-- /.col-->
				</div>
				<!-- ./row -->
			</section>
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
	<!-- Select2 -->
	<script src="../../plugins/select2/js/select2.full.min.js"></script>
	<!-- Bootstrap4 Duallistbox -->
	<script
		src="../../plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
	<!-- InputMask -->
	<script src="../../plugins/moment/moment.min.js"></script>
	<script src="../../plugins/inputmask/jquery.inputmask.min.js"></script>
	<!-- date-range-picker -->
	<script src="../../plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap color picker -->
	<script
		src="../../plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script
		src="../../plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Bootstrap Switch -->
	<script src="../../plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	<!-- BS-Stepper -->
	<script src="../../plugins/bs-stepper/js/bs-stepper.min.js"></script>
	<!-- dropzonejs -->
	<script src="../../plugins/dropzone/min/dropzone.min.js"></script>
	<!-- AdminLTE App -->
	<script src="../../dist/js/adminlte.min.js"></script>
	<!-- Summernote -->
	<script src="../../plugins/summernote/summernote-bs4.min.js"></script>
	<!-- CodeMirror -->
	<script src="../../plugins/codemirror/codemirror.js"></script>
	<script src="../../plugins/codemirror/mode/css/css.js"></script>
	<script src="../../plugins/codemirror/mode/xml/xml.js"></script>
	<script src="../../plugins/codemirror/mode/htmlmixed/htmlmixed.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="../../dist/js/demo.js"></script>


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
</body>

</html>