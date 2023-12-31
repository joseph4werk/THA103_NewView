<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	

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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/Backstage/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/Backstage/dist/css/adminlte.min.css">
</head>

<body class="hold-transition sidebar-mini">
  <div class="wrapper">
    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
      <!-- Left navbar links -->
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" data-widget="pushmenu" href="<%=request.getContextPath()%>/Backstage/pub-index.jsp" role="button"><i class="fas fa-bars"></i></a>
        </li>
      </ul>

      <!-- Right navbar links -->
      <ul class="navbar-nav ml-auto">
        <!-- Messages Dropdown Menu -->
        <li class="nav-item dropdown">
        
<%--           <a class="nav-link" data-toggle="dropdown" href="<%=request.getContextPath()%>/Backstage/pub-index.jsp"> --%>
<!--             <i class="far fa-comments"></i> -->
<!--             <span class="badge badge-danger navbar-badge">3</span> -->
<!--           </a> -->

<!--           <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right"> -->
<!--             <a href="#" class="dropdown-item"> -->
<!--               Message Start -->
<!--               <div class="media"> -->
<!--                 <img src="../dist/img/user1-128x128.jpg" alt="User Avatar" class="img-size-50 mr-3 img-circle"> -->
<!--                 <div class="media-body"> -->
<!--                   <h3 class="dropdown-item-title"> -->
<!--                     Brad Diesel -->
<!--                     <span class="float-right text-sm text-danger"><i class="fas fa-star"></i></span> -->
<!--                   </h3> -->
<!--                   <p class="text-sm">Call me whenever you can...</p> -->
<!--                   <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p> -->
<!--                 </div> -->
<!--               </div> -->
<!--               Message End -->
<!--             </a> -->
 
<!--             <div class="dropdown-divider"></div> -->
<!--             <a href="#" class="dropdown-item dropdown-footer">See All Messages</a> -->
<!--           </div> -->
        </li>
        <!-- Notifications Dropdown Menu -->
        <li class="nav-item dropdown">
<!--           <a class="nav-link" data-toggle="dropdown" href="#"> -->
<!--             <i class="far fa-bell"></i> -->
<!--             <span class="badge badge-warning navbar-badge">15</span> -->
<!--           </a> -->
          
<!--           <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right"> -->
<!--             <div class="dropdown-divider"></div> -->
<!--             <a href="#" class="dropdown-item"> -->
<!--               <i class="fas fa-file mr-2"></i> 3 new reports -->
<!--               <span class="float-right text-muted text-sm">2 days</span> -->
<!--             </a> -->
<!--             <div class="dropdown-divider"></div> -->
<!--             <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a> -->
<!--           </div> -->
        </li>
        <li class="nav-item">
          <a class="nav-link" data-widget="fullscreen" href="#" role="button">
            <i class="fas fa-expand-arrows-alt"></i>
          </a>
        </li>
      </ul>
    </nav>
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <aside class="main-sidebar sidebar-dark-primary elevation-4">
      <!-- Brand Logo -->
		<a href="<%=request.getContextPath()%>/Backstage/Allpage-publisher/pub-index.jsp" class="brand-link"> <img
			src="<%=request.getContextPath()%>/Backstage/dist/img/AdminLTELogo.png" alt="AdminLTE Logo"
			class="brand-image img-circle elevation-3" style="opacity: .8">
			<span class="brand-text font-weight-light">NewView</span>
		</a>


      <!-- Sidebar -->
      <div class="sidebar">
        <!-- Sidebar user (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
          <div class="image">
<!--             <img src="../dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image"> -->
          </div>
          <div class="info">
            <a href="#" class="d-block">廠商後台管理員</a>
          </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
          <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
            <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="nav-icon far fa-calendar-alt"></i>
                <p>
                  活動管理
                  <i class="right fas fa-angle-left"></i>
                </p>
              </a>
              <ul class="nav nav-treeview">
                <li class="nav-item">
                  <a href="<%=request.getContextPath()%>/Backstage/Allpage-publisher/activity/activity-list.jsp" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>活動列表</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="<%=request.getContextPath()%>/Backstage/Allpage-publisher/activity/activity-shelves.jsp" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>活動上架</p>
                  </a>
                </li>
              </ul>
            </li>
            <li class="nav-item">
              <a href="<%=request.getContextPath()%>/Backstage/Allpage-publisher/order/order-list.jsp" class="nav-link">
                <i class="nav-icon fas fa-file"></i>
                <p>訂單管理</p>
              </a>
            </li>
            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="nav-icon fas fa-tree"></i>
                <p>
                  優惠管理
                  <i class="fas fa-angle-left right"></i>
                </p>
              </a>
              <ul class="nav nav-treeview">
                <li class="nav-item">
                  <a href="<%=request.getContextPath()%>/Backstage/Allpage-publisher/discount/discount-list.jsp" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>優惠列表</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="<%=request.getContextPath()%>/Backstage/Allpage-publisher/discount/discount-add.jsp" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>新增優惠</p>
                  </a>
                </li>
              </ul>
            </li>
            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="nav-icon fas fa-edit"></i>
                <p>
                  權限管理
                  <i class="fas fa-angle-left right"></i>
                </p>
              </a>
              <ul class="nav nav-treeview">
                <li class="nav-item">
                  <a href="<%=request.getContextPath()%>/Backstage/Allpage-publisher/user/user-listAll.jsp" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>使用者列表</p>
                  </a>
                </li>
                <li class="nav-item">
                  <a href="<%=request.getContextPath()%>/Backstage/Allpage-publisher/user/user-add.jsp" class="nav-link">
                    <i class="far fa-circle nav-icon"></i>
                    <p>新增使用者</p>
                  </a>
                </li>
              </ul>
            </li>
				<form method="post" action="<%=request.getContextPath()%>/pubuser/pubuser.do">	
						<li class="nav-item">
							<div class="nav-link"> 
								<button type="submit" class="btn btn-block btn-light btn-sm">登出</button>
								<input type="hidden" name="action" value="logout" />
							</div>
						</li>
					</form>
          </ul>
        </nav>
        <!-- /.sidebar-menu -->
      </div>
      <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
      <!-- Content Header (Page header) -->
      <!-- Main content -->
      
      <!-- /.Main content -->
      <!-- /.content Header-->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
      <div class="float-right d-none d-sm-block">
          <b>Version</b> 1.0.0
      </div>
      <strong>Copyright &copy; 2023~ <a href="https://newview.com">NewView</a>.</strong> All rights
      reserved.
  </footer>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
      <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->
  </div>
  <!-- ./wrapper -->

  <!-- jQuery -->
  <script src="<%=request.getContextPath()%>/Backstage/plugins/jquery/jquery.min.js"></script>
  <!-- Bootstrap 4 -->
  <script src="<%=request.getContextPath()%>/Backstage/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- AdminLTE App -->
  <script src="<%=request.getContextPath()%>/Backstage/dist/js/adminlte.min.js"></script>
  <!-- AdminLTE for demo purposes -->
  <script src="<%=request.getContextPath()%>/Backstage/dist/js/demo.js"></script>
</body>

</html>