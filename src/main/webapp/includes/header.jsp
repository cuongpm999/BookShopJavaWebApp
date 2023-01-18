<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- HEADER -->
<div id="header">
	<div class="header-top">
		<div class="container">
			<div class="row">
				<div class="col-md-6 header-top-left">
					<i class="fas fa-user"></i> Cường Phạm - Analysis & Design
				</div>
				<div class="col-md-6 header-top-right">
					<c:choose>
						<c:when test="${not empty customerLogin }">
							<span style="color: #020528E6"><a href="/BookStoreOnline/view-profile"><i
									class="fas fa-user"></i>
									${customerLogin.fullName.firstName }</a></span>
							<div class="header-separator"></div>
							<a href="/BookStoreOnline/my-order"><i class="fas fa-clipboard"> </i> Đơn
								hàng của tôi</a>
							<div class="header-separator"></div>
							<a href="/BookStoreOnline/logout">Đăng xuất</a>
						</c:when>
						<c:when test="${empty customerLogin}">
							<a href="/BookStoreOnline/login"><i class="fas fa-sign-in-alt"> </i>&nbsp;&nbsp;Đăng
								nhập </a>
							<div class="header-separator"></div>
							<a href="/BookStoreOnline/register">Đăng kí</i></a>
						</c:when>
					</c:choose>

				</div>
			</div>
		</div>
	</div>

	<div class="header-bottom">
		<div class="container">
			<div class="row d-flex align-items-center">
				<div class="col-md-2">
					<a href="/BookStoreOnline"><img
						src="${pageContext.request.contextPath}/static/img/logo.png"
						style="width: 55px;" alt="logo"></a>
				</div>
				<div class="col-md-8" style="display: flex; align-items: center;">
					<ul class="navbar-nav" style="width: 28%">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbardrop"
							data-toggle="dropdown">DANH MỤC SẢN PHẨM</a>
							<div class="dropdown-menu" style="line-height: 30px;">
							<a class="dropdown-item" href="/BookStoreOnline/collections">Tất cả sản phẩm</a>
								<a class="dropdown-item" href="/BookStoreOnline/collections?category=Lịch sử truyền thống">Lịch sử truyền thống</a> <a
									class="dropdown-item" href="/BookStoreOnline/collections?category=Kiến thức khoa học">Kiến thức khoa học</a> <a
									class="dropdown-item" href="/BookStoreOnline/collections?category=Văn học Việt Nam">Văn học Việt Nam</a> <a
									class="dropdown-item" href="/BookStoreOnline/collections?category=Văn học nước ngoài">Văn học nước ngoài</a> <a
									class="dropdown-item" href="/BookStoreOnline/collections?category=Truyện tranh">Truyện tranh</a>
							</div></li>
					</ul>
					<div class="nav-search" style="width: 70%">
						<form action="/BookStoreOnline/collections" method="get"
							style="width: 100%; display: flex; align-items: center;">
							<input type="text" placeholder="Search name..." name="key" value="${key }">
							<button class="btn" type="submit">
								<i class="fas fa-search"
									style="color: rgba(55, 0, 255, 0.884); font-size: 20px;"></i>
							</button>
						</form>
					</div>
				</div>
				<div class="col-md-2 text-center">
					<div class="nav-cart">
						<a class="nav-link" href="/BookStoreOnline/cart"
							style="width: max-content; margin: auto;"> <img class="cart"
							src="${pageContext.request.contextPath}/static/img/cart.png"
							alt="cart" style="width: 45px;"> <span class="count-item">${not empty cart ? cart.totalQuantity : 0 }</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- ------ -->