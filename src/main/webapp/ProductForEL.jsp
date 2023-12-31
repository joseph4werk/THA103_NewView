<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tha103.newview.act.model.*"%>
<%@ page import="com.tha103.newview.act.service.*"%>
<%@ page import="com.tha103.newview.actcategory.model.*"%>
<%@ page import="com.tha103.newview.cityaddress.model.*"%>
<%@ page import="com.tha103.newview.publisher.model.*" %>
<%@ page import="com.tha103.newview.act.controller.*" %>
<%
ActService actSvc = new ActServiceImpl();
List<ActVO> list = actSvc.getAll();
List<ActCategory> categories = actSvc.getAllCategories();
List<CityAddress> city = actSvc.getAllCities();
pageContext.setAttribute("city", city);
pageContext.setAttribute("list", list);
pageContext.setAttribute("categories", categories);

%>
<html lang="en">
  <head>
    <title>Product</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.png" />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="vendor/bootstrap/css/bootstrap.min.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="fonts/font-awesome-4.7.0/css/font-awesome.min.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="fonts/iconic/css/material-design-iconic-font.min.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="fonts/linearicons-v1.0.0/icon-font.min.css"
    />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css" />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="vendor/css-hamburgers/hamburgers.min.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="vendor/animsition/css/animsition.min.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="vendor/select2/select2.min.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="vendor/daterangepicker/daterangepicker.css"
    />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/slick/slick.css" />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="vendor/MagnificPopup/magnific-popup.css"
    />
    <!--===============================================================================================-->
    <link
      rel="stylesheet"
      type="text/css"
      href="vendor/perfect-scrollbar/perfect-scrollbar.css"
    />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css" />
    <link rel="stylesheet" type="text/css" href="css/main.css" />
    <style>
      #act_date {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 100%;
        border: 1px solid #ccc;
        background-color: #fff;
      }
      .seatsTry a {
        padding: 15px;
        padding-left: 35px;
        padding-right: 35px;
        border-radius: 23px;
        color: #fff;
      }

      .seatsTry {
        border: 1px solid #f4f4f4;
        background-color: #717fe0;
        border-radius: 23px;
        min-width: 161px;
        height: 46px;
      }
      .seatsTry:hover {
        border: 1px solid #ebe8ef;
        background-color: #cb476a;
        color: aliceblue;
        border-radius: 23px;
        min-width: 161px;
        height: 46px;
      }
    </style>
    <!--===============================================================================================-->
  </head>
  <body class="animsition">
    <!-- Header -->
    <header class="header-v4">
      <!-- Header desktop -->
      <div class="container-menu-desktop">
        <!-- Topbar -->
        <div class="top-bar">
          <div class="content-topbar flex-sb-m h-full container">
            <div class="left-top-bar">NewView</div>

            <div class="right-top-bar flex-w h-full">
              <a href="#" class="flex-c-m trans-04 p-lr-25"> My Account </a>
            </div>
          </div>
        </div>

        <div class="wrap-menu-desktop how-shadow1">
          <nav class="limiter-menu-desktop container">
            <!-- Logo desktop -->
            <!-- <a href="#" class="logo">
              <img src="images/icons/LOGO.png" alt="IMG-LOGO" />
            </a> -->

            <!-- Menu desktop -->
            <div class="menu-desktop">
              <ul class="main-menu">
                <li>
                  <a href="home-03.html">主頁</a>
                  <ul class="sub-menu"></ul>
                </li>

                <li class="active-menu">
                  <a href="product.html">活動專區</a>
                </li>

                <li class="label1" data-label1="hot">
                  <a href="shoping-cart.html">購物車</a>
                </li>

                <li>
                  <a href="blog.html">討論區</a>
                </li>

                <li>
                  <a href="about.html">關於我們</a>
                </li>

                <li>
                  <a href="contact.html">聯絡我們</a>
                </li>
              </ul>
            </div>

            <!-- Icon header -->
            <div class="wrap-icon-header flex-w flex-r-m">
              <div
                class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 js-show-modal-search"
              >
                <i class="zmdi zmdi-search"></i>
              </div>

              <div
                class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart"
                data-notify="2"
              >
                <i class="zmdi zmdi-shopping-cart"></i>
              </div>

              <a
                href="#"
                class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti"
                data-notify="0"
              >
                <i class="zmdi zmdi-favorite-outline"></i>
              </a>
            </div>
          </nav>
        </div>
      </div>

      <!-- Header Mobile -->
      <div class="wrap-header-mobile">
        <!-- Logo moblie -->
        <div class="logo-mobile">
          <a href="index.html"
            ><img src="images/icons/logo-01.png" alt="IMG-LOGO"
          /></a>
        </div>

        <!-- Icon header -->
        <div class="wrap-icon-header flex-w flex-r-m m-r-15">
          <div
            class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 js-show-modal-search"
          >
            <i class="zmdi zmdi-search"></i>
          </div>

          <div
            class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti js-show-cart"
            data-notify="2"
          >
            <i class="zmdi zmdi-shopping-cart"></i>
          </div>

          <a
            href="#"
            class="dis-block icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti"
            data-notify="0"
          >
            <i class="zmdi zmdi-favorite-outline"></i>
          </a>
        </div>

        <!-- Button show menu -->
        <div class="btn-show-menu-mobile hamburger hamburger--squeeze">
          <span class="hamburger-box">
            <span class="hamburger-inner"></span>
          </span>
        </div>
      </div>

      <!-- Menu Mobile -->
      <div class="menu-mobile">
        <ul class="topbar-mobile">
          <li>
            <div class="left-top-bar">
              Free shipping for standard order over $100
            </div>
          </li>

          <li>
            <div class="right-top-bar flex-w h-full">
              <a href="#" class="flex-c-m p-lr-10 trans-04"> Help & FAQs </a>

              <a href="#" class="flex-c-m p-lr-10 trans-04"> My Account </a>

              <a href="#" class="flex-c-m p-lr-10 trans-04"> EN </a>

              <a href="#" class="flex-c-m p-lr-10 trans-04"> USD </a>
            </div>
          </li>
        </ul>

        <ul class="main-menu-m">
          <li>
            <a href="index.html">Home</a>
            <ul class="sub-menu-m">
              <li><a href="index.html">Homepage 1</a></li>
              <li><a href="home-02.html">Homepage 2</a></li>
              <li><a href="home-03.html">Homepage 3</a></li>
            </ul>
            <span class="arrow-main-menu-m">
              <i class="fa fa-angle-right" aria-hidden="true"></i>
            </span>
          </li>

          <li>
            <a href="product.html">Shop</a>
          </li>

          <li>
            <a href="shoping-cart.html" class="label1 rs1" data-label1="hot"
              >Features</a
            >
          </li>

          <li>
            <a href="blog.html">Blog</a>
          </li>

          <li>
            <a href="about.html">About</a>
          </li>

          <li>
            <a href="contact.html">Contact</a>
          </li>
        </ul>
      </div>

      <!-- Modal Search -->
      <div class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
        <div class="container-search-header">
          <button
            class="flex-c-m btn-hide-modal-search trans-04 js-hide-modal-search"
          >
            <img src="images/icons/icon-close2.png" alt="CLOSE" />
          </button>

          <form class="wrap-search-header flex-w p-l-15">
            <button class="flex-c-m trans-04">
              <i class="zmdi zmdi-search"></i>
            </button>
            <input
              class="plh3"
              type="text"
              name="search"
              placeholder="Search..."
            />
          </form>
        </div>
      </div>
    </header>

    <!-- Cart -->
    <div class="wrap-header-cart js-panel-cart">
      <div class="s-full js-hide-cart"></div>

      <div class="header-cart flex-col-l p-l-65 p-r-25">
        <div class="header-cart-title flex-w flex-sb-m p-b-8">
          <span class="mtext-103 cl2"> Your Cart </span>

          <div
            class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart"
          >
            <i class="zmdi zmdi-close"></i>
          </div>
        </div>

        <div class="header-cart-content flex-w js-pscroll">
          <ul class="header-cart-wrapitem w-full">
            <li class="header-cart-item flex-w flex-t m-b-12">
              <div class="header-cart-item-img">
                <img src="images/item-cart-01.jpg" alt="IMG" />
              </div>

              <div class="header-cart-item-txt p-t-8">
                <a
                  href="#"
                  class="header-cart-item-name m-b-18 hov-cl1 trans-04"
                >
                  White Shirt Pleat
                </a>

                <span class="header-cart-item-info"> 1 x $19.00 </span>
              </div>
            </li>

            <li class="header-cart-item flex-w flex-t m-b-12">
              <div class="header-cart-item-img">
                <img src="images/item-cart-02.jpg" alt="IMG" />
              </div>

              <div class="header-cart-item-txt p-t-8">
                <a
                  href="#"
                  class="header-cart-item-name m-b-18 hov-cl1 trans-04"
                >
                  Converse All Star
                </a>

                <span class="header-cart-item-info"> 1 x $39.00 </span>
              </div>
            </li>

            <li class="header-cart-item flex-w flex-t m-b-12">
              <div class="header-cart-item-img">
                <img src="images/item-cart-03.jpg" alt="IMG" />
              </div>

              <div class="header-cart-item-txt p-t-8">
                <a
                  href="#"
                  class="header-cart-item-name m-b-18 hov-cl1 trans-04"
                >
                  Nixon Porter Leather
                </a>

                <span class="header-cart-item-info"> 1 x $17.00 </span>
              </div>
            </li>
          </ul>

          <div class="w-full">
            <div class="header-cart-total w-full p-tb-40">Total: $75.00</div>

            <div class="header-cart-buttons flex-w w-full">
              <a
                href="shoping-cart.html"
                class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10"
              >
                View Cart
              </a>

              <a
                href="shoping-cart.html"
                class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-b-10"
              >
                Check Out
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Product -->
    <div class="bg0 m-t-23 p-b-140">
      <div class="container">
        <div class="flex-w flex-sb-m p-b-52">
          <div class="flex-w flex-l-m filter-tope-group m-tb-10">
            <button
              class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1"
              data-filter="*"
            >
              所有活動
            </button>

            <button
              class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5"
              data-filter=".women"
            >
              脫口秀
            </button>

            <button
              class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5"
              data-filter=".men"
            >
              講座
            </button>

            <button
              class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5"
              data-filter=".bag"
            >
              音樂會
            </button>

            <button
              class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5"
              data-filter=".shoes"
            >
              舞台劇
            </button>

            <button
              class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5"
              data-filter=".watches"
            >
              演唱會
            </button>
          </div>

          <div class="flex-w flex-c-m m-tb-10">
            <div
              class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4 js-show-filter"
            >
              <i
                class="icon-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-filter-list"
              ></i>
              <i
                class="icon-close-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"
              ></i>
              Filter
            </div>

            <div
              class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-tb-4 js-show-search"
            >
              <i
                class="icon-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-search"
              ></i>
              <i
                class="icon-close-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"
              ></i>
              Search
            </div>
          </div>

          <!-- Search product -->
          <div class="dis-none panel-search w-full p-t-10 p-b-15">
            <div class="bor8 dis-flex p-l-15">
              <button class="size-113 flex-c-m fs-16 cl2 hov-cl1 trans-04">
                <i class="zmdi zmdi-search"></i>
              </button>

              <input
                class="mtext-107 cl2 size-114 plh2 p-r-15"
                type="text"
                name="search-product"
                placeholder="Search"
              />
            </div>
          </div>

          <!-- Filter -->
          <div class="dis-none panel-filter w-full p-t-10">
            <div
              class="wrap-filter flex-w bg6 w-full p-lr-40 p-t-27 p-lr-15-sm"
            >
              <div class="filter-col1 p-r-15 p-b-27">
                <div class="mtext-102 cl2 p-b-15">Sort By</div>

                <ul>
                  <li class="p-b-6">
                    <a href="#" class="filter-link stext-106 trans-04">
                      Default
                    </a>
                  </li>

                  <li class="p-b-6">
                    <a href="#" class="filter-link stext-106 trans-04">
                      Popularity
                    </a>
                  </li>

                  <li class="p-b-6">
                    <a href="#" class="filter-link stext-106 trans-04">
                      Average rating
                    </a>
                  </li>

                  <li class="p-b-6">
                    <a
                      href="#"
                      class="filter-link stext-106 trans-04 filter-link-active"
                    >
                      Newness
                    </a>
                  </li>

                  <li class="p-b-6">
                    <a href="#" class="filter-link stext-106 trans-04">
                      Price: Low to High
                    </a>
                  </li>

                  <li class="p-b-6">
                    <a href="#" class="filter-link stext-106 trans-04">
                      Price: High to Low
                    </a>
                  </li>
                </ul>
              </div>

              <div class="filter-col2 p-r-15 p-b-27">
                <div class="mtext-102 cl2 p-b-15">Price</div>

                <ul>
                  <li class="p-b-6">
                    <a
                      href="#"
                      class="filter-link stext-106 trans-04 filter-link-active"
                    >
                      All
                    </a>
                  </li>

                  <li class="p-b-6">
                    <a href="#" class="filter-link stext-106 trans-04">
                      $0.00 - $50.00
                    </a>
                  </li>

                  <li class="p-b-6">
                    <a href="#" class="filter-link stext-106 trans-04">
                      $50.00 - $100.00
                    </a>
                  </li>

                  <li class="p-b-6">
                    <a href="#" class="filter-link stext-106 trans-04">
                      $100.00 - $150.00
                    </a>
                  </li>

                  <li class="p-b-6">
                    <a href="#" class="filter-link stext-106 trans-04">
                      $150.00 - $200.00
                    </a>
                  </li>

                  <li class="p-b-6">
                    <a href="#" class="filter-link stext-106 trans-04">
                      $200.00+
                    </a>
                  </li>
                </ul>
              </div>

              <div class="filter-col3 p-r-15 p-b-27">
                <div class="mtext-102 cl2 p-b-15">Color</div>

                <ul>
                  <li class="p-b-6">
                    <span class="fs-15 lh-12 m-r-6" style="color: #222">
                      <i class="zmdi zmdi-circle"></i>
                    </span>

                    <a href="#" class="filter-link stext-106 trans-04">
                      Black
                    </a>
                  </li>

                  <li class="p-b-6">
                    <span class="fs-15 lh-12 m-r-6" style="color: #4272d7">
                      <i class="zmdi zmdi-circle"></i>
                    </span>

                    <a
                      href="#"
                      class="filter-link stext-106 trans-04 filter-link-active"
                    >
                      Blue
                    </a>
                  </li>

                  <li class="p-b-6">
                    <span class="fs-15 lh-12 m-r-6" style="color: #b3b3b3">
                      <i class="zmdi zmdi-circle"></i>
                    </span>

                    <a href="#" class="filter-link stext-106 trans-04">
                      Grey
                    </a>
                  </li>

                  <li class="p-b-6">
                    <span class="fs-15 lh-12 m-r-6" style="color: #00ad5f">
                      <i class="zmdi zmdi-circle"></i>
                    </span>

                    <a href="#" class="filter-link stext-106 trans-04">
                      Green
                    </a>
                  </li>

                  <li class="p-b-6">
                    <span class="fs-15 lh-12 m-r-6" style="color: #fa4251">
                      <i class="zmdi zmdi-circle"></i>
                    </span>

                    <a href="#" class="filter-link stext-106 trans-04"> Red </a>
                  </li>

                  <li class="p-b-6">
                    <span class="fs-15 lh-12 m-r-6" style="color: #aaa">
                      <i class="zmdi zmdi-circle-o"></i>
                    </span>

                    <a href="#" class="filter-link stext-106 trans-04">
                      White
                    </a>
                  </li>
                </ul>
              </div>

              <div class="filter-col4 p-b-27">
                <div class="mtext-102 cl2 p-b-15">Tags</div>

                <div class="flex-w p-t-4 m-r--5">
                  <a
                    href="#"
                    class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5"
                  >
                    Fashion
                  </a>

                  <a
                    href="#"
                    class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5"
                  >
                    Lifestyle
                  </a>

                  <a
                    href="#"
                    class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5"
                  >
                    Denim
                  </a>

                  <a
                    href="#"
                    class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5"
                  >
                    Streetstyle
                  </a>

                  <a
                    href="#"
                    class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5"
                  >
                    Crafts
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="row isotope-grid">
          <!--動態增加測試-->
          <div id="city-container">
            <!-- 初始空值 -->
          </div>
          <!--動態增加測試-->

          <!--往下一格活動區-->
          <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item women">
            <!-- Block2 -->
            <div class="block2">
              <div class="block2-pic hov-img0">
                <img
                  src="images/icons/iStock-831601850.jpg"
                  alt="IMG-PRODUCT"
                />

                <a
                  href="#"
                  class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1"
                >
                  活動詳細
                </a>
              </div>

              <div class="block2-txt flex-w flex-t p-t-14">
                <p style="font-size: 11px">活動日期:2023.12.12(一)8:00</p>

                <div class="block2-txt-child1 flex-col-l">
                  <div style="width: 100%; overflow: hidden">
                    <div style="float: left">
                      <a
                        href="product-detail.html"
                        class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6"
                      >
                        誰的脫口秀
                      </a>
                    </div>
                    <div style="float: right">
                      <span class="stext-105 cl3"> TWD 300 </span>
                    </div>
                  </div>
                  <hr
                    style="margin-top: 0"
                    size="8px"
                    align="center"
                    width="100%"
                  />
                  <div style="width: 100%; overflow: hidden">
                    <div style="float: left">
                      <a
                        href="https://www.google.com/maps/@25.0356163,121.4798943,15z?authuser=0&entry=ttu"
                      >
                        <img
                          src="./images/icons/iStock-902788474 (1).png"
                          alt=""
                        />
                        台北市</a
                      >
                    </div>
                    <div
                      style="float: right"
                      class="block2-txt-child2 flex-r p-t-3"
                    >
                      <a
                        href="#"
                        class="btn-addwish-b2 dis-block pos-relative js-addwish-b2"
                      >
                        <img
                          class="icon-heart1 dis-block trans-04"
                          src="images/icons/icon-heart-01.png"
                          alt="ICON"
                        />

                        <img
                          class="icon-heart2 dis-block trans-04 ab-t-l"
                          src="images/icons/icon-heart-02.png"
                          alt="ICON"
                        />
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!--往上一格活動區-->
          <!--動態測試-->

          <div id="act-container">
            <!-- 活動空表格 -->
          </div>

          <script>
           
            //--------時間格式切割方法----------
            
            //--------時間格式切割方法----------
