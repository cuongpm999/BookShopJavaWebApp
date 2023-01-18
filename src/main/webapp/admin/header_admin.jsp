<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="sidebar">
	<div class="sidebar-inner">
		<div class="sidebar-logo">
			<a class="sidebar-link" href="/BookStoreOnline"> <img
				class="icon"
				src="${pageContext.request.contextPath}/static/img/logo.png">
				<h5 class="logo-text">Admin Manage</h5>
			</a>
		</div>

		<nav class="navbar scrollbar" id="style-1">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="/BookStoreOnline/admin"><i class="fas fa-home"></i>Dashboard</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/BookStoreOnline/admin/manage/author"><i
						class="fas fa-pencil-alt"></i>Manage author</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/BookStoreOnline/admin/manage/publisher"><i
						class="fas fa-warehouse"></i>Manage publisher</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/BookStoreOnline/admin/manage/book"><i
						class="fas fa-book-open"></i>Manage book</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/BookStoreOnline/admin/manage/book-item"><i
						class="fas fa-book-reader"></i>Manage book item</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/BookStoreOnline/admin/manage/customer"><i
						class="fas fa-users"></i>Manage customer</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/BookStoreOnline/admin/manage/staff"><i
						class="fas fa-user-tie"></i>Manage staff</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/BookStoreOnline/admin/manage/order"><i
						class="fas fa-shopping-cart"></i>Manage order</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/BookStoreOnline/admin/manage/shipment"><i
						class="fas fa-shipping-fast"></i>Manage shipment</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"><i class="fas fa-chart-bar"></i>Statistics</a>
					<div class="dropdown-menu">
						<a class="dropdown-item"
							href="/BookStoreOnline/admin/statistic/book-item">Thống kê
							top 9 quyển sách<br>được bán chạy nhất
						</a> <a class="dropdown-item"
							href="/BookStoreOnline/admin/statistic/customer">Thống kê top
							9 khách hàng<br>chi mua nhiều nhất
						</a> <a class="dropdown-item"
							href="/BookStoreOnline/admin/statistic/shipment">Thống kê top
							shipment<br>được sửa dụng nhiều nhất
						</a> <a class="dropdown-item"
							href="/BookStoreOnline/admin/statistic/income">Thống kê
							thu nhập theo<br>một tháng của năm nào đó
						</a>
					</div></li>
			</ul>

		</nav>
	</div>
</div>
<div class="page-container">
	<div class="header-container">
		<nav class="navbar">
			<ul class="navbar-nav">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"><img class="icon" alt="user"
						src="${pageContext.request.contextPath}/static/img/icons8-user-male-100.png">
						<div class="text-icon">${staffLogin.account.username }</div> </a>
					<div class="dropdown-menu">
						<a class="dropdown-item"
							href="/BookStoreOnline/admin/view-profile-staff"><i
							class="far fa-user"></i>Profile</a> <a class="dropdown-item"
							href="/BookStoreOnline/logout-staff"><i
							class="fas fa-power-off"></i>Logout</a>
					</div></li>
			</ul>
		</nav>
	</div>