<%
request.setCharacterEncoding("UTF-8");
%>

<c:forEach var="actData" items="${list}">
            function addAct(
            		${actData.actName},
            		${actData.actPrice},
            		 ${actData.actCategory.actCategoryName},
            		 ${actData.cityAddressID.cityName},
            		 ${actData.actScope},
              imageUrl,
              cataClass
            ) {
              var actContainer = document.getElementById("act-container");

              var actElement = document.createElement("div");
              actElement.className =
                "col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item " +
                cataClass +
                " block2";

              var formattedDateTime = formatActivityDateTime(activityDateTime);

              actElement.innerHTML = `
              <!-- Block2 -->
              <div class="block2">
                <div class="block2-pic hov-img0">
                  <img src="${imageUrl}" alt="IMG-PRODUCT" />
                  <a href="${activityLink}" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1">
                    活動詳細
                  </a>
                </div>

                <div class="block2-txt flex-w flex-t p-t-14">
                  <p style="font-size: 11px" id="activity-datetime">
                    活動日期: ${formattedDateTime}
                  </p>

                  <div class="block2-txt-child1 flex-col-l">
                    <div style="width: 100%; overflow: hidden">
                      <div style="float: left">
                        <a href="product-detail.html" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6" id="activity-name">
                          ${activityName}
                        </a>
                      </div>
                      <div style="float: right">
                        <span class="stext-105 cl3" id="activity-price">
                          TWD ${activityPrice}
                        </span>
                      </div>
                    </div>
                    <hr style="margin-top: 0" size="8px" align="center" width="100%" />
                    <div style="width: 100%; overflow: hidden">
                      <div style="float: left">
                        <a href="${activityLink}">
                          <img src="./images/icons/iStock-902788474 (1).png" alt="" />
                          ${activityAddress}
                        </a>
                      </div>
                      <div style="float: right" class="block2-txt-child2 flex-r p-t-3">
                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                          <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON" />
                          <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON" />
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            `;

              actContainer.appendChild(actElement);
            }
            </c:forEach>
            //------------------------TEST-------------------------------
            //數值之後改動態
            // var userActivityName = "天啊";
            // var userActivityPrice = "500";
            // var userActivityDateTime = "2023.09.18 12:00";
            // var userActivityAddress = "新北市";
            // var userActivityLink =
            //   "https://www.google.com/maps/place/%E8%A1%9B%E7%94%9F%E7%A6%8F%E5%88%A9%E9%83%A8%E8%87%BA%E5%8C%97%E9%86%AB%E9%99%A2/@25.0428811,121.4388424,14z/data=!3m1!5s0x3442a8651eb53393:0x60d99ab8a7d7a776!4m6!3m5!1s0x3442a86501f2f86d:0x8c42b695a045371f!8m2!3d25.042883!4d121.4594403!16s%2Fg%2F12nvp6pf9?authuser=0&entry=ttu"; // Replace with the actual link
            // var userImageUrl = "images/icons/aazt4-zd3be.png";
            // //--------------------判斷資料有沒有齊全---------------------
            // if (
            //   userActivityName &&
            //   userActivityPrice &&
            //   userActivityDateTime &&
            //   userActivityAddress &&
            //   userActivityLink &&
            //   userImageUrl
            // ) {
            //   addAct(
            //     userActivityName,
            //     userActivityPrice,
            //     userActivityDateTime,
            //     userActivityAddress,
            //     userActivityLink,
            //     userImageUrl
            //   );
            // }
            //--------------每多一筆就多一格-------------------
            var activityData = [
              {
                name: "我好爛",
                price: "100",
                dateTime: "2023.09.19 14:00",
                address: "台北市",
                link: "https://www.example.com/activity1",
                imageUrl: "images/icons/aazt4-zd3be.png",
                cataClass: "women",
              },
              {
                name: "WebSocket 到底怎麼用",
                price: "50",
                dateTime: "2023.09.20 10:30",
                address: "新竹市",
                link: "https://www.example.com/activity2",
                imageUrl: "images/icons/aazt4-zd3be.png",
                cataClass: "men",
              },
              // 繼續添加
            ];

            // 使用迴圈將每筆資料新增到頁面
            for (var i = 0; i < activityData.length; i++) {
              var data = activityData[i];
              addAct(
                data.name,
                data.price,
                data.dateTime,
                data.address,
                data.link,
                data.imageUrl,
                data.cataClass
              );
            }
            //------------------------TEST-------------------------------
          </script>
          <!--動態測試-->
          <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item bag">
            <!-- Block2 -->
            <div class="block2">
              <div class="block2-pic hov-img0">
                <img src="images/iStock-489518962.jpg" alt="IMG-PRODUCT" />

                <a
                  href="#"
                  class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1"
                >
                  活動詳細
                </a>
              </div>

              <div class="block2-txt flex-w flex-t p-t-14">
                <p style="font-size: 11px">活動日期:2023.12.12(一)8:00</p>

                <div class="block2-txt-child1 flex-col-l">
                  <div style="width: 100%; overflow: hidden">
                    <div style="float: left">
                      <a
                        href="product-detail.html"
                        class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6"
                      >
                        哪裡的音樂會
                      </a>
                    </div>
                    <div style="float: right">
                      <span class="stext-105 cl3"> TWD 500 </span>
                    </div>
                  </div>
                  <hr
                    style="margin-top: 0"
                    size="8px"
                    align="center"
                    width="100%"
                  />
                  <div style="width: 100%; overflow: hidden">
                    <div style="float: left">
                      <a
                        href="https://www.google.com/maps/@25.0356163,121.4798943,15z?authuser=0&entry=ttu"
                      >
                        <img
                          src="./images/icons/iStock-902788474 (1).png"
                          alt=""
                        />
                        台北市</a
                      >
                    </div>
                    <div
                      style="float: right"
                      class="block2-txt-child2 flex-r p-t-3"
                    >
                      <a
                        href="#"
                        class="btn-addwish-b2 dis-block pos-relative js-addwish-b2"
                      >
                        <img
                          class="icon-heart1 dis-block trans-04"
                          src="images/icons/icon-heart-01.png"
                          alt="ICON"
                        />

                        <img
                          class="icon-heart2 dis-block trans-04 ab-t-l"
                          src="images/icons/icon-heart-02.png"
                          alt="ICON"
                        />
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item shoes">
            <!-- Block2 -->
            <div class="block2">
              <div class="block2-pic hov-img0">
                <img
                  src="images/icons/00301812.202110180187M.jpg"
                  alt="IMG-PRODUCT"
                />

                <a
                  href="#"
                  class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1"
                >
                  活動詳細
                </a>
              </div>

              <div class="block2-txt flex-w flex-t p-t-14">
                <p style="font-size: 11px">活動日期:2023.12.12(一)8:00</p>

                <div class="block2-txt-child1 flex-col-l">
                  <div style="width: 100%; overflow: hidden">
                    <div style="float: left">
                      <a
                        href="product-detail.html"
                        class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6"
                      >
                        睡著的舞台劇
                      </a>
                    </div>
                    <div style="float: right">
                      <span class="stext-105 cl3"> TWD 300 </span>
                    </div>
                  </div>
                  <hr
                    style="margin-top: 0"
                    size="8px"
                    align="center"
                    width="100%"
                  />
                  <div style="width: 100%; overflow: hidden">
                    <div style="float: left">
                      <a
                        href="https://www.google.com/maps/@25.0356163,121.4798943,15z?authuser=0&entry=ttu"
                      >
                        <img
                          src="./images/icons/iStock-902788474 (1).png"
                          alt=""
                        />
                        台北市</a
                      >
                    </div>
                    <div
                      style="float: right"
                      class="block2-txt-child2 flex-r p-t-3"
                    >
                      <a
                        href="#"
                        class="btn-addwish-b2 dis-block pos-relative js-addwish-b2"
                      >
                        <img
                          class="icon-heart1 dis-block trans-04"
                          src="images/icons/icon-heart-01.png"
                          alt="ICON"
                        />

                        <img
                          class="icon-heart2 dis-block trans-04 ab-t-l"
                          src="images/icons/icon-heart-02.png"
                          alt="ICON"
                        />
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item men">
            <!-- Block2 -->
            <div class="block2">
              <div class="block2-pic hov-img0">
                <img src="images/iStock-1482843353.jpg" alt="IMG-PRODUCT" />

                <a
                  href="#"
                  class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1"
                >
                  活動詳細
                </a>
              </div>

              <div class="block2-txt flex-w flex-t p-t-14">
                <p style="font-size: 11px">活動日期:2023.12.12(一)8:00</p>

                <div class="block2-txt-child1 flex-col-l">
                  <div style="width: 100%; overflow: hidden">
                    <div style="float: left">
                      <a
                        href="product-detail.html"
                        class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6"
                      >
                        莫名其妙講座
                      </a>
                    </div>
                    <div style="float: right">
                      <span class="stext-105 cl3"> TWD 200 </span>
                    </div>
                  </div>
                  <hr
                    style="margin-top: 0"
                    size="8px"
                    align="center"
                    width="100%"
                  />
                  <div style="width: 100%; overflow: hidden">
                    <div style="float: left">
                      <a
                        href="https://www.google.com/maps/@25.0356163,121.4798943,15z?authuser=0&entry=ttu"
                      >
                        <img
                          src="./images/icons/iStock-902788474 (1).png"
                          alt=""
                        />
                        台北市</a
                      >
                    </div>
                    <div
                      style="float: right"
                      class="block2-txt-child2 flex-r p-t-3"
                    >
                      <a
                        href="#"
                        class="btn-addwish-b2 dis-block pos-relative js-addwish-b2"
                      >
                        <img
                          class="icon-heart1 dis-block trans-04"
                          src="images/icons/icon-heart-01.png"
                          alt="ICON"
                        />

                        <img
                          class="icon-heart2 dis-block trans-04 ab-t-l"
                          src="images/icons/icon-heart-02.png"
                          alt="ICON"
                        />
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item watches">
            <!-- Block2 -->
            <div class="block2">
              <div class="block2-pic hov-img0">
                <img src="images/icons/97928.jpg" alt="IMG-PRODUCT" />

                <a
                  href="#"
                  class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1"
                >
                  活動詳細
                </a>
              </div>

              <div class="block2-txt flex-w flex-t p-t-14">
                <p style="font-size: 11px">活動日期:2023.12.12(一)8:00</p>

                <div class="block2-txt-child1 flex-col-l">
                  <div style="width: 100%; overflow: hidden">
                    <div style="float: left">
                      <a
                        href="product-detail.html"
                        class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6"
                      >
                        永遠搶不到的演唱會
                      </a>
                    </div>
                    <div style="float: right">
                      <span class="stext-105 cl3"> TWD 900 </span>
                    </div>
                  </div>
                  <hr
                    style="margin-top: 0"
                    size="8px"
                    align="center"
                    width="100%"
                  />
                  <div style="width: 100%; overflow: hidden">
                    <div style="float: left">
                      <a
                        href="https://www.google.com/maps/@25.0356163,121.4798943,15z?authuser=0&entry=ttu"
                      >
                        <img
                          src="./images/icons/iStock-902788474 (1).png"
                          alt=""
                        />
                        台北市</a
                      >
                    </div>
                    <div
                      style="float: right"
                      class="block2-txt-child2 flex-r p-t-3"
                    >
                      <a
                        href="#"
                        class="btn-addwish-b2 dis-block pos-relative js-addwish-b2"
                      >
                        <img
                          class="icon-heart1 dis-block trans-04"
                          src="images/icons/icon-heart-01.png"
                          alt="ICON"
                        />

                        <img
                          class="icon-heart2 dis-block trans-04 ab-t-l"
                          src="images/icons/icon-heart-02.png"
                          alt="ICON"
                        />
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Load more -->
        <div class="flex-c-m flex-w w-full p-t-45">
          <a
            href="#"
            class="flex-c-m stext-101 cl5 size-103 bg2 bor1 hov-btn1 p-lr-15 trans-04"
          >
            Load More
          </a>
        </div>
      </div>
    </div>

    <!-- Footer -->
    <footer class="bg3 p-t-75 p-b-32">
      <div class="container">
        <div class="row">
          <div class="col-sm-6 col-lg-3 p-b-50">
            <h4 class="stext-301 cl0 p-b-30">Categories</h4>

            <ul>
              <li class="p-b-10">
                <a href="#" class="stext-107 cl7 hov-cl1 trans-04"> Women </a>
              </li>

              <li class="p-b-10">
                <a href="#" class="stext-107 cl7 hov-cl1 trans-04"> Men </a>
              </li>

              <li class="p-b-10">
                <a href="#" class="stext-107 cl7 hov-cl1 trans-04"> Shoes </a>
              </li>

              <li class="p-b-10">
                <a href="#" class="stext-107 cl7 hov-cl1 trans-04"> Watches </a>
              </li>
            </ul>
          </div>

          <div class="col-sm-6 col-lg-3 p-b-50">
            <h4 class="stext-301 cl0 p-b-30">Help</h4>

            <ul>
              <li class="p-b-10">
                <a href="#" class="stext-107 cl7 hov-cl1 trans-04">
                  Track Order
                </a>
              </li>

              <li class="p-b-10">
                <a href="#" class="stext-107 cl7 hov-cl1 trans-04"> Returns </a>
              </li>

              <li class="p-b-10">
                <a href="#" class="stext-107 cl7 hov-cl1 trans-04">
                  Shipping
                </a>
              </li>

              <li class="p-b-10">
                <a href="#" class="stext-107 cl7 hov-cl1 trans-04"> FAQs </a>
              </li>
            </ul>
          </div>

          <div class="col-sm-6 col-lg-3 p-b-50">
            <h4 class="stext-301 cl0 p-b-30">GET IN TOUCH</h4>

            <p class="stext-107 cl7 size-201">
              Any questions? Let us know in store at 8th floor, 379 Hudson St,
              New York, NY 10018 or call us on (+1) 96 716 6879
            </p>

            <div class="p-t-27">
              <a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
                <i class="fa fa-facebook"></i>
              </a>

              <a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
                <i class="fa fa-instagram"></i>
              </a>

              <a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
                <i class="fa fa-pinterest-p"></i>
              </a>
            </div>
          </div>

          <div class="col-sm-6 col-lg-3 p-b-50">
            <h4 class="stext-301 cl0 p-b-30">Newsletter</h4>

            <form>
              <div class="wrap-input1 w-full p-b-4">
                <input
                  class="input1 bg-none plh1 stext-107 cl7"
                  type="text"
                  name="email"
                  placeholder="email@example.com"
                />
                <div class="focus-input1 trans-04"></div>
              </div>

              <div class="p-t-18">
                <button
                  class="flex-c-m stext-101 cl0 size-103 bg1 bor1 hov-btn2 p-lr-15 trans-04"
                >
                  Subscribe
                </button>
              </div>
            </form>
          </div>
        </div>

        <div class="p-t-40">
          <div class="flex-c-m flex-w p-b-18">
            <a href="#" class="m-all-1">
              <img src="images/icons/icon-pay-01.png" alt="ICON-PAY" />
            </a>

            <a href="#" class="m-all-1">
              <img src="images/icons/icon-pay-02.png" alt="ICON-PAY" />
            </a>

            <a href="#" class="m-all-1">
              <img src="images/icons/icon-pay-03.png" alt="ICON-PAY" />
            </a>

            <a href="#" class="m-all-1">
              <img src="images/icons/icon-pay-04.png" alt="ICON-PAY" />
            </a>

            <a href="#" class="m-all-1">
              <img src="images/icons/icon-pay-05.png" alt="ICON-PAY" />
            </a>
          </div>

          <p class="stext-107 cl6 txt-center">
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            Copyright &copy;
            <script>
              document.write(new Date().getFullYear());
            </script>
            All rights reserved |Made with
            <i class="fa fa-heart-o" aria-hidden="true"></i> by
            <a href="https://colorlib.com" target="_blank">Colorlib</a> &amp;
            distributed by
            <a href="https://themewagon.com" target="_blank">ThemeWagon</a>
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
          </p>
        </div>
      </div>
    </footer>

    <!-- Back to top -->
    <div class="btn-back-to-top" id="myBtn">
      <span class="symbol-btn-back-to-top">
        <i class="zmdi zmdi-chevron-up"></i>
      </span>
    </div>
    <!--動態1生成測試-->

    <!--動態1生成測試-->
    <!-- Modal1 -->

    <div class="wrap-modal1 js-modal1 p-t-60 p-b-20">
      <div class="overlay-modal1 js-hide-modal1"></div>

      <div class="container">
        <div class="bg0 p-t-60 p-b-30 p-lr-15-lg how-pos3-parent">
          <button class="how-pos3 hov3 trans-04 js-hide-modal1">
            <img src="images/icons/icon-close.png" alt="CLOSE" />
          </button>

          <div class="row">
            <div class="col-md-6 col-lg-7 p-b-30">
              <div class="p-l-25 p-r-30 p-lr-0-lg">
                <div class="wrap-slick3 flex-sb flex-w">
                  <div class="wrap-slick3-dots"></div>
                  <div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

                  <div class="slick3 gallery-lb">
                    <div id="img1YA" class="item-slick3" data-thumb="">
                      <div class="wrap-pic-w pos-relative">
                        <img id="img1YA" src="#" alt="IMG-PRODUCT" />

                        <a
                          id="img1YAa"
                          class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04"
                          href="#"
                        >
                          <i class="fa fa-expand"></i>
                        </a>
                      </div>
                    </div>

                    <div
                      id="img2YAa"
                      class="item-slick3"
                      data-thumb="images/icons/iStock-961709574.jpg"
                    >
                      <div class="wrap-pic-w pos-relative">
                        <img
                          id="img2YAa"
                          src="images/icons/iStock-961709574.jpg"
                          alt="IMG-PRODUCT"
                        />

                        <a
                          id="img2YAa"
                          class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04"
                          href="images/icons/iStock-961709574.jpg"
                        >
                          <i class="fa fa-expand"></i>
                        </a>
                      </div>
                    </div>

                    <div
                      class="item-slick3"
                      data-thumb="images/icons/iStock-461162561.jpg"
                    >
                      <div class="wrap-pic-w pos-relative">
                        <img
                          src="images/icons/iStock-461162561.jpg"
                          alt="IMG-PRODUCT"
                        />

                        <a
                          class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04"
                          href="images/icons/iStock-461162561.jpg"
                        >
                          <i class="fa fa-expand"></i>
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-md-6 col-lg-5 p-b-30">
              <div class="p-r-50 p-t-5 p-lr-0-lg">
                <h4
                  class="mtext-105 cl2 js-name-detail p-b-14"
                  id="dynamic-heading"
                ></h4>

                <span class="mtext-106 cl2" id="priceYA"></span>

                <p class="stext-102 cl3 p-t-23" id="castYA"></p>

                <!--活動簡介 規模  時間  -->
                <div class="p-t-33">
                  <div class="flex-w flex-r-m p-b-10">
                    <div class="size-203 flex-c-m respon6">場地</div>

                    <div class="size-204 respon6-next">
                      <div class="rs1-select2 bor8 bg0">
                        <div class="seatWT" id="act_date"></div>

                        <div class="dropDownSelect2"></div>
                      </div>
                    </div>
                  </div>

                  <div class="flex-w flex-r-m p-b-10">
                    <div class="size-203 flex-c-m respon6">活動日期</div>

                    <div class="size-204 respon6-next">
                      <div class="rs1-select2 bor8 bg0">
                        <div class="actDate" id="act_date">2024-10-10</div>

                        <div class="dropDownSelect2"></div>
                      </div>
                    </div>
                  </div>

                  <div class="flex-w flex-r-m p-b-10">
                    <div class="size-204 flex-w flex-m respon6-next">
                      <button class="seatsTry">
                        <a id="dynamic-link" href="#">馬上前往購買</a>
                      </button>
                    </div>
                  </div>
                </div>

                <!--  -->
                <div class="flex-w flex-m p-l-100 p-t-40 respon7">
                  <div class="flex-m bor9 p-r-10 m-r-11">
                    <a
                      href="#"
                      class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100"
                      data-tooltip="Add to Wishlist"
                    >
                      <i class="zmdi zmdi-favorite"></i>
                    </a>
                  </div>

                  <a
                    href="#"
                    class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100"
                    data-tooltip="Facebook"
                  >
                    <i class="fa fa-facebook"></i>
                  </a>

                  <a
                    href="#"
                    class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100"
                    data-tooltip="Twitter"
                  >
                    <i class="fa fa-twitter"></i>
                  </a>

                  <a
                    href="#"
                    class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100"
                    data-tooltip="Google Plus"
                  >
                    <i class="fa fa-google-plus"></i>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!--===============================================================================================-->
    <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/animsition/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/bootstrap/js/popper.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/select2/select2.min.js"></script>
    <script>
      $(".js-select2").each(function () {
        $(this).select2({
          minimumResultsForSearch: 20,
          dropdownParent: $(this).next(".dropDownSelect2"),
        });
      });
    </script>
    <!--===============================================================================================-->
    <script src="vendor/daterangepicker/moment.min.js"></script>
    <script src="vendor/daterangepicker/daterangepicker.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/slick/slick.min.js"></script>
    <script src="js/slick-custom.js"></script>
    <!--===============================================================================================-->
    <script src="vendor/parallax100/parallax100.js"></script>
    <script>
      $(".parallax100").parallax100();
    </script>
    <!--===============================================================================================-->
    <script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
    <script>
      $(".gallery-lb").each(function () {
        // the containers for all your galleries
        $(this).magnificPopup({
          delegate: "a", // the selector for gallery item
          type: "image",
          gallery: {
            enabled: true,
          },
          mainClass: "mfp-fade",
        });
      });
    </script>

    <script src="vendor/isotope/isotope.pkgd.min.js"></script>

    <script src="vendor/sweetalert/sweetalert.min.js"></script>
    <script>
      $(".js-addwish-b2, .js-addwish-detail").on("click", function (e) {
        e.preventDefault();
      });

      $(".js-addwish-b2").each(function () {
        var nameProduct = $(this)
          .parent()
          .parent()
          .parent()
          .find(".js-name-b2")
          .html();
        $(this).on("click", function () {
          swal(nameProduct, "已加入我的最愛 !", "success");

          $(this).addClass("js-addedwish-b2");
          $(this).off("click");
        });
      });

      $(".js-addwish-detail").each(function () {
        var nameProduct = $(this)
          .parent()
          .parent()
          .parent()
          .find(".js-name-detail")
          .html();

        $(this).on("click", function () {
          swal(nameProduct, "已加入我的最愛 !", "success");

          $(this).addClass("js-addedwish-detail");
          $(this).off("click");
        });
      });

      /*---------------------------------------------*/

      $(".js-addcart-detail").each(function () {
        var nameProduct = $(this)
          .parent()
          .parent()
          .parent()
          .parent()
          .find(".js-name-detail")
          .html();
        $(this).on("click", function () {
          swal(nameProduct, "is added to cart !", "success");
        });
      });
    </script>
    <!--===============================================================================================-->
    <script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script>
      $(".js-pscroll").each(function () {
        $(this).css("position", "relative");
        $(this).css("overflow", "hidden");
        var ps = new PerfectScrollbar(this, {
          wheelSpeed: 1,
          scrollingThreshold: 1000,
          wheelPropagation: false,
        });

        $(window).on("resize", function () {
          ps.update();
        });
      });
    </script>
    <!--===============================================================================================-->
    <script src="js/main.js"></script>

    <!--更改 小視窗消息-->
    <script>
      //第一組
      var imgElement = document.getElementById("img1YA");

      var slickDotsElement = document.querySelector(".slick3-dots");

      // 找到该元素下的所有 img 元素
      var imgElements = slickDotsElement.querySelectorAll("img");

      // 确保至少有一个 img 元素
      if (imgElements.length > 0) {
        // 获取第一个 img 元素
        var firstImgElement = imgElements[0];
        // 动态设置 img 元素的地址
        firstImgElement.src = "images/icons/" + "iStock-831601850" + ".jpg";
      }
      if (imgElements.length >= 1) {
        // 获取第一个 img 元素
        var firstImgElement = imgElements[1];
        // 动态设置 img 元素的地址
        firstImgElement.src = "images/icons/" + "iStock-961709574" + ".jpg";
      }
      if (imgElements.length >= 2) {
        // 获取第一个 img 元素
        var firstImgElement = imgElements[2];
        // 动态设置 img 元素的地址
        firstImgElement.src = "images/icons/" + "iStock-461162561" + ".jpg";
      }
      var img1YAa = document.getElementById("img1YAa");
      var img1YAaa = "images/icons/" + "iStock-831601850" + ".jpg";

      var dynamicImagePath = "images/icons/" + "iStock-831601850" + ".jpg";
      var dynamicContent = "我改到了YA!!";
      var dynamicPrice = 320;
      var castYA = `內容簡介:單口喜劇（英語：Stand-up
                  comedy）粵語稱棟篤笑，正字為「戙𡰪笑」（dungduksíu），中國大陸和台灣稱為單口喜劇，又稱脫口秀、單人喜劇、站立喜劇等，是一種喜劇表演，通常是由喜劇演員一個人，直接站在觀眾面前，以搞笑方式面對觀眾，使觀眾發笑。
                  通常是一位喜劇演員站在台上表演喜劇，而且多以語言笑話為主。`;
      var seatWT1 = "測試規模";
      var actDate = "1970-01-01";
      var dynamicLink = "./seat/seatMiddle20X20/seatnotest.html";
      var linkElement = document.getElementById("dynamic-link");
      imgElement.src = dynamicImagePath;
      $("#dynamic-heading").text(dynamicContent);
      $("#priceYA").text("TWD" + dynamicPrice);
      $("#castYA").text(castYA);
      $(".seatWT").text(seatWT1);
      $(".actDate").text(actDate);
      linkElement.href = dynamicLink;
      img1YAa.href = img1YAaa;
    </script>
  </body>
</html>
